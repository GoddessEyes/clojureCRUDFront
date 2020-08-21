(ns health-samurai-front-demo.api
  (:require [ajax.core :refer [GET DELETE POST PATCH]]
            [health-samurai-front-demo.state :refer [patients patient]]
            [health-samurai-front-demo.config :refer [api-root-url]]))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(defn get-all-patients-handler [response]
  (reset! patients response))

(defn get-patient-handler [response]
  (reset! patient (first (:result response))))

(defn update-patient-by-id [patient-id update-schema]
  (PATCH (str api-root-url "/update_patients/" patient-id)
    {:handler (defn a [x] (prn x))
     :error-handler error-handler
     :params {:full_name (get update-schema "full_name")
              :date_birth (get update-schema "date_birth")
              :gender (js/parseInt (get update-schema "gender"))
              :oms (js/parseInt (get update-schema "oms"))
              :address (get update-schema "address")}
     :response-format :json
     :keywords? true}))

(defn get-all-patients []
  (GET (str api-root-url "/patients")
    {:handler get-all-patients-handler
     :error-handler error-handler
     :response-format :json
     :keywords? true}))

(defn refetch-patients [response]
  (get-all-patients))

(defn get-patient-by-id [patient-id]
  (GET (str api-root-url "/patients/" patient-id)
    {:handler get-patient-handler
     :error-handler error-handler
     :response-format :json
     :keywords? true}))

(defn delete-patient-by-id [id]
  (DELETE (str api-root-url "/delete_patients")
    {:handler refetch-patients
     :params {:id id}
     :error-handler error-handler
     :response-format :json
     :keywords? true}))

(defn create-patient [patient]
  (POST (str api-root-url "/create_patients")
    {:handler refetch-patients
     :params {:full_name (get patient "full_name")
              :date_birth (get patient "date_birth")
              :gender (js/parseInt (get patient "gender"))
              :oms (js/parseInt (get patient "oms"))
              :address (get patient "address")}
     :error-handler error-handler
     :response-format :json
     :keywords? true}))
