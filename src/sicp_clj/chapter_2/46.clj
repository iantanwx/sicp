(ns sicp-clj.chapter-2.46
  (:require [sicp-clj.pict-lang.core :refer :all]))

(defn add-vect
  "Vector addition"
  [v1 v2]
  (let [x1 (xcor-vect v1) y1 (ycor-vect v1)
        x2 (xcor-vect v2) y2 (ycor-vect v2)]
    [(+ x1 x2) (+ y1 y2)]))

(defn sub-vect
  "Vector subtraction"
  [v1 v2]
  (let [x1 (xcor-vect v1) y1 (ycor-vect v1)
        x2 (xcor-vect v2) y2 (ycor-vect v2)]
    [(- x1 x2) (- y1 y2)]))

(defn scale-vect
  "scalar * vector"
  [s v]
  (let [x (xcor-vect v)
        y (ycor-vect v)]
    [(* s x) (* s y)]))
