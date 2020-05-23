(ns sicp-clj.chapter-2.5 
  (:require
    [clojure.math.numeric-tower :refer [expt]]))

(defn cons
  [a b]
  (* (expt 2 a)
     (expt 3 b)))

(defn car
  [z]
  (loop [n z exp 0]
    (if (= (rem n 2) 0) (recur (/ n 2) (inc exp)) exp)))
(defn cdr
  [z]
  (loop [n z exp 0]
    (if (= (rem n 3) 0) (recur (/ n 3) (inc exp)) exp)))

(def one-oh-eight (cons 2 3))
(assert (= (car one-oh-eight) 2))
(assert (= (cdr one-oh-eight) 3))
