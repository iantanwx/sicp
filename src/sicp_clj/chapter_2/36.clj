(ns sicp-clj.chapter-2.36
  (:require [sicp-clj.chapter-2.33 :refer [accumulate]]))

(defn accumulate-n
  [op identity-element colls]
  (if (empty? (first colls))
    nil
    (cons (accumulate op identity-element (map first colls))
          (accumulate-n op identity-element (map rest colls)))))

(def s [[1 2 3] [4 5 6] [7 8 9] [10 11 12]])
(accumulate-n + 0 s)
