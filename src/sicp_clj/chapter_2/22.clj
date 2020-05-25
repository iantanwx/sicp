(ns sicp-clj.chapter-2.22)

(defn square-list
  [items]
  (loop [things items answer '()]
    (if (empty? things)
      answer
      (recur (rest things)
             (cons (* (first things) (first things)) answer)))))

(square-list [1 2 3 4])
; => (16 9 4 1)
; the iteration goes as follows
; (cons (* 1 1) '())
; (cons (* 2 2) '(1))
; (cons (* 3 3) '(4 1))
; (cons (* 4 4) '(9 4 1))
; '(16 9 4 1)

(defn square-list-reverse
  [items]
  (loop [things items answer '()]
    (if (empty? things)
      answer
      (recur (rest things)
             ; in Clojure, the original answer doesn't work.
             ; we have to make the second argument to cons a sequence.
             ; the result is the same: we get a sequence of sequences.
             (cons answer (list (* (first things) (first things))))))))

(square-list-reverse [1 2 3 4])
