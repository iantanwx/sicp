(ns sicp-clj.chapter-2.56
  (:require
   [clojure.math.numeric-tower :as math]))

(defn variable?
  [x]
  (symbol? x))

(defn same-variable?
  [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number?
  "Determines whether the given expression is a number equal to the given number"
  [expr num]
  (and (number? expr) (= expr num)))

(defn sum?
  [x]
  (and (seq? x) (= (first x) '+)))

(defn addend
  [s]
  (second s))

(defn augend
  [s]
  (nth s 2))

(defn make-sum
  [a1 a2]
  (cond
    (=number? a1 0) a2
    (=number? a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    :else (list '+ a1 a2)))

(defn product?
  [x]
  (and (seq? x) (= (first x) '*)))

(defn multiplier
  [p]
  (second p))

(defn multiplicand
  [p]
  (nth p 2))

(defn make-product
  [m1 m2]
  (cond
    (or (=number? m1 0) (=number? m2 0)) 0
    (=number? m1 1) m2
    (=number? m2 1) m1
    (and (number? m1) (number? m2)) (* m1 m2)
    :else (list '* m1 m2)))

(defn exponentiation?
  [x]
  (and (seq? x) (= (first x) '**)))

(defn base
  [x]
  (second x))

(defn exponent
  [x]
  (nth x 2))

(defn make-expontentiation
  [b e]
  (cond (=number? e 0) 1
        (=number? e 1) b
        (and (number? b) (number? e)) (math/expt b e)
        :else (list '** b e)))

(defn deriv
  [expr var]
  (cond
    (number? expr) 0
    (variable? expr) (if (same-variable? expr var) 1 0)
    (sum? expr) (make-sum (deriv (addend expr) var)
                          (deriv (augend expr) var))
    (product? expr) (make-sum
                     (make-product (multiplicand expr)
                                   (deriv (multiplicand expr) var))
                     (make-product (deriv (multiplier expr) var)
                                   (multiplicand expr)))
    (exponentiation? expr) (make-product
                            (make-product (exponent expr)
                                          (make-expontentiation (base expr) (- (exponent expr) 1)))
                            (deriv (base expr) var))
    :else (throw (AssertionError. (format "Unknown expression: %s" expr)))))

(deriv '(+ x 3) 'x)
(deriv '(** x 3) 'x)
