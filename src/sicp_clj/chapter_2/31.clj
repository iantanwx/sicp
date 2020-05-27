(ns sicp-clj.chapter-2.31)

(def x (list 1
             (list 2 (list 3 4) 5)
             (list 6 7)))

(defn tree-map
  "Traverses a tree applying f to each leaf."
  [t f]
  (cond
    (not (coll? t)) (f t)
    (empty? t) nil
    :else (conj (tree-map (rest t) f) (tree-map (first t) f))))

(defn square-tree
  "Implementation of square-tree using tree-map"
  [t]
  (tree-map t #(* % %)))

(square-tree x)
