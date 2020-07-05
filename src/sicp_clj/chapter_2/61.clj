(ns sicp-clj.chapter-2.61)

(defn element-of-set?
  [x s]
  (cond (empty? s) false
        (= x (first s)) true
        (< x (first s)) false
        :else (element-of-set? x (rest s))))

(element-of-set? 5 [1 2 3 4])

(defn intersection-set
  [set1 set2]
  (if (or (empty? set1) (empty? set2))
    []
    (let [x1 (first set1)
          x2 (first set2)]
      (cond (= x1 x2) (cons x1 (intersection-set (rest set1)
                                                 (rest set2)))
            (< x1 x2) (intersection-set (rest set1) set2)
            (< x2 x1) (intersection-set set1 (rest set2))))))

(intersection-set [1 2 3] [3 4 5])
(intersection-set [1 2 3] [2 3 4])

;;; this implementation is similar to element-of-set?
;;; since we rely on order to avoid searching the entire set for x before deciding to append it.
(defn adjoin-set
  [x s]
  (let [fst (first s)]
    (cond (empty? s) [x]
          (= x fst) s
          (< x fst) (cons x s)
          :else (cons fst (adjoin-set x (rest s))))))

(adjoin-set 3 [1 2 4 5])
(adjoin-set 3 [1 2])
(adjoin-set 3 [4 5])
