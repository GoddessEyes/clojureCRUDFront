(ns health-samurai-front-demo.api
  (:require [ajax.core :refer [GET]]
            [health-samurai-front-demo.state :refer [patients]]))

(defn handler [response]
  (reset! patients response))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "something bad happened: " status " " status-text)))

(defn get-all-patients []
  (GET "http://localhost:3000/patients"
    {:handler handler
     :error-handler error-handler
     :response-format :json
     :keywords? true}))
