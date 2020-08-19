(ns health-samurai-front-demo.core
  (:require
   [reagent.dom :as d]
   [health-samurai-front-demo.api :as api]
   [health-samurai-front-demo.components.main :refer [header patients]]
   [health-samurai-front-demo.components.create-component :refer [create-patient-component]]))

(defn app []
  [:div
   [header]
   [patients]
   [create-patient-component]])

(defn mount-root []
  (api/get-all-patients)
  (d/render [app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
