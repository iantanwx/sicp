(ns sicp-clj.chapter-1.31)

(defn product
  [term a next b]
  (loop [ak a result 1]
    (if (> ak b) result
      (recur (next ak) (* result (term ak))))))

(defn product-rec
  [term a next b]
  (if (> a b) 1
    (* (term a) (product-rec term (next a) next b))))

(defn factorial [n]
  (product identity 1 #(+ 1 %1) n))

(defn factorial-rec [n]
  (product-rec identity 1 #(+ 1 %1) n))

(factorial 5)
; => 120

(factorial-rec 5)
; => 120

(defn pi-term [k]
    (if (even? k)
      (/ (+ k 2) (+ k 1))
      (/ (+ k 1) (+ k 2))))

(defn pi [n]
  (float (* 4 (product pi-term 1 #(+ 1 %1) n))))

(defn pi-rec [n]
  (float (* 4 (product-rec pi-term 1 #(+ 1 %1) n))))

(pi 1000)
; => 3.1431608

(pi-rec 1000)
; => 3.1431608
