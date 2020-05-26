(ns sicp-clj.chapter-2.24)

(defn count-leaves
  [x]
  (cond
    (not (coll? x)) 1
    (empty? x) 0
    :else (+ (count-leaves (first x))
             (count-leaves (rest x)))))

(def x [[1 2] [3 4]])
(count-leaves x)
; => 4

(def y (list 1 (list 2 (list 3 4))))
(count-leaves y)
; => 4

