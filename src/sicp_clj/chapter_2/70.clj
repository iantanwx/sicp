(ns sicp-clj.chapter-2.70
  (:require [sicp-clj.chapter-2.67 :refer :all]
            [sicp-clj.chapter-2.68 :refer :all]
            [sicp-clj.chapter-2.69 :refer :all]))

(def song-alphabet '[[A, 2] [NA, 16]
                     [BOOM 1] [SHA 3]
                     [GET 2] [YIP 9]
                     [JOB 2] [WAH 1]])

(def song-tree (generate-huffman-tree song-alphabet))

(def song-lyrics '[GET A JOB
                   SHA NA NA NA NA NA NA NA NA
                   GET A JOB
                   SHA NA NA NA NA NA NA NA NA
                   WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP
                   SHA BOOM])

(def song-bits (encode song-lyrics song-tree))

(count song-bits)
;;; => 84

(count song-lyrics)
;;; => 36

;;; Since the alphabet has eight symbols, we would need 3 bits (2^3 combinations) 
;;; to represent each of them.
;;; Therefore, we would need, at minimum, (* 3 36) / 108 bits to represent the lyrics.
(* 36 3)
