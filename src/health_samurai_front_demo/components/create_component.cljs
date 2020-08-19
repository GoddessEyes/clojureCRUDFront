(ns health-samurai-front-demo.components.create-component
  (:require [health-samurai-front-demo.api :refer [create-patient]]
            [fork.reagent :as fork-reagent]
            [reagent.core :as r]))

(defn get-create-patient-input [name type placeholder handle-change handle-blur]
  [:input
   {:name name
    :type type
    :placeholder placeholder
    :on-change handle-change
    :on-blur handle-blur
    :style {:margin-top "2%" :display "block"}}])

(defn create-patient-component []
  [fork-reagent/form {:path :form
                      :form-id "form-id"
                      :prevent-default? true
                      :clean-on-unmount? true
                      :keyword? true
                      :on-submit #(create-patient (:values @(:state %)))}
   (fn [{:keys [form-id
                handle-change
                handle-blur
                submitting?
                handle-submit]}]
     [:form
      {:id form-id
       :on-submit handle-submit
       :style {:margin-top "15%" :display "block"}}

      (get-create-patient-input
       "full_name" "text" "Full Name" handle-change handle-blur)
      (get-create-patient-input
       "date_birth" "date" "Date of birth" handle-change handle-blur)
      (get-create-patient-input
       "address" "text" "Address" handle-change handle-blur)
      (get-create-patient-input
       "gender" "number" "Gender" handle-change handle-blur)
      (get-create-patient-input
       "oms" "number" "OMS" handle-change handle-blur)

      [:button
       {:type "submit"
        :style {:margin-top "5%" :display "block"}
        :disabled submitting?}
       "Create Patient"]])])
