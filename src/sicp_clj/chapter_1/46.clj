(ns sicp-clj.chapter-1.46
  (:require
   [clojure.math.numeric-tower :as math]
   [sicp-clj.chapter-1.24 :as ex24]))

(defn iterative-improve
  "Calls f until good-enough? f(x) is true"
  [improve good-enough?]
  #(loop [guess %1]
     (if (good-enough? guess)
       guess
       (recur (improve guess)))))

(def tolerance 0.00001)

(defn sqrt
  [x]
  ((iterative-improve
    #(/ (+ %1 (/ x %1)) 2)
    #(< (math/abs (- (ex24/square %1) x)) tolerance))
   1.0))

(sqrt 4)
; 2.0000000929222947

(defn fixed-point
  [f first-guess]
  ((iterative-improve
    f
    #(< (math/abs (- %1 (f %1))) tolerance))
   first-guess))
; golden ratio
(fixed-point #(+ 1 (/ 1 %)) 1.0)
; 1.6180371352785146
