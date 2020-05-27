(ns sicp-clj.chapter-2.34
  (:require [sicp-clj.chapter-2.33 :refer [accumulate]]))

(defn horner-eval
  [x coefficients]
  (accumulate #(+ %1 (* %2 x))
              0
              coefficients))

(horner-eval 2 [1 3 0 5 0 1])
; => 79
