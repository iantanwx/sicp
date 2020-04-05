(ns sicp_clj.chapter-1
  (:require [clojure.math.numeric-tower :as math]))

(defn square
  "Square of an integer x"
  [x]
  (* x x))

(defn sum-of-squares
  "Adds square of x and y"
  [x y]
  (+ (square x) (square y)))

(defn f
  [a]
  (sum-of-squares (+ a 1) (* a 2)))

;; Ex 1.3
(defn largest
  [x y z]
  (cond (and (> x y) (> y z)) (sum-of-squares x y)
        (and (> x y) (> z y)) (sum-of-squares x z)
        :else (sum-of-squares y z)))

;; Ex 1.7
(defn good-enough
  [guess x]
  (< (math/abs (- (square guess) x)) 0.001))

(defn avg
  [a b]
  (/ (+ a b) 2))

(defn improve
  [guess x]
  (avg guess (/ x guess)))

(defn sqrt-iter
  [guess x]
  (if (good-enough guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt
  [x]
  (sqrt-iter 1.0 x))

;; this works
;; => 3.000091...
(sqrt 9)

;; this doesn't
;; => 1.0
(sqrt 0.001)
(sqrt 100000)

;; we need a better version
(defn better-sqrt-iter
  [guess old-guess x]
  (if (<= (math/abs (- guess old-guess)) 0.00000001)
    guess
    (better-sqrt-iter (improve guess x) guess x)))

(defn better-sqrt
  [x]
  (better-sqrt-iter 1.0 0.0 x))

(better-sqrt 1)
(better-sqrt 0.001)
(better-sqrt 9)
(better-sqrt 100000)
