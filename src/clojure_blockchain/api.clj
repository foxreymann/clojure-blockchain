(ns clojure-blockchain.api
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes app
  (GET "/" [] "<h1>Hello Fox")
  (route/not-found "<h1>Page not found</h1>"))

