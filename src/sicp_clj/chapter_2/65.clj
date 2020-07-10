(ns sicp-clj.chapter-2.65
  (:require [sicp-clj.chapter-2.61 :refer :all]
            [sicp-clj.chapter-2.63 :refer :all]
            [sicp-clj.chapter-2.64 :refer :all]))

(defn tree-intersection-set
  [tree1 tree2]
  (let [list1 (tree->list-2 tree1)
        list2 (tree->list-2 tree2)]
    (let [intersection (intersection-set list1 list2)]
      (list->tree intersection))))

(tree-intersection-set (list->tree [1 2 3]) (list->tree [3 4 5]))
