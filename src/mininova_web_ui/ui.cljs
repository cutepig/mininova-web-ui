(ns mininova-web-ui.ui
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [mininova-web-ui.midi :as midi]
            [mininova-web-ui.params :as params]))

(def default-panel :osc)

(rf/reg-event-db ::panel
  [rf/debug]
  (fn [db [_ panel]]
    (assoc db ::panel panel)))

(rf/reg-sub ::panel
  (fn [db _]
    (get db ::panel default-panel)))

(rf/reg-event-fx ::control
  [rf/debug]
  (fn [fx [_ cc value from-midi?]]
    (let [fx- (assoc-in fx [:db ::control cc] value)]
      (if from-midi?
        fx-
        ;; If the event doesn't originate from midi, send it
        (assoc fx- ::midi/midi
          (if (vector? cc)
            ;; NRPN
            [[0xB0 0x63 (first cc)]
             [0xB0 0x62 (second cc)]
             [0xB0 0x06 value]]
            ;; Regular CC
            [[0xB0 cc value]]))))))

(rf/reg-sub ::control
  (fn [db [_ cc]]
    (get-in db [::control cc])))

(defn tabs []
  (let [tab @(rf/subscribe [::panel])]
    [:ul.tabs
      [:li {:class (if (or (= tab :osc)) (nil? tab) "is-active")
            :on-click #(rf/dispatch [::panel :osc])}
        "Osc"]
      [:li {:class (if (= tab :filter) "is-active")
            :on-click #(rf/dispatch [::panel :filter])}
        "Filter"]
      [:li {:class (if (= tab :env) "is-active")
            :on-click #(rf/dispatch [::panel :env])}
        "Env"]]))

(defn map-in-out [in out value]
  (+ (- value (first in)) (first out)))

(defn knob [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control (:cc param)])]
    [:div.knob
      [:label label
        [:input {:id id
                 :type :range
                 :value value
                 :min (first (:in param))
                 :max (second (:in param))
                 :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}]
        [:span (map-in-out (:in param) (:out param) value)]]]))

(defn select-enum [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control (:cc param)])]
    [:div.select-enum
      [:label label
        [:select {:id id
                  :value (or value "")
                  :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}
         [:option {:value "" :disabled true} label]
         (for [[val lbl] (map vector (range (first (:in param)) (inc (second (:in param)))) (:enum param))]
           ^{:key val}
           [:option {:value val} lbl])]]]))

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
     [:h2 "Filter"]
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

(defn env-panel []
  [:div.env-panel
    [:h2 "Envelope"]
    [knob {:id :env-2/attack :label "Attack"}]])

(defn main-panel []
  (let [tab @(rf/subscribe [::panel])]
    [:main.main-panel
      [tabs]
      (condp = tab
        :osc [osc-panel]
        :filter [filter-panel]
        :env [env-panel]
        [:h2 "Select a panel"])]))
