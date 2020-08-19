(ns health-samurai-front-demo.components.update-component
  (:require [health-samurai-front-demo.api :refer [get-patient-by-id update-patient-by-id]]
            [reagent.dom :refer [render]]
            [fork.reagent :as fork-reagent]
            [health-samurai-front-demo.state :as state]))

(defn get-update-patient-input [name type placeholder handle-change handle-blur]
  [:input
   {:name name
    :type type
    :placeholder placeholder
    :on-change handle-change
    :on-blur handle-blur
    :style {:margin-top "2%" :display "block"}}])

(defn update-patient-component []
  [fork-reagent/form {:path :form
                      :form-id "form-id"
                      :prevent-default? true
                      :clean-on-unmount? true
                      :keyword? true
                      :on-submit #(update-patient-by-id (:id @state/patient) (:values @(:state %)))}
   (fn [{:keys [form-id
                handle-change
                handle-blur
                submitting?
                handle-submit]}]
     [:form
      {:id form-id
       :on-submit handle-submit
       :style {:margin-top "15%" :display "block"}}

      (get-update-patient-input
       "full_name" "text" (:full_name @state/patient) handle-change handle-blur)
      (get-update-patient-input
       "date_birth" "date" (:date_birth @state/patient) handle-change handle-blur)
      (get-update-patient-input
       "address" "text" (:address @state/patient) handle-change handle-blur)
      (get-update-patient-input
       "gender" "number" (:gender @state/patient) handle-change handle-blur)
      (get-update-patient-input
       "oms" "number" (:oms @state/patient) handle-change handle-blur)

      [:button
       {:type "submit"
        :style {:margin-top "5%" :display "block"}
        :disabled submitting?}
       "Save changes"]])])

(defn render-updating-form-and-set-state [patient-id]
  (get-patient-by-id patient-id)
  (render update-patient-component (.-body js/document)))
