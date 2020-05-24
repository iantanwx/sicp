(ns sicp-clj.chapter-2.10-test
  (:require [clojure.test :refer :all]
            [sicp-clj.chapter-2.7 :refer :all]
            [sicp-clj.chapter-2.10 :refer :all]))

(deftest test-throws-0
  (is (thrown? (div-interval-safe (make-interval 1.0 3.0)
                                  (make-interval 5.0 5.0)))))
