(ns sicp-clj.chapter-2.57
  (:require
   [sicp-clj.chapter-2.56 :refer :all]))

(defn augend-n
  [[_ & terms]]
  (let [[_ & b] terms]
    (if (= (count b) 1)
      (first b)
      (loop [acc (list '+) augend' b]
        (let [[x & xs] augend']
          (if (empty? xs)
            (concat acc [x])
            (recur (concat acc [x]) xs)))))))

(defn make-sum-n
  [& nums]
  (concat (list '+) nums))

(defn multiplicand-n
  [[_ & terms]]
  (let [[_ & b] terms]
    (if (= (count b) 1)
      (first b)
      (loop [acc (list '*) augend' b]
        (let [[x & xs] augend']
          (if (empty? xs)
            (concat acc [x])
            (recur (concat acc [x]) xs)))))))

(multiplicand-n '(* x y (+ x 3)))

(defn make-product-n
  [& nums]
  (concat (list '*) nums))

(defn deriv-n
  [expr var]
  (cond
    (number? expr) 0
    (variable? expr) (if (same-variable? expr var) 1 0)
    (sum? expr) (make-sum-n (deriv-n (addend expr) var)
                            (deriv-n (augend-n expr) var))
    (product? expr) (make-sum-n
                     (make-product-n (multiplicand-n expr)
                                     (deriv-n (multiplicand-n expr) var))
                     (make-product-n (deriv-n (multiplier expr) var)
                                     (multiplicand-n expr)))
    (exponentiation? expr) (make-product-n
                            (make-product-n (exponent expr)
                                            (make-exponentiation (base expr) (- (exponent expr) 1)))
                            (deriv-n (base expr) var))
    :else (println "Unknown expr: " expr)))

(deriv-n '(* x y (+ x 3)) 'x)
