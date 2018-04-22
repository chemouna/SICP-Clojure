
(ns sicp.chapter2.rational-test
  (:use clojure.test)
  (:use sicp.chapter2.tag)
  (:use sicp.chapter2.rational)
  (:require [sicp.chapter2.real :as real]))

(deftest test-numer
  (is (= (numer (make-rational-number 2 3)) 2)))

(deftest test-denom
  (is (= (denom (make-rational-number 2 3)) 3)))

(deftest test-add-rational-number
  (is (=
       (add-rat
        '(2 4)
        '(1 4))
       '(3 4))))

(deftest test-negate
  (is (equal? (negate (make-rational-number 2 3)) (make-rational-number -2 3))))
