(ns sicp-clj.chapter-2.52
  (:require [sicp-clj.pict-lang.core :refer :all]))

(def wave-smile (segment->painter (concat (connect (make-vect 0.40 0.75)
                                                   (make-vect 0.50 0.70)
                                                   (make-vect 0.60 0.75))
                                          (connect (make-vect 0.45 0.85)
                                                   (make-vect 0.46 0.85))
                                          (connect (make-vect 0.54 0.85)
                                                   (make-vect 0.55 0.85))
                                          (connect (make-vect 0.0 0.85)
                                                   (make-vect 0.15 0.6)
                                                   (make-vect 0.3 0.65)
                                                   (make-vect 0.4 0.65)
                                                   (make-vect 0.35 0.85)
                                                   (make-vect 0.4 1.0))
                                          (connect (make-vect 0.6 1.0)
                                                   (make-vect 0.65 0.85)
                                                   (make-vect 0.6 0.65)
                                                   (make-vect 0.75 0.65)
                                                   (make-vect 1.0 0.35))
                                          (connect (make-vect 1.0 0.15)
                                                   (make-vect 0.6 0.45)
                                                   (make-vect 0.75 0.0))
                                          (connect (make-vect 0.6 0.0)
                                                   (make-vect 0.5 0.3)
                                                   (make-vect 0.4 0.0))
                                          (connect (make-vect 0.25 0.0)
                                                   (make-vect 0.35 0.5)
                                                   (make-vect 0.3 0.6)
                                                   (make-vect 0.15 0.4)
                                                   (make-vect 0.0 0.65)))))

(defn corner-split-single
  [painter n]
  (if (= n 0)
    painter
    (let [up (up-split painter (dec n))
          right (right-split painter (dec n))]
      (let [top-left up
            bottom-right right
            corner (corner-split painter (dec n))]
        (beside (below painter top-left)
                (below bottom-right corner))))))

(defn my-square-limit
  [painter n]
  (let [combine4 (square-of-four flip-vert rotate-180
                                 identity flip-horiz)]
    (combine4 (corner-split painter n))))

(paint (my-square-limit will 3))
