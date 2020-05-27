(ns sicp-clj.chapter-2.35
  (:require [sicp-clj.chapter-2.33 :refer [accumulate]]))

(defn count-leaves
  [t]
  (accumulate #(if (coll? %1)
                 (+ (count-leaves %1) %2)
                 (+ 1 %2))
              0
              t))

(count-leaves [[1 2] [3 4]])
