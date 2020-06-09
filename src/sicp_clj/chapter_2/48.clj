(ns sicp-clj.chapter-2.48
  (:require [sicp-clj.pict-lang.core :refer :all]))

(def test-line (segment->painter [(make-segment (make-vect 0.0 0.0)
                                                (make-vect 100.0 100.0))]))

(paint test-line)
