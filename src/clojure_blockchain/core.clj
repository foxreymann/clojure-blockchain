(ns clojure-blockchain.core
 (:require digest
           [clojure.string :as string]))

(defn last-block [blockchain] (last (:chain blockchain)))

(defn last-block-idx [blockchain] (inc (or (:index (last-block blockchain)) 0)))

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

;(empty-blockchain)

(defn hash-block [block]
  (digest/sha-256 (pr-str block)))

(defn new-block [blockchain proof prev-hash]
  (let [block {:index (-> blockchain :chain count)
               :timestamp (System/currentTimeMillis)
               :transaction (:current-transactions blockchain)
               :proof proof
               :prev-hash (or prev-hash (hash-block (last-block blockchain)))}]
    {:chain (conj (:chain blockchain) block)
     :current-transactions []}))

(defn new-transaction [blockchain sender recipient amount]
  (update blockchain :current-transactions conj { :sender sender, :recipient recipient, :amount amount }))

(defn is-valid-pow? [s]
  (string/starts-with? s "0000"))

(defn pair [last-pow pow]
  [pow (digest/sha-256 (pr-str (* last-pow pow)))])

(defn proof-of-work [last-pow]
  (first (first (filter (fn [p] (is-valid-pow? (second p))) (map (fn [pow] (pair last-pow pow))(range))))))

;(proof-of-work 5)
