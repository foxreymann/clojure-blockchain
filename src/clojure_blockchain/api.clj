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

(defn mine [] nil)

(defn new-transaction [] nil)

(defn full-chain []
  {:chain (:chain @blockchain)
   :length (count (:chain @blockchain))})

(defroutes app
  (GET "/" [] "<h1>Hello Fox")
  (GET "/chain" [] (json-response (full-chain)))
  (GET "/mine" [] (json-response (mine)))
  (POST "/transactions/new" [] (json-response (new-transaction)))
  (route/not-found "<h1>Page not found</h1>"))

