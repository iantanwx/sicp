(ns sicp-clj.chapter-2.60
  (:require
    [sicp-clj.chapter-2.59 :refer :all]))

;;; element-of-multiset? and intersection-multiset would be identical in time complexity.
;;; for that reason they are not implemented here.
;;; however, adjoin-multiset and union-multiset are constant-time operations.
;;; therefore, it would make sense to allow multisets if a majority of operations
;;; are adjoin and/or union.

(defn adjoin-multiset
  [x s]
  (conj s x))

(defn union-multiset
  [set1 set2]
  (concat set1 set2))

