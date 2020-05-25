(ns sicp-clj.chapter-2.20)

(defn same-parity
  [x & args]
  (loop [[y & ys] args acc (list x)]
    (cond
      (nil? y) acc
      (= (even? x) (even? y)) (recur ys (concat acc [y]))
      :else (recur ys acc))))
(same-parity 1 2 3 4 5 6 7)
(same-parity  2 3 4 5 6 7)
