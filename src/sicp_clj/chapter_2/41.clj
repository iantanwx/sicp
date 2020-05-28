(ns sicp-clj.chapter-2.41
  (:require 
    [sicp-clj.chapter-2.33 :refer [accumulate]]
    [sicp-clj.chapter-2.40 :refer [make-pair-sum flatmap]]))

(defn sum-lt?
  [n triple]
  (< (accumulate + 0 triple) n))

(defn unique-triples
  [n]
  (flatmap (fn [i]
             (flatmap (fn [j]
                    (map (fn [k] (list i j k))
                         (range 1 j))) 
                  (range 1 i)))
           (range 1 (inc n))))
(unique-triples 5)

(defn sum-lt-pairs
  "Returns all pairs from 1 to n whose sum is < the number s"
  [n s]
  (filter (partial sum-lt? s) (unique-triples n)))
(sum-lt-pairs 20 30)
