(ns sicp-clj.chapter-1.16)

(defn square [x] (* x x))

(defn fast-exp-helper [b n a]
  (cond (= n 1) a
        (even? n) (fast-exp-helper b (/ n 2) (* (square b) a))
        :else (fast-exp-helper b (- n 1) (* a b))))

(defn fast-exp-iter [b n]
  (fast-exp-helper b n 1))

(fast-exp-iter 2 4)
