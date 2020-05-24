(ns sicp-clj.chapter-2.7)

(defn make-interval
  "Creates an 'interval' i.e. a pair consisting of a lower and upper bound"
  [lower upper]
  [lower upper])

(defn lower-bound
  [i]
  (first i))

(defn upper-bound
  [i]
  (second i))

(defn add-interval
  "Adds two intervals by mashing their respective bounds"
  [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval
  "Multiplies two intervals"
  [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4) (max p1 p2 p3 p4))))

(defn div-interval
  "Divides two intervals"
  [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))

(def fst (make-interval 1.5 3.0))
(def snd (make-interval 3.5 5.0))
(add-interval fst snd)
(mul-interval fst snd)
(div-interval fst snd)
