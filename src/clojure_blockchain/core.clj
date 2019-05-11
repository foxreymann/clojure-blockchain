(ns clojure-blockchain.core)

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

(empty-blockchain)

(defn new-block [blockchain] nil)

(defn new-transaction [blockchain sender recipient amount]
  (update blockchain :current-transactions conj { :sender sender, :recipient recipient, :amount amount }))

(defn hash-block [blockchain] nil)

(defn last-block [blockchain] nil)

(new-transaction (empty-blockchain) "A" "B" 2)
