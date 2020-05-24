(ns sicp-clj.chapter-2.10
  (:require [sicp-clj.chapter-2.7 :refer :all]))

(defn interval-span
  [x]
  (- (upper-bound x) (lower-bound x)))

(defn div-interval-safe
  "Safely divides the interval x by y"
  [x y]
  (let [divisor (interval-span y)]
    (if (= 0 divisor) (throw (IllegalArgumentException. "Cannot divide by an interval of span 0"))
        (mul-interval x
                      (make-interval (/ 1.0 (lower-bound y))
                                     (/ 1.0 (upper-bound y)))))))
