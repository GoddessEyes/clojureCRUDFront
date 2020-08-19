(ns health-samurai-front-demo.components.main
  (:require [health-samurai-front-demo.state :as state]
            [health-samurai-front-demo.api :refer [get-all-patients delete-patient-by-id]]))

(defn header []
  [:header {:style {:margin-bottom "5%"}} "Health Samurai Demo"])

(defn get-result [response]
  (:result @response))

(defn patients []
  [:div {:style {:width "100%"}}
   [:table {:style {:width "100%" :white-space "nowrap"} :border "1"}
    [:thead  {:style {:width "100%" :white-space "nowrap"}}
     [:td "Id"]
     [:td "Full Name"]
     [:td "Date birth"]
     [:td "Address"]
     [:td "OMS"]
     [:td  "Gender"]]
    (let [patient-result (get-result state/patients)]
      (for [patient patient-result]
        [:tr {:style {:width "100%" :white-space "nowrap"}}
         [:td (:id patient)]
         [:td (:full_name patient)]
         [:td (:date_birth patient)]
         [:td (:address patient)]
         [:td (:oms patient)]
         [:td (:gender patient)]
         [:td [:button.destroy {:value "Delete" :type "button" :on-click #(delete-patient-by-id (:id patient))} "Delete"]]]))]

   [:div {:style {:display "block" :margin-top "5%"}} [:input
                                                       {:type "button" :value "Reload!" :style {:margin-right "5%"}  :on-click #(swap! state/patients (get-all-patients))}]
    [:input
     {:type "button" :value "Reset!" :on-click #(reset! state/patients {})}]]])
