(ns sicp-clj.chapter-1.18)

(defn dbl [x] (* x 2))
(defn halve [x] (/ x 2))
(defn iter [x y a]
  (cond (= 0 y) a
        (even? y) (iter (dbl x) (halve y) a)
        :else (iter x (- y 1) (+ a x))))
(defn mul-iter [a b] (iter a b 0))

(mul-iter 4 5)
