(ns sicp-clj.chapter-2.48
  (:require [sicp-clj.pict-lang.core :refer :all]))

(def frame (make-frame (make-vect 0.0 0.0)
                       (make-vect 200.0 0.0)
                       (make-vect 0.0 200.0)))

(def draw-x (segment->painter [(make-segment (make-vect 0.0 0.0)
                                             (make-vect 1.0 1.0))
                               (make-segment (make-vect 0.0 1.0)
                                             (make-vect 1.0 0.0))]))

(paint draw-x :width 200 :height 200 :frame frame)
