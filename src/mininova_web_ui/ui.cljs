(ns mininova-web-ui.ui
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [mininova-web-ui.midi :as midi]))

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

(defn knob [{:keys [cc label min max]}]
  (let [value @(rf/subscribe [::control cc])]
    [:div.knob
      [:label label
        [:input {:type :range
                 :min min
                 :max max
                 :on-change #(rf/dispatch [::control cc (.-currentTarget.value %)])}]
        [:span value]]]))

(defn osc-panel []
  [:div.osc-panel
    [:h2 "Oscillator"]
    [knob {:cc 26 :label "Semitune" :min 0 :max 127}]])

(defn filter-panel []
  [:div.filter-panel
    [:h2 "Filter"]])

(defn env-panel []
  [:div.env-panel
    [:h2 "Envelope"]
    [knob {:cc [0 1] :label "Attack" :min 0 :max 127}]])

(defn main-panel []
  (let [tab @(rf/subscribe [::panel])]
    [:main.main-panel
      [tabs]
      (condp = tab
        :osc [osc-panel]
        :filter [filter-panel]
        :env [env-panel]
        [:h2 "Select a panel"])]))
