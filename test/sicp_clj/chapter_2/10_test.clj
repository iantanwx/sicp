(ns sicp-clj.chapter-2.10-test
  (:require [clojure.test :refer :all]
            [sicp-clj.chapter-2.7 :as ex7]
            [sicp-clj.chapter-2.10 :as ex10]))

(deftest test-throws-if-0
  (is (thrown? IllegalArgumentException
               (ex10/div-interval-safe (ex7/make-interval 1.0 3.0)
                                       (ex7/make-interval -5.0 5.0)))))
