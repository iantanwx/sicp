(ns sicp-clj.chapter-1.34
  (:require [sicp-clj.chapter-1.24 :as ch24]))

(defn f [g] (g 2))
(f ch24/square)
(f (fn [z] (* z (+ z 1))))

(f f)
; (f 2)
; (2 2) => this is attempt to apply 2 to an integer, which won't work
; Execution error (ClassCastException) at sicp-clj.chapter-1.34/f (form-init3373528795015691161.clj:4).
; class java.lang.Long cannot be cast to class clojure.lang.IFn (java.lang.Long is in module java.base of loader 'bootstrap'; clojure.lang.IFn is in unnamed module of loader 'app')
