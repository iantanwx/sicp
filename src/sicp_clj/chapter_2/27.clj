(ns sicp-clj.chapter-2.27
  (:require [sicp-clj.chapter-2.18 :refer [reverse]])
  (:refer-clojure :exclude [reverse]))

(def x (list (list 1 2) (list 3 4)))
(reverse x)

(defn deep-reverse
  [x]
  (if (coll? x)
    (-> (map deep-reverse x)
         reverse)
    x))
(deep-reverse x)

