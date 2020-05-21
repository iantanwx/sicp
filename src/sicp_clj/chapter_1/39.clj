(ns sicp-clj.chapter-1.39
  (:require [sicp-clj.chapter-1.37 :as ch37]))

(defn tan-cf
  [x k]
  (ch37/cont-frac
   #(- (* 2 %) 1)
   #(if (= % 1) x (* x x))
   k))
(tan-cf 5.0 100)
