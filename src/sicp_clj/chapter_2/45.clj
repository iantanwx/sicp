(ns sicp-clj.chapter-2.45
  (:require [sicp-clj.pict-lang.core :refer :all]))

(defn split
  [one two]
  (defn 
    iter [painter n]
    (if (= n 0)
      painter
      (let [smaller (iter painter (dec n))]
        (one painter (two smaller smaller))))))


(def up-split (split below beside))
(def right-split-gen (split beside below))
(paint (right-split-gen will 3))
