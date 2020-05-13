(ns sicp-clj.chapter-1.21
  (:require [sicp-clj.chapter-1.16 :as ch16]))

(defn divides?
  [a b]
  (= (mod b a) 0))
(defn find-divisor
  [n test-divisor]
  (cond
    (> (ch16/square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (+ test-divisor 1))))
(defn smallest-divisor
  [n]
  (find-divisor n 2))
(defn prime?
  [n]
  (= n (smallest-divisor n)))

(smallest-divisor 199) ; 199
(smallest-divisor 1999) ; 1999
(smallest-divisor 19999) ; 19999
(prime? 10)
