(ns sicp-clj.chapter-2.28)

(def x (list (list 1 2) (list 3 4)))

(defn fringe
  "collects all leaves into a list"
  [x]
  (cond
    (not (coll? x)) (list x)
    (empty? x) nil
    :else (concat (fringe (first x)) (fringe (rest x)))))

(fringe x)
(fringe (list x x))
