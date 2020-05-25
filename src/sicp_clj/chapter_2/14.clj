(ns sicp-clj.chapter-2.14
  (:require [sicp-clj.chapter-2.7 :refer :all]
            [sicp-clj.chapter-2.12 :refer :all]))

(defn par1
  [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))
(defn par2
  [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))

(def a (make-center-percent 100 1))
(def b (make-center-percent 100 2))

(par1 a b)
; [47.793103448275865 52.294416243654815]
(par2 b b)
; [49.00000000000001 51.0]

(div-interval a a)
; [0.9801980198019802 1.0202020202020203]
; this should be 1.0 -> we get an approximation.
(div-interval a b)
; [0.9705882352941176 1.030612244897959]
