(ns sicp-clj.chapter-1.23
  (:require [sicp-clj.chapter-1.16 :as ch16]
            [sicp-clj.chapter-1.21 :as ch21]
            [sicp-clj.chapter-1.22 :as ch22]))
(defn find-next
  [n]
  (if (= n 2) (+ n 1)
      (+ n 2)))
(defn find-divisor
  [n test-divisor]
  (cond
    (> (ch16/square test-divisor) n) n
    (ch21/divides? test-divisor n) test-divisor
    :else (find-divisor n (find-next test-divisor))))
(defn smallest-divisor
  [n]
  (find-divisor n 2))
(defn prime?
  [n]
  (= (smallest-divisor n)))
(defn timed-prime-test
  [n]
  (if (prime? n) (time (prime? n))))
(defn search-for-primes
  [n candidates]
  (let [x (ch22/next-odd n)]
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

; sicp-clj.chapter-1.23=> (search-for-primes 1000, '())
; "Elapsed time: 0.012356 msecs"
; "Elapsed time: 0.009915 msecs"
; "Elapsed time: 0.002577 msecs"
; (1005 1003 1001)
; sicp-clj.chapter-1.23=> (search-for-primes 10000, '())
; "Elapsed time: 0.028945 msecs"
; "Elapsed time: 0.003423 msecs"
; "Elapsed time: 0.002075 msecs"
; (10005 10003 10001)
; sicp-clj.chapter-1.23=> (search-for-primes 100000, '())
; "Elapsed time: 0.012633 msecs"
; "Elapsed time: 0.147882 msecs"
; "Elapsed time: 0.002206 msecs"
; (100005 100003 100001)
; sicp-clj.chapter-1.23=> (search-for-primes 1000000, '())
; "Elapsed time: 0.021902 msecs"
; "Elapsed time: 0.166376 msecs"
; "Elapsed time: 0.00153 msecs"
; (1000005 1000003 1000001)

; conclusion: input halved but the test is still slower
