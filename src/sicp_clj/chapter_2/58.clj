(ns sicp-clj.chapter-2.58
  (:require
   [sicp-clj.chapter-2.56 :refer :all]
   [clojure.string]))

(defn addend-infix
  [expr]
  (first (take-while #(not (= % '+)) expr)))

(addend-infix '(1 + 2))
(addend-infix '(1 + 2 + 3))

(defn augend-infix
  [expr]
  (let [augend (rest (drop-while #(not (= % '+)) expr))]
    (if (= (count augend) 1)
      (first augend)
      augend)))

(defn make-sum-infix
  "Creates an infix addition expression"
  [& terms]
  (interpose '+ terms))

(defn infix-sum?
  [expr]
  (and (some #(= % '+) expr)
       (not (some #(= % '*) expr))))

(infix-sum? '(1 + 2 * 3))
(infix-sum? '(1 + 2 + 3))

(defn multiplier-infix
  [expr]
  (let [multiplier (take-while #(not (= % '*)) expr)]
    (if (= (count multiplier) 1)
      (first multiplier)
      multiplier)))

(multiplier-infix '(1 + 2 * 3))

(defn multiplicand-infix
  [expr]
  (let [multiplicand (rest (drop-while #(not (= % '*)) expr))]
    (if (= (count multiplicand) 1)
      (first multiplicand)
      multiplicand)))

(multiplicand-infix '(1 + 2 * 3))

(defn make-product-infix
  "Creates an infix multiplication expression"
  [& terms]
  (interpose '* terms))

(defn infix-product?
  [expr]
  (some #(= % '*) expr))
(infix-product? '(1 + 2 * 3))

(defn deriv-infix
  "Differentiates expressions that use mathematical infix notation.
   This version only support + and * operations."
  [expr var]
  (cond
    (number? expr) 0
    (variable? expr) (if (same-variable? expr var) 1 0)
    (infix-sum? expr) (make-sum-infix (deriv-infix (addend-infix expr) var)
                                      (deriv-infix (augend-infix expr) var))
    (infix-product? expr) (make-sum-infix
                           (make-product-infix (multiplicand-infix expr)
                                               (deriv-infix (multiplicand-infix expr) var))
                           (make-product-infix (deriv-infix (multiplier-infix expr) var)
                                               (multiplicand-infix expr)))
    :else (println "Unknown expr: " expr)))

(deriv-infix '(x + 3 * (x + y + 2)) 'x)

