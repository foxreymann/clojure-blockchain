(ns clojure-blockchain.core)

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

(empty-blockchain)

(defn hash-block [blockchain] nil)

(defn new-block [blockchain proof prev-hash]
  {:index (-> blockchain :chain count inc)
   :timestamp (System/currentTimeMillis)
   :transaction (:current-transactions blockchain)
   :proof proof
   :prev-hash (or prev-hash (hash-block (last-block blockchain)))})

(defn new-transaction [blockchain sender recipient amount]
  (update blockchain :current-transactions conj { :sender sender, :recipient recipient, :amount amount }))

(defn last-block [blockchain] (last (:chain blockchain)))

(new-transaction (empty-blockchain) "A" "B" 2)

(new-block (empty-blockchain) 10003333 "hash-of-genesis-block")
