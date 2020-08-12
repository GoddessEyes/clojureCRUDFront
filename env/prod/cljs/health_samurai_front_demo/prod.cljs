(ns health-samurai-front-demo.prod
  (:require
    [health-samurai-front-demo.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
