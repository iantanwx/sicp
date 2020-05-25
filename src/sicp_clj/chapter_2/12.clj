(ns sicp-clj.chapter-2.12
  (:require [sicp-clj.chapter-2.7 :refer :all]
            [clojure.math.numeric-tower :as math]))

(defn make-center-width
  "Makes an interval with tolerance based on a single number"
  [c w]
  (make-interval (- c w) (+ c w)))

(defn make-center-percent
  "Creates an interval given a center and a percentage tolerance > 0.0 < 1.0"
  [c p]
  (make-interval (- c (math/abs (* c (/ p 100))))
                 (+ c (math/abs (* c (/ p 100))))))

(defn center
  "Gets the originally provided center based on absolute interval"
  [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width
  "Gets the width (relative to the center) of the given interval"
  [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(defn percent
  "Gets the percentage tolerance relative to the center"
  [i]
  (let [w (width i) c (center i)]
    (math/abs (/ w c))))

(def x (make-center-percent 5 10))
(def y (make-center-percent -5 10))
(percent x)
(percent y)
