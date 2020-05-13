(ns sicp-clj.chapter-1.22
  (:require [sicp-clj.chapter-1.21 :refer :all])
  (:import java.lang.System))

(defn timed-prime-test
  [n]
  (if (prime? n) (time (prime? n))))

(defn next-odd
  [n]
  (if (even? n) (+ n 1) (+ n 2)) )

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
