(ns sicp-clj.chapter-2.32)

(defn subsets
  [s]
  (if (empty? s)
    (list s)
    (let [r (subsets (rest s))]
      (concat r (map #(cons (first s) %) r)))))

(subsets (list 1 2 3))
