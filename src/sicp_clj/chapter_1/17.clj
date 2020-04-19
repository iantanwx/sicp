(ns sicp-clj.chapter-1.17)

(defn dbl [x] (* x 2))
(defn halve [x] (/ x 2))
(defn mul [a b]
  (cond (= b 2) (dbl a)
        (= b 3) (+ (dbl a) a)
        (even? b) (+ (dbl a) (mul a (halve b)))
        :else (+ (dbl a) a (mul a (halve (- b 1))))))

(mul 5 5)
