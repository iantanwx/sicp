(ns sicp-clj.chapter-1.36
  (:import java.lang.Math))

(defn fixed-point
  "Approximates a fixpoint of a function f(x)"
  [f first-guess averager]
  (let [tolerance 0.00001]
    (defn close-enough? [v1 v2]
      (< (Math/abs (- v1 v2)) tolerance))
    (loop [guess first-guess num-steps 1]
      (let [next-guess (averager guess (f guess))]
        (println (format "guess: %s" next-guess))
        (println (format "steps %s" num-steps))
        (if (close-enough? guess next-guess)
          next-guess
          (recur next-guess (inc num-steps)))))))
(fixed-point #(/ (Math/log 1000) (Math/log %)) 2.0 #(identity %2))
; => 34 steps
(fixed-point #(/ (Math/log 1000) (Math/log %)) 2.0 #(/ (+ %1 %2) 2))
; => 9 steps
