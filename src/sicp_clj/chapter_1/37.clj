(ns sicp-clj.chapter-1.37)

(defn cont-frac
  [d n k]
  (loop [i (- k 1) denom (+ (d (- k 1)) (/ (n k) (d k)))]
    (if (= i 0) (/ (n 1) denom)
        (recur (dec i) (+ (d (- i 1)) (/ (n i) denom))))))

(defn cont-frac-rec
  [d n k]
  (defn denom
    [i]
    (if (= i k) (+ (d (dec i)) (/ (n i) (d i)))
        (+ (d (dec i)) (/ (n 1) (denom (inc i))))))
  (/ (n 1) (denom 1)))

(def golden-ratio 1.61803398875)
(/ 1 golden-ratio)
; => 0.6180339887498547
(cont-frac (fn [_] 1.0) (fn [_] 1.0) 9)
(cont-frac-rec (fn [_] 1.0) (fn [_] 1.0) 9)
; => 0.6179775280898876
(cont-frac (fn [_] 1.0) (fn [_] 1.0) 10)
(cont-frac-rec (fn [_] 1.0) (fn [_] 1.0) 10)
; => 0.6180555555555556

