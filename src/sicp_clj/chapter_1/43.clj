(ns sicp-clj.chapter-1.43
  (:require [sicp-clj.chapter-1.24 :as ch24]))

(defn repeated
  "Returns a function that applies f to its argument n times"
  [f n]
  #(loop [i (- n 1) res (f %)]
     (if (= i 0)
       res
       (recur (- i 1) (f res)))))

((repeated ch24/square 2) 5)
