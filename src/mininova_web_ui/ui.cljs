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

(defn map-in-out [in out])

(defn knob [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control (:cc param)])]
    [:div.knob
      [:label label
        [:input {:id id
                 :type :range
                 :value value
                 :min (first (:in param))
                 :max (last (:in param))
                 :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}]
        [:span value]]]))

(defn select-enum [{:keys [id label]}]
  (let [param (get params/params id)
        value @(rf/subscribe [::control (:cc param)])]
    [:div.select-enum
      [:label label
        [:select {:id id
                  :value (or value "")
                  :on-change #(rf/dispatch [::control (:cc param) (.-currentTarget.value %)])}
         [:option {:value ""} "Waveform"]
         (for [[val lbl] (map vector (range (count (:enum param))) (:enum param))]
           ^{:key val}
           [:option {:value val} lbl])]]]))

(defn osc-panel []
  [:div.osc-panel
    [:h2 "Oscillator"]
    [select-enum {:id :osc-1/wave :label "Waveform"}]
    [knob {:id :osc-1/semitones :label "Semitone"}]])

(defn filter-panel []
  [:div.filter-panel
    [:h2 "Filter"]
    [knob {:id :filter-1/frequency :label "Frequency"}]])

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
