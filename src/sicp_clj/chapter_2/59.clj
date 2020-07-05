(ns sicp-clj.chapter-2.59)

;;; dependencies
(defn element-of-set?
  [x s]
  (cond
    (or (nil? s) (empty? s)) false
    (= x (first s)) true
    :else (element-of-set? x (rest s))))

(element-of-set? 1 [1 2 3])

(defn adjoin-set
  [x s]
  (if (element-of-set? x s)
    s
    (conj s x)))
(adjoin-set 3 [1 2])

(defn intersection-set
  [set1 set2]
  (cond (or (empty? set1) (empty? set2)) []
        (element-of-set? (first set1) set2) (cons (first set1)
                                                  (intersection-set (rest set1) set2))
        :else (intersection-set (rest set1) set2)))

(intersection-set [1 2 3] [3 4 5])

(defn union-set
  [set1 set2]
  (loop [acc [] s (concat set1 set2)]
    (cond (empty? s) acc
          (element-of-set? (first s) acc) (recur acc (rest s))
          :else (recur (conj acc (first s)) (rest s)))))
(union-set [1 2 3] [3 4 5])
