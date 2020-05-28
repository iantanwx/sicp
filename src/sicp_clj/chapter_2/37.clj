(ns sicp-clj.chapter-2.37
  (:require [sicp-clj.chapter-2.33 :refer [accumulate]]
            [sicp-clj.chapter-2.36 :refer [accumulate-n]]))

(def m [[1 2 3 4]
        [4 5 6 6]
        [6 7 8 9]])

(defn dot-product
  [v w]
  (accumulate + 0 (map * v w)))

(dot-product [1 2 3 4] [4 5 6 7])
; => 60

(defn matrix-*-vector
  [m v]
  (map #(dot-product %1 v) m))

(matrix-*-vector m [1 2 3 4])

(defn transpose
  [m]
  (accumulate-n #(conj %2 %1) nil m))

(transpose m)

(defn matrix-*-matrix
  [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %1) m)))

(def a [[1 2]
        [3 4]])
(def b [[5 6]
        [7 8]])

(transpose b)
(matrix-*-matrix a b)

