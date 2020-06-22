(ns sicp-clj.chapter-2.53)

(defn memq
  [item x]
  (cond (empty? x) false
        (= item (first x)) x
        :else (memq item (rest x))))

(memq 'apple '(pair banana prune))
(memq 'banana '(pair banana prune))

(list 'a 'b 'c)
;;; => (a b c)

(list (list 'george))
;;; => ((george))

(rest '((x1 x2) (y1 y2)))
;;; => ((y1 y2))

(second '((x1 x2) (y1 y2)))
;;; => (y1 y2)

(memq 'red '((red shoes) (blue socks)))
;;; => false

(memq 'red '(red shoes blue socks))
;;; (red shoes blue socks)
