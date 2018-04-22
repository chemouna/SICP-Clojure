
(ns sicp.chapter2.scheme-number-test
  (:use [clojure.test]
        [sicp.chapter2.scheme-number]))

(deftest test-make-scheme-number
  (is (= (make-scheme-number 2) 2)))

(deftest test-negate
  (is (= (negate (make-scheme-number 2)) -2)))
