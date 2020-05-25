(ns sicp-clj.chapter-2.21)

(defn square-list-recursive
  [items]
  (if (empty? items)
    nil
    (cons (* (first items) (first items)) (square-list-recursive (rest items)))))

(defn square-list
  [items]
  (map #(* % %) items))

(square-list-recursive [1 2 3 4])
(square-list [1 2 3 4])
