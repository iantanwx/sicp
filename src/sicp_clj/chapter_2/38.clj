(ns sicp-clj.chapter-2.38
  (:require [sicp-clj.chapter-2.33 :refer [accumulate]]))

(def fold-right accumulate)
(defn fold-left
  [op id coll]
  (defn iter
    [acc tail]
    (if (empty? tail)
      acc
      (iter (op acc (first tail))
            (rest tail))))
  (iter id coll))

(fold-right / 1 [1 2 3])
; = (3 / 2) / 1
(fold-left / 1 [1 2 3])
; = ((1/2) / 3)

(fold-right list '() (list 1 2 3))
(fold-left list '() (list 1 2 3))

; op has to be both commutative and associative
