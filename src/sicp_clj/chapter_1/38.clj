(ns sicp-clj.chapter-1.38
  (:require [sicp-clj.chapter-1.37 :as ch37]))

(defn approx-e [k]
  (defn d [i]
    (if (= (mod i 3) 2) (/ (+ i 1) 1.5) 1))
  (+ 2 (ch37/cont-frac d (fn [_] 1.0) k)))
(approx-e 10)
