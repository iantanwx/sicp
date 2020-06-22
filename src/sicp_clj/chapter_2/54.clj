(ns sicp-clj.chapter-2.54)

(defn equal?
  [x y]
  (cond (= x y) true
        (and (= (first x) (first y))
             (equal? (rest x) (rest y))) true
        :else false))

(equal? '(this is a list) '(this is a list))
;;; true
(equal? '(this is a list) '(this (is a) list))
;;; false
(equal? '((x1 x2) (y1 y2)) '((x1 x2) (y1 y2)))
;;; true
(equal? '((x1 x2) (y1 y2)) '((x1 x2 x3) (y1 y2)))
;;; false
(equal? 'abc 'abc)
;;; true
