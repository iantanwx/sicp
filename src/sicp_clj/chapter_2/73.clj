(ns sicp-clj.chapter-2.73
  (:require [sicp-clj.chapter-2.56 :as d]))

(def lookup-table (atom {}))

(defn put [op typ fn]
  (let [fns (@lookup-table op)]
    (swap! lookup-table assoc op (merge fns {typ fn}))))

(defn get [op typ]
  ((@lookup-table op) typ))

(put :add :polar #(print "hello world polar"))
(put :add :rectangular #(print "hello world rectangular"))
(put '+ :rectangular #(print "hello world rectangular"))
((get :add :polar))
((get :add :rectangular))

;;; new dependencies of the dynamic dispatch system
(defn operator [expr]
  (first expr))
(operator '(+ 1 1))

(defn operands [expr]
  (rest expr))
(operands '(+ 1 1))

(defn deriv
  [expr var]
  (cond
    (number? expr) 0
    (d/variable? expr) (if (d/same-variable? expr var) 1 0)
    :else ((get 'deriv (operator expr)) (operands expr) var)))

;;; install our functions
(defn install-deriv []
  ;; addition procedure
  (letfn [(addend [expr]
            (first expr))
          (augend [expr]
            (second expr))
          (multiplicand [expr]
            (first expr))
          (multiplier [expr]
            (second expr))
          (base [expr]
            (first expr))
          (exponent [expr]
            (second expr))]
    (put 'deriv '+ (fn [operands var] (d/make-sum (deriv (addend operands) var)
                                                  (deriv (augend operands) var))))
    (put 'deriv '* (fn [operands var] (d/make-sum
                                        (d/make-product (multiplier operands)
                                                        (deriv (multiplicand operands) var))
                                        (d/make-product (deriv (multiplier operands) var)
                                                        (multiplicand operands)))))
    (put 'deriv '** (fn [operands var] (d/make-product (d/make-product (exponent operands)
                                                                       (d/make-exponentiation (base operands)
                                                                                              (- (exponent operands) 1)))
                                                       (deriv (base operands) var))))))
(install-deriv)

(deriv '(+ x 3) 'x)
(deriv '(* x 3) 'x)
(deriv '(** x 3) 'x)
