(ns sicp-clj.chapter-2.51
  (:require [sicp-clj.pict-lang.core :refer :all]
            [sicp-clj.chapter-2.49 :refer :all]
            [sicp-clj.chapter-2.50 :refer :all]))

(defn below-rot
  [p1 p2]
  (rotate-90 (beside (rotate-270 p1)
                     (rotate-270 p2))))

(paint (below-rot will will))
