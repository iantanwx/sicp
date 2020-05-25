(ns sicp-clj.chapter-2.18
  (:refer-clojure :exclude [reverse]))

(defn reverse
  [coll]
  (loop [[x & xs :as all] coll acc '()]
    (if all
      (recur xs (cons x acc))
      acc)))

(reverse [1 2 3 4 5])
