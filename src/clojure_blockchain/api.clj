(ns clojure-blockchain.api
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [ring.middleware.json :refer [wrap-json-params]]
            [clojure-blockchain.core :as blockchain]))

(defn json-response [data & [status]]
  {:status  (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body    (json/generate-string data)})

(def blockchain (atom (blockchain/empty-blockchain)))

(defn mine [] nil)

(defn new-transaction [sender recipient amount] (println sender recipient amount))

(defn full-chain []
  {:chain (:chain @blockchain)
   :length (count (:chain @blockchain))})

(defroutes api
  (GET "/" [] "<h1>Hello Fox")
  (GET "/chain" [] (json-response (full-chain)))
  (GET "/mine" [] (json-response (mine)))
  (POST "/transactions/new" request (json-response (new-transaction
                                                     (get-in request [:params "sender"])
                                                     (get-in request [:params "recipient"])
                                                     (get-in request [:params "amount"]))))
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (wrap-json-params api))
