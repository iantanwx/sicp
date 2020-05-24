(ns sicp-clj.chapter-2.9
  (:require
   [sicp-clj.chapter-2.7 :refer :all]
   [sicp-clj.chapter-2.8 :refer :all]))

(defn interval-width
  "Derives the width an interval"
  [x]
  (/ (- (upper-bound x) (lower-bound x)) 2.0))

(= (interval-width (add-interval fst snd))
   (+ (interval-width fst) (interval-width snd)))
; => true

(= (interval-width (sub-interval fst snd))
   (- (interval-width fst) (interval-width snd)))
; => true

(= (interval-width (mul-interval fst snd))
   (* (interval-width fst) (interval-width snd)))
; => false

(= (interval-width (div-interval fst snd))
   (/ (interval-width fst) (interval-width snd)))
; => false
