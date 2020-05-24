(ns sicp-clj.chapter-2.11
  (:require [sicp-clj.chapter-2.7 :refer :all]))

(defn -+?
  [x]
  (and (neg? (lower-bound x))
       (pos? (upper-bound x))))
(defn --?
  [x]
  (and (neg? (lower-bound x))
       (neg? (upper-bound x))))
(defn ++?
  [x]
  (and (pos? (lower-bound x))
       (pos? (upper-bound x))))

(defn mul-interval-fast
  "Over-engineered mul-interval variation"
  [x y]
  (cond
    (and (-+? x) (-+? y)) (make-interval (min (* (lower-bound x) (upper-bound y))
                                              (* (lower-bound y) (upper-bound x)))
                                         (max (* (lower-bound x) (lower-bound y))
                                              (* (upper-bound x) (upper-bound y))))
    (and (-+? x) (--? y)) (make-interval (* (upper-bound x) (lower-bound y))
                                         (* (lower-bound x) (upper-bound y)))
    (and (-+? x) (++? y)) (make-interval (* (lower-bound x) (upper-bound y))
                                         (* (upper-bound x) (upper-bound y)))
    (and (--? x) (-+? y)) (make-interval (* (lower-bound x) (upper-bound y))
                                         (* (lower-bound x) (lower-bound y)))
    (and (--? x) (--? y)) (make-interval (* (upper-bound x) (upper-bound y))
                                         (* (lower-bound x) (lower-bound y)))
    (and (--? x) (++? y)) (make-interval (* (lower-bound x) (lower-bound y))
                                         (* (upper-bound x) (upper-bound y)))
    (and (++? x) (-+? y)) (make-interval (* (upper-bound x) (lower-bound y))
                                         (* (upper-bound x) (upper-bound y)))
    (and (++? x) (--? y)) (make-interval (* (upper-bound x) (lower-bound y))
                                         (* (lower-bound x) (upper-bound y)))
    (and (++? x) (++? y)) (make-interval (* (lower-bound x) (lower-bound y))
                                         (* (upper-bound x) (upper-bound y)))))
(def a (make-interval 2 4))
(def b (make-interval -2 4))
(def c (make-interval -4 -2))
(mul-interval-fast a a)
(mul-interval-fast a b)
(mul-interval-fast a c)
(mul-interval-fast b a)
(mul-interval-fast b b)
(mul-interval-fast b c)
(mul-interval-fast c a)
(mul-interval-fast c b)
(mul-interval-fast c c)
