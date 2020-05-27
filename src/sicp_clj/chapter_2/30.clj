(ns sicp-clj.chapter-2.30)

(def x (list 1
             (list 2 (list 3 4) 5)
             (list 6 7)))

(defn square-tree
  "Traverses a tree squaring each node on the way."
  [t]
  (cond
    (not (coll? t)) (* t t)
    (empty? t) nil
    :else (conj (square-tree (rest t)) (square-tree (first t)))))

(square-tree x)
