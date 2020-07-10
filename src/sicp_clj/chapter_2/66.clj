(ns sicp-clj.chapter-2.66
  (:require [sicp-clj.chapter-2.63 :refer :all]))

(def some-record {:a "record"})
(:a some-record)

(defn key-of
  [record]
  (:key record))

(defn tree-lookup
  "Tries to retrieve a value corresponding to some key by traversing the tree"
  [k records]
  (cond (empty? records) false
        (= k (key-of (entry records))) (entry records)
        (< (key-of (entry records)) k) (tree-lookup k (right-branch records))
        (> (key-of (entry records)) k) (tree-lookup k (left-branch records))))

(def tree-d
  (make-tree {:key 7}
             (make-tree {:key 3}
                        (make-tree {:key 1} empty-tree empty-tree) 
                        (make-tree {:key 5} empty-tree empty-tree))
             (make-tree {:key 9}
                        empty-tree
                        (make-tree {:key 11} empty-tree empty-tree))))

(tree-lookup 5 tree-d)
(tree-lookup 6 tree-d)
(tree-lookup 9 tree-d)

