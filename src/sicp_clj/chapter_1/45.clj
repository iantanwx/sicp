(ns sicp-clj.chapter-1.45
  (:require [sicp-clj.chapter-1.35 :as ex35]
            [sicp-clj.chapter-1.40 :as ex40]
            [sicp-clj.chapter-1.43 :as ex43]
            [clojure.math.numeric-tower :as math]))

(defn nth-root
  "Approximates the nth root of x"
  [x n]
  (ex35/fixed-point
   ((ex43/repeated ex40/average-damp 2)
    #(/ x (math/expt %1 (- n 1))))
   1.0))

(nth-root 8 3)
(nth-root 32 5)
