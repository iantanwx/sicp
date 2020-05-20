(ns sicp-clj.chapter-1.29
  (:require [clojure.math.numeric-tower :as math]))

(defn cube [n] (math/expt n 3))

(defn simpson-integration
  [f a b n]
  (let [h (/ (- b a) n)]
    (defn y [k]
      (f (+ a (* k h))))
    (loop [k 1 acc (f a)]
      (cond
        (= k n) (* (/ h 3) (+ acc (y k)))
        (even? k) (recur (+ k 1) (+ acc (* 2 (y k))))
        :else (recur (+ k 1) (+ acc (* 4 (y k))))))))

(simpson-integration cube 0 1 100)
; => 1/4
(simpson-integration cube 0 1 1000)
; => 1/4
