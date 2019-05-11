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

(defn is-valid-pow? [s]
  (string/starts-with? s "0000"))

(defn pair [last-pow pow]
  [pow (digest/sha-256 (pr-str (* last-pow pow)))])

(defn proof-of-work [last-pow]
  (first (filter (fn [p] (is-valid-pow? (second p))) (map (fn [pow] (pair last-pow pow))(range)))))

(proof-of-work 5)
