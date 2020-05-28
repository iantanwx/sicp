(ns sicp-clj.chapter-2.39
  (:require [sicp-clj.chapter-2.38 :refer [fold-right fold-left]]))

(defn reverse-right
  [coll]
  (fold-right #(concat %2 [%1]) (empty seq) coll))
(reverse-right [1 2 3])

(defn reverse-left
  [coll]
  (fold-left #(cons %2 %1) (empty seq) coll))
(reverse-left [1 2 3])
