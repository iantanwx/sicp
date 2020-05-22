(ns sicp-clj.chapter-1.44
  (:require [sicp-clj.chapter-1.43 :as ch43]
            [sicp-clj.chapter-1.24 :as ch24]))

(def dx 0.00001)

(defn smooth
  "Transform f into a smoothed function"
  [f]
  #(/ (+ (f (- %1 dx))
         (f %1)
         (f (+ %1 dx)))
      3))

(defn smooth-n
  "Smooths a given function f n times"
  [f n]
  ((ch43/repeated smooth n) f))

((smooth-n ch24/square 5) 4)
