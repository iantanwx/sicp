(ns sicp-clj.chapter-1.24
  (:require [clojure.math.numeric-tower :as math]))

; non-proof of Fermat's little theorem
(math/expt 2 3)
(rem 2 3)
(rem 8 3)

(defn square
  [n]
  (* n n))

(square 2)

(defn expmod
  [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
    :else (rem (* base (expmod base (- exp 1) m)) m)))

(expmod 2 3 3)
; => 2

(defn fermat-test
  [n]
  (let [a (+ (rand-int n) 1)]
    (= (expmod a n n) a)))

(fermat-test 3)

(defn fast-prime?
  [n times]
  (cond
    (= times 0) true
    (fermat-test n) (fast-prime? n (- times 1))
    :else false))

(fast-prime? 1000037 10)

(defn timed-prime-test
  [n]
  (if (fast-prime? n 100) (time (fast-prime? n 100))))

(timed-prime-test 1019)
; sicp-clj.chapter-1.24=> (timed-prime-test 1019)
; "Elapsed time: 0.473967 msecs"
; true

(timed-prime-test 1000037)
; sicp-clj.chapter-1.24=> (timed-prime-test 1000037)
; "Elapsed time: 0.722492 msecs"
; true
