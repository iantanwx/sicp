(ns sicp-clj.chapter-2.64
  (:require [sicp-clj.chapter-2.63 :refer :all]))

(defn partial-tree
  [elts n]
  (if (= n 0)
    (cons [] elts)
    (let [left-size (quot (- n 1) 2)]
      (let [left-result (partial-tree elts left-size)]
        (let [left-tree (first left-result)
              non-left-elts (rest left-result)
              right-size (- n (+ left-size 1))]
          (println non-left-elts)
          (println left-size)
          (println right-size)
          (let [this-entry (first non-left-elts)
                right-result (partial-tree (rest non-left-elts)
                                           right-size)]
            (let [right-tree (first right-result)
                  remaining-elts (rest right-result)]
              (cons (make-tree this-entry left-tree right-tree) remaining-elts))))))))

(defn list->tree
  [elements]
  (first (partial-tree elements (count elements))))

(list->tree [1 3 5 7 9 11])
