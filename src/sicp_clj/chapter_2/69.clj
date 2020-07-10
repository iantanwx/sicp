(ns sicp-clj.chapter-2.69
  (:require
   [sicp-clj.chapter-2.67 :refer :all]
   [sicp-clj.chapter-2.68 :refer :all]))

(defn successive-merge
  [pairs]
  (loop [elems pairs]
    (if (= (count elems) 1)
      (first elems)
      (let [fst (first elems)
            snd (second elems)
            merged-elem (make-code-tree fst snd)]
        (recur (adjoin-set merged-elem (drop 2 elems)))))))

(defn generate-huffman-tree
  [pairs]
  (successive-merge (make-leaf-set pairs)))

(def symbol-set '[[A, 4] [B, 2] [C, 1] [D, 1]])

(def generated-sample-tree (generate-huffman-tree symbol-set))

(decode sample-message generated-sample-tree)
