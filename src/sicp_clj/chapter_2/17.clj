(ns sicp-clj.chapter-2.17
  (:refer-clojure :exclude [last]))

(defn last
  [a]
  (loop [[x & xs] a]
    (if (nil? xs) x (recur xs))))

(last [1 2 3 4 5])
