(ns sicp-clj.chapter-2.10
  (:require [sicp-clj.chapter-2.7 :refer :all]))

(defn spans-0?
  [x]
  (and (<= (lower-bound x) 0)
       (>= (upper-bound x) 0)))

(defn div-interval-safe
  "Safely divides the interval x by y"
  [x y]
  (if (spans-0? y) (throw (IllegalArgumentException. "Cannot divide by an interval of span 0"))
        (mul-interval x
                      (make-interval (/ 1.0 (lower-bound y))
                                     (/ 1.0 (upper-bound y))))))
