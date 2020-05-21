(ns sicp-clj.chapter-1.35
  (:require [clojure.math.numeric-tower :as math])
  (:import java.lang.Math))

(defn fixed-point
  "Approximates a fixpoint of a function f(x)"
  [f first-guess]
  (let [tolerance 0.00001]
    (defn close-enough? [v1 v2]
      (< (math/abs (- v1 v2)) tolerance))
    (loop [guess first-guess]
      (let [next-guess (f guess)]
        (if (close-enough? guess next-guess)
          next-guess
          (recur next-guess))))))

(fixed-point #(+ (Math/sin %) (Math/cos %)) 1.0)

(def golden-ratio 1.61803398875)
(assert (< (math/abs (- golden-ratio (+ 1 (/ 1 golden-ratio)))) 0.00001))
(fixed-point #(+ 1 (/ 1 %)) 1.0)
; => 1.6180327868852458
