(ns sicp-clj.chapter-2.33
  (:refer-clojure :exclude [map]))

(defn accumulate
  "Applies op to each element of coll. Op must be a binary operation."
  [op identity-element coll]
  (if (empty? coll)
    identity-element
    (op (first coll)
        (accumulate op identity-element (rest coll)))))

(accumulate + 0 [1 2 3 4 5])

(defn map
  [p coll]
  (accumulate #(cons (p %1) %2) nil coll))

(map #(* % %) [1 2 3 4 5])

(defn append
  [coll1 coll2] 
  (accumulate cons coll2 coll1))

(append [1 2 3] [4 5])

(defn len
  [coll]
  (accumulate (fn [_ n] (+ 1 n)) 0 coll))

(len [1 2 3 4 5])
