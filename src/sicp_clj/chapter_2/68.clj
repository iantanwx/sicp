(ns sicp-clj.chapter-2.68
  (:require [sicp-clj.chapter-2.67 :refer :all]))

(defn has-symbol?
  [x sym]
  (let [s (symbols x)]
    (some #(= % sym) s)))

(defn encode-symbol
  [sym tree]
  (loop [bits [] subtree tree]
    (let [left (left-branch subtree)
          right (right-branch subtree)
          left-has-symbol (has-symbol? left sym)
          right-has-symbol (has-symbol? right sym)]
      (cond (and (leaf? left) left-has-symbol) (conj bits 0)
            (and (leaf? right) right-has-symbol) (conj bits 1)
            (and (not (leaf? left)) left-has-symbol) (recur (conj bits 0) left)
            (and (not (leaf? right)) right-has-symbol) (recur (conj bits 1) right)
            :else (println "symbol does not exist in tree")))))

(defn encode
  [message tree]
  (if (empty? message)
    []
    (concat (encode-symbol (first message) tree)
            (encode (rest message) tree))))

(encode '[A D A B B C A] sample-tree)
