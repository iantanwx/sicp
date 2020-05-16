(ns sicp-clj.chapter-1.25
  (:require [sicp-clj.chapter-1.16 :as ch16]
            [sicp-clj.chapter-1.24 :as ch24]))

(defn expmod
  [base exp m]
  (rem (ch16/fast-exp-iter base exp) m))

(expmod 2 3 3)

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
; Execution error (ArithmeticException) at sicp-clj.chapter-1.16/fast-exp-helper (16.clj:7).
; integer overflow
; using fast-exp always computes the actual result of a ^ n
; if a or n or both are big, there will be an integer overflow (e.g. 2^100 - 100 bit number!)

(defn timed-prime-test
  [n]
  (if (fast-prime? n 100) (time (fast-prime? n 100))))

(timed-prime-test 1019)

