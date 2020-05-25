(ns sicp-clj.chapter-2.23)

(defn for-each
  [f coll]
  (loop [[x & xs] coll]
    (if (nil? x)
      nil
      (do
        (f x)
        (recur xs)))))

(for-each #(println %) [1 2 3 4 5])
