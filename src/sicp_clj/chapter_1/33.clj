(ns sicp-clj.chapter-1.33
  (:require
   [sicp-clj.chapter-1.20 :as ch20]
   [sicp-clj.chapter-1.24 :as ch24]
   [sicp-clj.chapter-1.28 :as ch28]))

(defn filtered-acc
  [combiner filter identity-element term a next b]
  (loop [ak a result identity-element]
    (cond
      (> ak b) result
      (filter ak) (recur (next ak) (combiner result (term ak)))
      :else (recur (next ak) (combiner result identity-element)))))

(defn sum-prime-squares
  [a b]
  (filtered-acc + ch28/miller-rabin 0 ch24/square a inc b))

(assert (= (sum-prime-squares 1 5) (+ 1 4 9 25)))

(defn prime-product
  [n]
  (filtered-acc * #(= 1 (ch20/gcd n %1)) 1 identity 1 inc n))
(prime-product 10)
