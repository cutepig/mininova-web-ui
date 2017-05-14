(ns mininova-web-ui.midi
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

;; MIDI input and output maps are basically ES6 Maps
(defn Map->clj [m]
  (let [o #js {}]
    (.forEach m #(aset o %2 %1))
    (js->clj o)))

;; MIDI event data is Uint8Array
(defn Array->clj [a]
  (let [v #js []]
    (.forEach a #(.push v %))
    (js->clj v)))

(defn on-nrpn [nrpn-v]
  (let [msb (nth (first @nrpn-v) 2)
        lsb (nth (second @nrpn-v) 2)
        value (nth (nth @nrpn-v 2) 2)]
    (rf/dispatch [::cc [0xb0 [msb lsb] value]])
    (reset! nrpn-v [])))

(defn on-cc [nrpn-v data]
  ;; Check if we are in the middle of NRPN message
  (if (< 0 (count @nrpn-v))
    (do
      (swap! nrpn-v conj data)
      ;; TODO: Handle 4 len vector
      (if (= 3 (count @nrpn-v))
        (on-nrpn nrpn-v)))
    ;; Check first NRPN message
    (if (= 0x63 (second data))
      (swap! nrpn-v conj data)
      (rf/dispatch [::cc data]))))

(def nrpn-v (atom []))

(defn on-midi-message [data]
  ; (println ::on-midi-message data)
  (let [status (first data)]
    (condp = status
      0xb0 (on-cc nrpn-v data)
      0xc0 (rf/dispatch [::patch data])
      0xf0 (rf/dispatch [::sysex data])
      nil)))

(defn connect-input! [input port]
  (println "MiniNova input is now connected")
  (set! (.-onmidimessage port) #(on-midi-message (Array->clj (.-data %))))
  (rf/dispatch [::connect true])
  (reset! input port))

(defn disconnect-input! [input]
  (println "MiniNova input is now disconnected")
  (reset! input nil))

(defn connect-output! [output port]
  (println "MiniNova output is now connected")
  (reset! output port))

(defn disconnect-output! [output]
  (println "MiniNova output is now disconnected")
  (reset! output nil))

(defn configure-ports [input-atom output-atom midi-access]
  (let [input-port (->> (Map->clj (.-inputs midi-access))
                    vals
                    (filter #(= "MiniNova" (.-name %)))
                    first)
        output-port (->> (Map->clj (.-outputs midi-access))
                     vals
                     (filter #(= "MiniNova" (.-name %)))
                     first)]
    (println "on-state-change" [input-atom output-atom] [input-port output-port])
    ;; FIXME: `connect-input` is called twice
    (if (and (some? input-port) (nil? @input-atom))
      (connect-input! input-atom input-port))
    (if (and (nil? input-port) (some? @input-atom))
      (disconnect-input! input-atom))
    (if (and (some? output-port) (nil? @output-atom))
      (connect-output! output-atom output-port))
    (if (and (nil? output-port) (some? @output-atom))
      (disconnect-output! output-atom))))

(defn initialize-mininova! []
  (let [input (atom nil)
        output (atom nil)
        midi-access (atom nil)
        on-state-change #(configure-ports input output @midi-access)
        on-midi-access (fn [ma]
                         (reset! midi-access ma)
                         (set! (.-onstatechange ma) on-state-change)
                         (configure-ports input output ma))]
    (-> (js/navigator.requestMIDIAccess #js {:sysex true})
      (.then on-midi-access)
      (.catch #(println "MIDI access denied" %)))

    (rf/reg-fx ::midi
      (fn [midi-msgs]
        (if (some? @output)
          (doseq [msg midi-msgs
                  :let [array (.from js/Uint8Array (clj->js msg))]]
            (do (println "Sending out" array)
                (.send @output array))))))

    (rf/reg-event-fx ::midi
      [rf/debug]
      (fn [cofx [_ cc val]]
        ;; TODO: Deconstruct this into MIDI values here?
        (assoc cofx ::midi [[176 cc val]])))))

