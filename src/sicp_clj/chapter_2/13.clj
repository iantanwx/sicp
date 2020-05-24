(ns sicp-clj.chapter-2.13
  (:require 
    [sicp-clj.chapter-2.7 :refer :all]
    [sicp-clj.chapter-2.12 :refer :all]
    [clojure.math.numeric-tower :as math]))

(def a (make-center-percent 5 0.1))
(def b (make-center-percent 5 0.2))
(def c (mul-interval a b))
(math/abs (percent c))
(def tolerance 0.01)
(assert  (< (math/abs (- (percent c) (+ (percent a) (percent b)))) tolerance))

