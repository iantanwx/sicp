(ns sicp-clj.chapter-2.1 
  (:require
    [clojure.math.numeric-tower :as math]))

(def x [1 2])
(first x)
; equivalent to (car x)
; => 1
(second x)
; equivalent to (cdr x)
; => 2
(def y [3 4])
(def z [x y])

(first (first z))
; equivalent of (car (car z)
; => 1

(first (second z))
; equivalent of (car (cdr z))
; => 3

(defn gcd
  "Gets the GCD of x and y"
  [x y]
  (if (= y 0) x
    (gcd y (rem x y))))

(defn make-rat
  "Takes a numerator and denominator and returns a vector of them"
  [n d]
  (let [g ((if (< d 0) - +) (math/abs (gcd n d)))]
    [(/ n g) (/ d g)]))
(defn numer
  "Evaluates the numerator of the rational number"
  [x]
  (first x))
(defn denom
  "Evaluates the denominator of the rational number"
  [x]
  (second x))
(defn add-rat
  "Adds two rational numbers"
  [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(defn sub-rat
  "Subtracts y from x"
  [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(defn mul-rat
  "Multiplies x and y"
  [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(defn div-rat
  "Divides x by y"
  [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))
(defn equal-rat?
  "Evaluates true if x and y are equal"
  [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defn print-rat
  [x]
  (println (format "%s/%s" (numer x) (denom x))))

(def one-half (make-rat 1 2))
(print-rat one-half)
; => 1 / 2

(def one-third (make-rat 1 3))
(print-rat one-third)
; => 1 / 3

(print-rat (add-rat one-half one-third))
; => 5 / 6

(print-rat (mul-rat one-half one-third))
; => 1 / 6

(print-rat (add-rat one-third one-third))
; => 2 / 3

(print-rat (make-rat -1 3))
(print-rat (make-rat 1 -3))
(print-rat (make-rat -1 -3))
(print-rat (make-rat 1 3))
