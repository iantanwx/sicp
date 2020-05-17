(ns sicp-clj.chapter-1.28
  (:require [sicp-clj.chapter-1.24 :as ch24]))

(defn non-trivial-square?
  "True if x is the non-trivial square root of a number equal to 1 modulo n"
  [x n]
  (cond
    (= x 1) false
    (= x (- n 1)) false
    :else (= (rem (ch24/square x) n) 1)))

(defn expmod
  "Modified expmod that satisfies the miller-rabin test"
  [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (let [x (expmod base (/ exp 2) m)]
                  (if (non-trivial-square? x m) 0 (rem (ch24/square x) m)))
    :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn miller-rabin
  "Implements the miller-rabin test by checking a^n-1 mod n for all a < n"
  [n]
  (loop [a (- n 1)]
    (cond
      (= a 0) true
      (= (expmod a (- n 1) n) 1) (recur (- a 1))
      :else false)))

(miller-rabin 561)
(miller-rabin 1105)
(miller-rabin 1729)
(miller-rabin 2465)
(miller-rabin 2821)
(miller-rabin 6601)

(miller-rabin 3)
(miller-rabin 7)
(miller-rabin 11)
