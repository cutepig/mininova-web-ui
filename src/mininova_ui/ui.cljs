(ns mininova-ui.ui
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

(def default-panel :osc)

(rf/reg-event-db ::panel
  [rf/debug]
  (fn [db [_ panel]]
    (assoc db ::panel panel)))

(rf/reg-sub ::panel
  (fn [db _]
    (get db ::panel default-panel)))

(rf/reg-event-db ::control
  [rf/debug]
  (fn [db [_ id value midi?]]
    (assoc-in db [::control id] value)))

(rf/reg-sub ::control
  (fn [db [_ id]]
    (get-in db [::control id])))

(defn tabs []
  (let [tab @(rf/subscribe [::panel])]
    [:ul.tabs
      [:li {:class (if (or (= tab :osc)) (nil? tab) "is-active")
            :on-click #(rf/dispatch [::panel :osc])}
        "Osc"]
      [:li {:class (if (= tab :filter) "is-active")
            :on-click #(rf/dispatch [::panel :filter])}
        "Filter"]]))

(defn knob [{:keys [id label min max]}]
  (let [value @(rf/subscribe [::control id])]
    [:div.knob
      [:label label
        [:input {:type :range
                 :min min
                 :max max
                 :on-change #(rf/dispatch [::control id (.-currentTarget.value %)])}]
        [:span value]]]))

(defn osc-panel []
  [:div.osc-panel
    [:h2 "Oscillator"]
    [knob {:id :osc/semitune :label "Semitune" :min -12 :max 12}]])

(defn filter-panel []
  [:div.filter-panel
    [:h2 "Filter"]])

(defn main-panel []
  (let [tab @(rf/subscribe [::panel])]
    [:main.main-panel
      [tabs]
      (condp = tab
        :osc [osc-panel]
        :filter [filter-panel]
        nil)]))
  
