(ns sicp-clj.chapter-1.20)

(defn rem-logged [a b]
  (print "called remainder\n")
  (rem a b))

(defn gcd [a b]
  (if (= b 0) a
    (gcd b (rem-logged a b))))

; verify the answer for applicative order
; should be 4
(gcd 206 40)
