(ns sicp-clj.chapter-1.41)

(defn dbl
  "Takes a function and applies it to the given argument twice"
  [f]
  #(f (f %1)))

((dbl inc) 1)

(((dbl (dbl dbl)) inc) 5)
; => 21
