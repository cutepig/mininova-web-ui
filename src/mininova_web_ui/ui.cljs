(ns mininova-web-ui.ui
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [mininova-web-ui.midi :as midi]
            [mininova-web-ui.params :as params]))

(def patch-inspector-enabled? true)

(def patch-request-message
  [0xF0 0x00 0x20 0x29 0x03 0x01 0x7F 0x40 0x00 0x00 0x00 0x00 0x00 0xF7])

(def patch-response-sentinel
  [0xF0 0x00 0x20 0x29 0x03 0x01 0x7F 0x00 0x00 0x09 0x04])

(def default-panel (keyword (subs js/location.hash 1)))

;; FIXME: Get ridi of `re-frame: no :fx handler registered for :event` error
(rf/reg-fx :event (fn [_ _]))

(rf/reg-event-db ::panel
  [rf/debug]
  (fn [db [_ panel]]
    (assoc db ::panel panel)))

(rf/reg-sub ::panel
  (fn [db _]
    (get db ::panel default-panel)))

(defn send-midi-control [fx cc value from-midi?]
  (if from-midi?
    fx
    ;; If the event doesn't originate from midi, send it
    (assoc fx ::midi/midi
      (if (vector? cc)
        ;; NRPN
        [[0xB0 0x63 (first cc)]
         [0xB0 0x62 (second cc)]
         [0xB0 0x06 value]]
        ;; Regular CC
        [[0xB0 cc value]]))))

;; Multi control is a CC/NRPN binding that has multiple controls
;; separated by value ranges, for example :env-[1-6]/trigger controls
;; are all bound to NRPN (0 122). This matches the control by value.
(defn set-multi-control [fx cc value params]
  (let [id (->> params
                (filter #(<= (first (:in %)) value (second (:in %))))
                first
                :id)]
    (if (some? id)
      (assoc-in fx [:db ::control id] value)
      fx)))


(defn set-control [fx cc value]
  (let [params (get params/cc->param cc)]
    (if (= 1 (count params))
      (assoc-in fx [:db ::control (:id (first params))] value)
      (set-multi-control fx cc value params))))

(rf/reg-event-fx ::control
  [rf/debug]
  (fn [fx [_ cc value from-midi?]]
    (-> fx
        (set-control cc value)
        (send-midi-control cc value from-midi?))))

(rf/reg-sub ::control
  (fn [db [_ id]]
    (get-in db [::control id])))

(rf/reg-event-fx ::midi/connect
  [rf/debug]
  (fn [fx [_ connected?]]
    (-> fx
        (assoc-in [:db ::connected?] connected?)
        (assoc ::midi/midi (if connected? [patch-request-message])))))

(rf/reg-event-fx ::midi/cc
  [rf/debug]
  (fn [fx [_ [_ cc value]]]
    (-> fx
        (assoc :dispatch [::control cc value true]))))

(rf/reg-event-fx ::midi/patch
  [rf/debug]
  (fn [fx _]
    (assoc fx ::midi/midi [patch-request-message])))

(defn patch-dump? [data]
  (= patch-response-sentinel
     (take (count patch-response-sentinel) data)))

(defn make-set-patch-value [data]
  (fn [db [id param]]
    (if (and (contains? param :offset) (not= 0 (:offset param)))
      (assoc-in db [::control id] (nth data (:offset param)))
      db)))

(defn apply-patch [db data]
  (reduce (make-set-patch-value data) db params/params))

(rf/reg-event-db ::midi/sysex
  [rf/debug]
  (fn [db [_ data]]
    (if (patch-dump? data)
      (-> db
          (apply-patch data)
          (assoc ::patch data)))))

(rf/reg-sub ::patch
  (fn [db]
    (::patch db)))

(defn tab [{:keys [id]}]
  (let [current-tab @(rf/subscribe [::panel])]
    [:a {:class (if (or (= current-tab id)) (nil? current-tab) "is-active")
         :href (str "#" (name id))
         :on-click #(rf/dispatch [::panel id])}
      (first (r/children (r/current-component)))]))

(defn tabs []
  [:ul.tabs
    [:li [tab {:id :filter} "Filter"]]
    [:li [tab {:id :env} "Env"]]
    [:li [tab {:id :lfo} "LFO"]]
    [:li [tab {:id :arp} "ARP"]]
    (if patch-inspector-enabled?
      [:li [tab {:id :patch-inspector} "Patch inspector"]])])

(defn map-in-out [in out value]
  (+ (- value (first in)) (first out)))

(defn knob [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control id])]
    [:div.knob
      [:label label
        [:input {:id (str id)
                 :type :range
                 :value value
                 :min (first (:in param))
                 :max (second (:in param))
                 :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}]
        [:span (map-in-out (:in param) (:out param) value)]]]))

(defn select-enum [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control id])]
    [:div.select-enum
      [:label label
        [:select {:id (str id)
                  :value (or value "")
                  :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}
         [:option {:value "" :disabled true} label]
         (for [[val lbl] (map vector (range (first (:in param)) (inc (second (:in param)))) (:enum param))]
           ^{:key val}
           [:option {:value val} lbl])]]]))

(defn toggle [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control id])]
    [:div.toggle
      [:label label
        [:input {:type :checkbox
                 :checked (= value (second (:in param)))
                 :on-change #(rf/dispatch [::control (:cc param) (if (.-currentTarget.checked %)
                                                                     (second (:in param))
                                                                     (first (:in param)))])}]]]))

(defn osc-strip [index]
  (let [key #(keyword (str "osc-" index "/" %))]
    [:div.osc-strip
      [:h2 (str "Oscillator " index)]
      [select-enum {:id (key "wave") :label "Waveform"}]
      [knob {:id (key "wave-interpolate") :label "WaveInterpolate"}]
      [knob {:id (key "pulse-width-index") :label "PulseWidthIndex"}]
      [knob {:id (key "virtual-sync-depth") :label "VirtualSyncDepth"}]
      [knob {:id (key "hardness") :label "Hardness"}]
      [knob {:id (key "density") :label "Density"}]
      [knob {:id (key "density-detune") :label "DensityDetune"}]
      [knob {:id (key "semitones") :label "Semitones"}]
      [knob {:id (key "cents") :label "Cents"}]
      [knob {:id (key "pitch-bend") :label "PitchBend"}]
      [knob {:id (keyword (str "mixer/osc-" index "-level")) :label "Level"}]]))

(defn osc-panel []
  [:div.osc-panel
    [osc-strip 1]
    [osc-strip 2]
    [osc-strip 3]])

(defn filter-strip [index]
  (let [key #(keyword (str "filter-" index "/" %))]
    [:div.filter-strip
     [:h2 (str "Filter " index)]
     [knob {:id (key "drive") :label "Drive"}]
     [select-enum {:id (key "drive-type") :label "DriveType"}]
     [select-enum {:id (key "type") :label "Type"}]
     [knob {:id (key "track") :label "Track"}]
     [knob {:id (key "resonance") :label "Resonance"}]
     [knob {:id (key "frequency") :label "Frequency"}]
     [knob {:id (key "q-normalise") :label "QNormalize"}]
     [knob {:id (key "env-2->freq") :label "Env2ToFreq"}]]))

(defn filter-panel []
  [:div.filter-panel
    [filter-strip 1]
    [filter-strip 2]])

(defn env-strip [index]
  (let [key #(keyword (str "env-" index "/" %))]
    [:div.env-strip
      [:h2 (condp = index
             1 "Amp envelope"
             2 "Filter envelope"
             (str "Envelope " index))]
      (if (<= 1 index 2)
        [knob {:id (key "velocity") :label "Velocity"}]
        [knob {:id (key "delay") :label "Delay"}])
      [knob {:id (key "attack") :label "Attack"}]
      [knob {:id (key "decay") :label "Decay"}]
      [knob {:id (key "sustain") :label "Sustain"}]
      [knob {:id (key "release") :label "Release"}]
      [knob {:id (key "sustain-rate") :label "SustainRate"}]
      [knob {:id (key "sustain-time") :label "SustainTime"}]
      [knob {:id (key "ad-repeats") :label "ADRepeats"}]
      [knob {:id (key "attack-track") :label "AttackTrack"}]
      [knob {:id (key "decay-track") :label "DecayTrack"}]
      [knob {:id (key "level-track") :label "LevelTrack"}]
      [knob {:id (key "attack-slope") :label "AttackSlope"}]
      [knob {:id (key "decay-slope") :label "DecaySlope"}]
      [select-enum {:id (key "anim-trigger") :label "AnimTrigger"}]
      [select-enum {:id (key "trigger") :label "Trigger"}]]))

(defn env-panel []
  [:div.env-panel
    [env-strip 1]
    [env-strip 2]
    [env-strip 3]
    [env-strip 4]
    [env-strip 5]
    [env-strip 6]])

(defn lfo-strip [index]
  (let [key #(keyword (str "lfo-" index "/" %))]
    [:div.lfo-strip
      [:h2 (str "LFO " index)]
      [select-enum {:id (key "waveform") :label "Waveform"}]
      [knob {:id (key "phase-offset") :label "PhaseOffset"}]
      [knob {:id (key "slew-rate") :label "SlewRate"}]
      [knob {:id (key "delay") :label "Delay"}]
      [select-enum {:id (key "delay-sync") :label "DelaySync"}]
      [knob {:id (key "rate") :label "Rate"}]
      [select-enum {:id (key "rate-sync") :label "RateSync"}]
      [select-enum {:id (key "one-shot") :label "OneShot"}]
      [select-enum {:id (key "key-sync") :label "KeySync"}]
      [select-enum {:id (key "common-sync") :label "CommonSync"}]
      [select-enum {:id (key "delay-trigger") :label "DelayTrigger"}]
      [select-enum {:id (key "fade-mode") :label "FadeMode"}]]))

(defn lfo-panel []
  [:div.lfo-panel
    [lfo-strip 1]
    [lfo-strip 2]
    [lfo-strip 3]])

(defn arp-panel []
  [:div.arp-panel
    [:h2 "Arp"]
    [toggle {:id :arp/on :label "On"}]
    [toggle {:id :arp/key-latch :label "KeyLatch"}]
    [knob {:id :arp/octaves :label "Octaves"}]
    [select-enum {:id :arp/rate-sync :label "RateSync"}]
    [knob {:id :arp/gate :label "Gate"}]
    [select-enum {:id :arp/mode :label "Mode"}]
    [select-enum {:id :arp/pattern :label "Pattern"}]
    [knob {:id :arp/swing :label "Swing"}]
    [knob {:id :arp/length :label "Length"}]
    [:ul.arp-panel-steps
      [:li [toggle {:id :arp-1/step :label "Step 1"}]]
      [:li [toggle {:id :arp-2/step :label "Step 2"}]]
      [:li [toggle {:id :arp-3/step :label "Step 3"}]]
      [:li [toggle {:id :arp-4/step :label "Step 4"}]]
      [:li [toggle {:id :arp-5/step :label "Step 5"}]]
      [:li [toggle {:id :arp-6/step :label "Step 6"}]]
      [:li [toggle {:id :arp-7/step :label "Step 7"}]]
      [:li [toggle {:id :arp-8/step :label "Step 8"}]]]])

(defn patch-inspector-panel []
  (let [patch @(rf/subscribe [::patch])]
    [:div.patch-inspector-panel
      [:h2 "Patch inspector"]
      [:button {:on-click #(rf/dispatch [::midi/patch])} "Fetch patch"]
      [:div {:style {:height "400px" :overflow-y "scroll"}}
        [:table
          [:thead
            [:tr [:th "Offset"] [:th "Value"]]]
          [:tbody
            (for [[offset value] (map vector (range (count patch)) patch)]
              ^{:key offset}
              [:tr [:td offset] [:td (str "0b" (.toString value 2))]])]]]]))

(defn main-panel []
  (let [tab @(rf/subscribe [::panel])]
    [:main.main-panel
      [tabs]
      (condp = tab
        :osc [osc-panel]
        :filter [filter-panel]
        :env [env-panel]
        :lfo [lfo-panel]
        :arp [arp-panel]
        :patch-inspector [patch-inspector-panel]
        [:h2 "Select a panel"])]))
