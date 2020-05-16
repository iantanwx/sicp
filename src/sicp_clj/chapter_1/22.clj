(ns sicp-clj.chapter-1.22
  (:require [sicp-clj.chapter-1.21 :refer :all])
  (:import java.lang.System))

(defn timed-prime-test
  [n]
  (if (prime? n) (time (prime? n))))

(defn next-odd
  [n]
  (if (even? n) (+ n 1) (+ n 2)))

(defn search-for-primes
  [n candidates]
  (let [x (next-odd n)]
    (cond
      (= (count candidates) 3) candidates
      (even? n) (search-for-primes x candidates)
      :else (if (timed-prime-test n)
              (search-for-primes x (conj candidates n))
              (search-for-primes x candidates)))))

(search-for-primes 1000, '())
(search-for-primes 10000, '())
(search-for-primes 100000, '())
(search-for-primes 1000000, '())

; sicp-clj.chapter-1.22=> (search-for-primes 1000, '())
; "Elapsed time: 0.011769 msecs"
; "Elapsed time: 0.007162 msecs"
; "Elapsed time: 0.007187 msecs"
; (1019 1013 1009)
; sicp-clj.chapter-1.22=> (search-for-primes 10000, '())
; "Elapsed time: 0.022055 msecs"
; "Elapsed time: 0.020069 msecs"
; "Elapsed time: 0.03408 msecs"
; (10037 10009 10007)
; sicp-clj.chapter-1.22=> (search-for-primes 100000, '())
; "Elapsed time: 0.07262 msecs"
; "Elapsed time: 0.073901 msecs"
; "Elapsed time: 0.083722 msecs"
; (100043 100019 100003)
; sicp-clj.chapter-1.22=> (search-for-primes 1000000, '())
; "Elapsed time: 0.300471 msecs"
; "Elapsed time: 0.262487 msecs"
; "Elapsed time: 0.257447 msecs"
; (1000037 1000033 1000003)
