(ns sicp-clj.chapter-2.19)

(def us-coins [50 25 10 5 1])
(def uk-coins [100 50 20 10 5 2 1 0.5])

(defn no-more?
  [coll]
  (empty? coll))

(defn except-first-denomination
  [coll]
  (rest coll))

(defn first-denomination
  [coll]
  (first coll))

(defn cc
  [amount coin-values]
  (cond
    ; gotcha! in Clojure, use == to compare numbers of different categories.
    (== amount 0) 1
    (or (< amount 0) (no-more? coin-values)) 0
    :else (+ (cc amount (except-first-denomination coin-values))
             (cc (- amount (first-denomination coin-values)) coin-values))))

(cc 100 us-coins)
(cc 100 uk-coins)
