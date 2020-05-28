(ns sicp-clj.chapter-2.40
  (:require
   [sicp-clj.chapter-1.27 :refer [prime?]]
   [sicp-clj.chapter-2.33 :refer [accumulate]]))

(defn flatmap
  [proc coll]
  (accumulate concat (empty list) (map proc coll)))

(defn unique-pairs
  "Generates a range of ordered pairs from 1 to n"
  [n]
  (flatmap (fn [i]
             (map (fn [j] (list i j))
                  (range 1 i)))
           (range 1 (inc n))))
(unique-pairs 6)

(defn prime-sum?
  [[a b & _]]
  (prime? (+ a b)))
(prime-sum? [1 2])

(defn make-pair-sum
  [[a b & _]]
  (list a b (+ a b)))
(make-pair-sum [1 2])

(defn prime-sum-pairs
  [n]
  (filter prime-sum? (unique-pairs n)))
(prime-sum-pairs 6)

(defn remove
  [item coll]
  (filter #(not (= %1 item)) coll))

(defn permutations
  [s]
  (if (empty? s)
    (list nil) ; use (list nil) so that map will at least go through a single iteration
    (flatmap (fn [x]
               (map (fn [p] (cons x p))
                    (permutations (remove x s))))
             s)))

(permutations [1 2 3])
