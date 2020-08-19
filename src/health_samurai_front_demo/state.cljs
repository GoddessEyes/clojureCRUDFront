(ns health-samurai-front-demo.state
  (:require [reagent.core :as r]))

(def patients (r/atom {}))
(def patient (r/atom {}))
