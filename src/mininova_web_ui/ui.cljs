(ns mininova-web-ui.ui
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))


(rf/reg-event-db ::panel
  [rf/debug]
  (fn [db [_ panel]]
    (assoc db ::panel panel)))

(rf/reg-sub ::panel
  (fn [db _]
    (::panel db)))

(defn tabs []
  (let [tab (rf/subscribe [::panel])]
    [:ul.tabs
      [:li {:class (if (= tab :osc) "is-active")
            :on-click #(rf/dispatch [::panel :osc])}
        "Osc"]
      [:li {:class (if (= tab :filter) "is-active")
            :on-click #(rf/dispatch [::panel :filter])}
        "Filter"]]))

(defn osc-panel []
  [:div.osc-panel
    [:h2 "Oscillator"]])

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
        [:h2 "Select a panel"])]))

