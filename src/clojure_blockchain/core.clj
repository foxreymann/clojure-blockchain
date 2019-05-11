(ns clojure-blockchain.core
 (:require digest
           [clojure.string :as string]))

(defn last-block [blockchain] (last (:chain blockchain)))

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

(empty-blockchain)

(defn hash-block [block]
  (digest/sha-256 (pr-str block)))

(defn new-block [blockchain proof prev-hash]
  {:index (-> blockchain :chain count inc)
   :timestamp (System/currentTimeMillis)
   :transaction (:current-transactions blockchain)
   :proof proof
   :prev-hash (or prev-hash (hash-block (last-block blockchain)))})

(defn new-transaction [blockchain sender recipient amount]
  (update blockchain :current-transactions conj { :sender sender, :recipient recipient, :amount amount }))

(new-transaction (empty-blockchain) "A" "B" 2)

(hash-block (new-block (empty-blockchain) 10003333 "hash-of-genesis-block"))

(defn ends-with-zero? [s]
  (string/ends-with? s "0"))

(defn pair [x y]
  [y (digest/sha-256 (pr-str (* x y)))])

(defn toy [x]
  (first (filter (fn [p] (ends-with-zero? (second p))) (map (fn [y] (pair x y))(range)))))

(toy 5)
