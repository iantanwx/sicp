(ns sicp-clj.chapter-2.6)

(defn zero
  [_]
  #(%))
(defn one
  [f]
  (fn [x]
    (f x)))
(defn two
  [f]
  (fn [x]
    (f (f x))))

(defn add-1
  [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))

(defn add
  [i j]
  (fn [f]
    (fn [x]
      ((i f) ((j f) x)))))

(add one two)
(add-1 zero)
