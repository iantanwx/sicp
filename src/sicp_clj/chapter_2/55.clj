(ns sicp-clj.chapter-2.55)

(defn variable?
  [x]
  (symbol? x))

(defn same-variable?
  [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(same-variable? 'x 'x)

(defn make-sum
  [a1 a2]
  (list '+ a1 a2))

(defn make-product
  [m1 m2]
  (list '* m1 m2))

(defn sum?
  [x]
  (and (list? x) (= (first x) '+)))

(defn addend
  [s]
  (second s))

(defn augend
  [s]
  (nth s 2))

(defn product?
  [x]
  (and (list? x) (= (first x) '*)))

(defn multiplier
  [p]
  (second p))

(defn multiplicand
  [p]
  (nth p 2))
