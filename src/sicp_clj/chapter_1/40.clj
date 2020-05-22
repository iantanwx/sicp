(ns sicp-clj.chapter-1.40
  (:require [sicp-clj.chapter-1.29 :as ch29]
            [sicp-clj.chapter-1.24 :as ch24]
            [sicp-clj.chapter-1.35 :as ch35]))

(def dx 0.00001)

(defn deriv
  "Returns the derivative Dg(x) of the function g(x)"
  [g]
  #(/ (- (g (+ %1 dx)) (g %1)) dx))

((deriv ch29/cube) 5)

(defn newton-transform
  "Given g(x), returns f(x) its fixed point that satisfies g(x) = 0"
  [g]
  #(- %1 (/ (g %1) ((deriv g) %1))))

(defn newtons-method
  "Approximates a a solution for g(x) = 0"
  [g guess]
  (ch35/fixed-point (newton-transform g) guess))

(defn sqrt
  "Approximates the square root of x using Newton's method"
  [x]
  (newtons-method #(- (ch24/square %1) x) 1.0))

(sqrt 4)

(defn fixed-point-of-transform
  "Approximates a solution for g(x) = 0 by transforming g(x) and finding its fixed point"
  [g transform guess]
  (ch35/fixed-point (transform g) guess))

(defn average-damp
  "Average dampens a function"
  [f]
  #(/ (+ %1 (f %1) 2)))

(defn sqrt-fixed-point
  "Approximates the square root of x"
  [x]
  (fixed-point-of-transform #(/ x %1) average-damp 1.0))
(sqrt-fixed-point 4)

(defn cubic
  [a b c]
  #(+ (ch29/cube %1)
      (* a (ch24/square %1))
      (* b %1)
      c))

(newtons-method (cubic 1 2 3) 1.0)
