(ns mininova-web-ui.core
  (:require [reagent.core :as reagent :refer [atom]]
            [mininova-web-ui.ui :as ui]))

(enable-console-print!)

(println "This text is printed from src/mininova-web-ui/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(reagent/render-component [ui/main-panel]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

