(ns sicp-clj.chapter-1.32)

(defn accumulate
  "A iterative generalisation of a foldable operation"
  [combiner identity-element term a next b]
  (loop [ak a result identity-element]
    (if (> ak b) result
        (recur (next ak) (combiner result (term ak))))))

(defn accumulate-rec
  "A recursive generalisation of a foldable operation"
  [combiner identity-element term a next b]
  (if (> a b) identity-element
      (combiner
       (term a)
       (accumulate-rec combiner identity-element term (next a) next b))))

(defn sum
  [term a next b]
  (accumulate + 0 term a next b))
(sum identity 1 #(+ 1 %1) 5)

(defn sum-rec
  [term a next b]
  (accumulate-rec + 0 term a next b))
(sum-rec identity 1 #(+ 1 %1) 5)

(defn product
  [term a next b]
  (accumulate * 1 term a next b))
(defn factorial [n]
  (product identity 1 inc n))
(factorial 5)

(defn product-rec
  [term a next b]
  (accumulate-rec * 1 term a next b))
(defn factorial-rec [n]
  (product-rec identity 1 inc n))
(factorial-rec 5)
