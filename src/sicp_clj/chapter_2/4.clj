(ns sicp-clj.chapter-2.4)

(defn cons
  [x y]
  #(% x y))
(defn car
  [z]
  (z (fn [p _] p)))
(defn cdr
  [z]
  (z (fn [_ q] q)))

(def pair (cons 1 2))
(assert (= (car pair) 1))
(assert (= (cdr pair) 2))

