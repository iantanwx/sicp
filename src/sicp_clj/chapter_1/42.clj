(ns sicp-clj.chapter-1.42
  (:require [sicp-clj.chapter-1.24 :as ch24]))

(defn compose
  "Composes f and g"
  [f g]
  #(f (g %1)))

((compose ch24/square inc) 6)
