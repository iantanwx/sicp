(ns sicp-clj.chapter-2.8
  (:require [sicp-clj.chapter-2.7 :refer :all]))

(defn sub-interval
  "Subtracts to intervals. Analogous to add-interval."
  [x y]
  (make-interval (- (lower-bound x) (lower-bound y))
                 (- (upper-bound x) (upper-bound y))))

(sub-interval snd fst)
