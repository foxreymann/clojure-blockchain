(ns clojure-blockchain.api
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [clojure-blockchain.core :as blockchain]))

(defn json-response [data & [status]]
  {:status  (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body    (json/generate-string data)})

(def blockchain (atom (blockchain/empty-blockchain)))

(defn full-chain []
  {:chain (:chain @blockchain)
   :length (count (:chain @blockchain))})

(defroutes app
  (GET "/" [] "<h1>Hello Fox")
  (GET "/chain" [] (json-response (full-chain)))
  (GET "/foxjson" [] (json-response {:name "Fox"}))
  (route/not-found "<h1>Page not found</h1>"))

