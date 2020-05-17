(ns sicp-clj.chapter-1.27
  (:require [sicp-clj.chapter-1.24 :as ch24]))

(def carmichael-numbers '(561, 1105, 1729, 2465, 2821, 6601))

(defn prime? [n]
  (loop [a (- n 1)]
    (cond
      (= a 0) true
      (= (ch24/expmod a n n) a) (recur (- a 1))
      :else false)))

(defn all-primes
  [xs]
  (reduce #(and %1 %2) (map #(prime? %1) xs)))

(all-primes carmichael-numbers)
; => true
; Fermat fooled! 1105 is most certainly not a prime number
