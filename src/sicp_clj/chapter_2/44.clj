(ns sicp-clj.chapter-2.44
  (:require [sicp-clj.pict-lang.core :refer :all]))

(defn up-split
  [painter n]
  (if (= n 0)
    painter
    (let [smaller (up-split painter (dec n))]
      (below painter (beside smaller smaller)))))

(defn corner-split
  [painter n]
  (if (= n 0)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))]
      (let [top-left (beside up up)
            bottom-right (below right right)
            corner (corner-split painter (dec n))]
        (beside (below painter top-left)
                (below bottom-right corner))))))

(paint (corner-split will 2))
