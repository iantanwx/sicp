(ns sicp-clj.chapter-1
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

; Exercise 1.8
(defn guess-cube-root
  [x guess]
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn cubert-iter
  [guess prev-guess x]
  (if (<= (math/abs (- guess prev-guess)) 0.00000001)
    guess
    (cubert-iter (guess-cube-root x guess) guess x)))

(defn cube-root
  [x]
  (cubert-iter 1.0, 0.0 x))

(cube-root 8)

; Exercise 1.10
(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (- x 1) (A x (- y 1)))))

(defn f [n] (A 0 n))
; => (* 2 n)
; equates to 2n
(f 2)
; => 4

(defn g [n] (A 1 n))
; => (A 0 (A 1 (- n 1)))
; => (f (g (- n 1)))
; => ... (f ...n-1...(g 2))
; => 2 ^ n
(g 3)
; => 8
(g 10)
; => (A 1 10)
; => 2 ^ 10
; => 1024

(defn h [n] (A 2 n))
; => (A (- 2 1) (A 2 (- n 1))))
; => (A 1 (A 2 (- n 1)))
; => (g (h (- n 1)))
; => (g (g...n-1...(h (- 2 1)))
; => 2 ^ h (n - 1)
(h 3)
; => (g (h 2))
; => (g (g (h 1)))
; => (g (g 2))
; => (g (2 ^ 2))
; => (2 ^ (2 ^ 2))
(h 4)
; => (g (h 3))
; => (g (g (h 2))
; => (g (g (g (h 1))))
; => 2 ^ 2 ^ 2 ^ 2
; => 2 ^ 2 ^ 4
; => 2 ^ 16
; => 65536
(math/expt 2 (math/expt 2 4))

; Exercise 1.11
(defn f-rec [n]
  (if (< n 3)
    n
    (+ (f-rec (- n 1)) (* 2 (f-rec (- n 2))) (* 3 (f-rec (- n 3))))))
(f-rec 5)
(f-rec 4)
(f-rec 3)
(f-rec 2)

(defn f-iter-helper [a b c n]
  (if (= n 3)
    (+ a (* b 2) (* c 3))
    (f-iter-helper (+ a (* b 2) (* c 3)) a b (- n 1))))

(defn f-iter [n]
  (if (< n 3)
    n
    (f-iter-helper 2 1 0 n)))

(f-iter 5)
