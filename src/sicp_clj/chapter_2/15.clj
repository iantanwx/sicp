(ns sicp-clj.chapter-2.15
  (:require 
    [sicp-clj.chapter-2.7 :refer :all]
    [sicp-clj.chapter-2.12 :refer :all]
    [sicp-clj.chapter-2.14 :refer :all]))

; Dividing "uncertain" i.e. width > 0 intervals always gives us approximations
(div-interval a a)
; [0.9801980198019802 1.0202020202020203]

; But one does not give an approximation as there are no rounding errors to account for
(div-interval one one)

; since par1 involves more "uncertain" intervals and their division thereof, Lem must be right.
