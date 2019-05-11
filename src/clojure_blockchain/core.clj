(ns clojure-blockchain.core)

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

(empty-blockchain)

(defn hash-block [blockchain] nil)

(defn new-block [blockchain proof prev-hash]
  {:index (inc (count (:chain blockchain)))
   :timestamp (System/currentTimeMillis)
   :transaction (:current-transactions blockchain)
   :proof proof
   :prev-hash (or prev-hash (hash-block (last (:chain blockchain))))})

(defn new-transaction [blockchain sender recipient amount]
  (update blockchain :current-transactions conj { :sender sender, :recipient recipient, :amount amount }))

(defn last-block [blockchain] nil)

(new-transaction (empty-blockchain) "A" "B" 2)
