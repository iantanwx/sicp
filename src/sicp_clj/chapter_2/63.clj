(ns sicp-clj.chapter-2.63)

(defn entry
  [tree]
  (first tree))

(defn left-branch
  [tree]
  (second tree))

(defn right-branch
  [tree]
  (second (next tree)))

(defn make-tree
  [entry left right]
  [entry left right])

(def empty-tree (empty []))

(defn tree->list-1
  [tree]
  (if (empty? tree)
    empty-tree
    (concat (tree->list-1 (left-branch tree))
            (cons (entry tree)
                  (tree->list-1 (right-branch tree))))))

(defn tree->list-2
  [tree]
  (defn copy-to-list
    [tree result-list]
    (if (empty? tree)
      result-list
      (copy-to-list (left-branch tree)
                    (cons (entry tree)
                          (copy-to-list (right-branch tree)
                                        result-list)))))
  (copy-to-list tree []))

(def tree-a
  (make-tree 7
             (make-tree 3
                        (make-tree 1 empty-tree empty-tree) 
                        (make-tree 5 empty-tree empty-tree))
             (make-tree 9
                        empty-tree
                        (make-tree 11 empty-tree empty-tree))))

(def tree-b
  (make-tree 3
             (make-tree 1 empty-tree empty-tree)
             (make-tree 7
                        (make-tree 5 empty-tree empty-tree)
                        (make-tree 9
                                   empty-tree
                                   (make-tree 11 empty-tree empty-tree)))))

(def tree-c
  (make-tree 5
             (make-tree 3
                        (make-tree 1 empty-tree empty-tree)
                        empty-tree)
             (make-tree 9
                        (make-tree 7 empty-tree empty-tree)
                        (make-tree 11 empty-tree empty-tree))))

;;; both procedures produce the exact same tree in O(n) time
(tree->list-1 tree-a)
(tree->list-2 tree-a)

(tree->list-1 tree-b)
(tree->list-2 tree-b)

(tree->list-1 tree-c)
(tree->list-2 tree-c)

