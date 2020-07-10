(ns sicp-clj.chapter-2.67)

(defn make-leaf
  [symbol weight]
  ['leaf symbol weight])

(defn leaf?
  [obj]
  (= (first obj) 'leaf))

(defn symbol-leaf
  [x]
  (second x))

(defn weight-leaf
  [x]
  (second (next x)))

(defn left-branch
  [[left]]
  left)

(defn right-branch
  [[_ right]]
  right)

(defn symbols
  [tree]
  (if (leaf? tree)
    [(symbol-leaf tree)]
    (nth tree 2)))

(defn weight
  [tree]
  (if (leaf? tree)
    (weight-leaf tree)
    (nth tree 3)))

(defn make-code-tree
  [left right]
  [left
   right
   (concat (symbols left) (symbols right))
   (+ (weight left) (weight right))])

(defn choose-branch
  [bit tree]
  (cond (= bit 0) (left-branch tree)
        (= bit 1) (right-branch tree)
        :else (printf "wtf this isn't a bit %s" bit)))

(defn decode
  [bits tree]
  (defn decode-1
    [bits current-branch]
    (if (empty? bits)
      []
      (let [next-branch (choose-branch (first bits) current-branch)]
        (if (leaf? next-branch)
          (cons (symbol-leaf next-branch)
                (decode-1 (rest bits) tree))
          (decode-1 (rest bits) next-branch)))))
  (decode-1 bits tree))

(defn adjoin-set
  [x s]
  (cond (empty? s) [x]
        (< (weight x) (weight (first s))) (cons x s)
        :else (cons (first s)
                    (adjoin-set x (rest s)))))

(defn make-leaf-set
  [pairs]
  (if (empty? pairs)
    []
    (let [[sym wt] (first pairs)]
      (adjoin-set (make-leaf sym wt)
                  (make-leaf-set (rest pairs))))))

(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree (make-leaf 'B 2)
                                  (make-code-tree (make-leaf 'D 1)
                                                  (make-leaf 'C 1)))))

(def sample-message [0 1 1 0 0 1 0 1 0 1 1 1 0])

(decode sample-message sample-tree)
