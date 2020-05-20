(ns sicp-clj.chapter-1.30)

(defn sum
  [term a next b]
  (defn iter [a result]
    (if (> a b) result (iter (next a) (+ result (term a)))))
  (iter a 0))

(defn sum-tail-rec
  [term a next b]
  (loop [ak a result 0]
    (if (> ak b) result
      (recur (next ak) (+ result (term ak))))))

(sum identity 1 #(+ 1 %1) 5)
; => 15

; (sum identity 1 #(+ 1 %1) 50000)
; => StackOverflowError

(sum-tail-rec identity 1 #(+ 1 %1) 50000)
; 1250025000

