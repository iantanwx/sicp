(ns sicp-clj.chapter-2.3
  (:require [sicp-clj.chapter-2.2 :as ex2]
            [clojure.math.numeric-tower :as math]))

(defn make-rectangle
  "Creates a rectangle from two diagonally opposite points represented as a pair (w, h)"
  [a b]
  [(math/abs (- (ex2/x-point a) (ex2/x-point b)))
   (math/abs (- (ex2/y-point a) (ex2/y-point b)))])

(defn width-rectangle
  "Selects the width of a rectangle"
  [rect]
  (first rect))
(defn height-rectangle
  "Selects the height of a rectangle"
  [rect]
  (second rect))

(defn area-rectangle
  "Evaluates the area of a rectangle"
  [rect]
  (* (width-rectangle rect) (height-rectangle rect)))
(defn perimeter-rectangle
  "Evaluates the perimeter of a rectangle"
  [rect]
  (+ (* (width-rectangle rect) 2) (* (height-rectangle rect) 2)))
(def rectangle (make-rectangle (ex2/make-point 0 0) (ex2/make-point 10 5)))
(println (format "Area: %s square units" (area-rectangle rectangle)))
(println (format "Perimeter: %s units" (perimeter-rectangle rectangle)))
