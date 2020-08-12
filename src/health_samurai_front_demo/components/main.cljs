(ns health-samurai-front-demo.components.main
  (:require [health-samurai-front-demo.state :as state]
            [health-samurai-front-demo.api :refer [get-all-patients]]
            ))

(defn header []
  [:header "Health Samurai Demo"])

(defn get-result [response]
  (:result @response))

(defn patients []
  [:div [:ul
         (let [patient-result (get-result state/patients)]
           (for [patient patient-result]
             [:li (str "Id : "(:id patient) " | Full Name: " (:full_name patient))]))

         [:input {:type "button" :value "Reload!" :style {:margin-top "5%"}
                  :on-click #(swap! state/patients (get-all-patients))}]
         [:input {:type "button" :value "Reset!" :style {:margin-left "5%"}
                  :on-click #(reset! state/patients {})}]]])
