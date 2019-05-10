(ns clojure-blockchain.core)

(defn empty-blockchain []
  {:chain []
   :current-transactions []})

(empty-blockchain)

(defn new-block [blockchain] nil)

(defn new-transaction [blockchain] nil)

(defn hash-block [blockchain] nil)

(defn last-block [blockchain] nil)
