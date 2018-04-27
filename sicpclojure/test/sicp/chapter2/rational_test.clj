
(ns sicp.chapter2.rational-test
  (:use clojure.test)
  (:use sicp.chapter2.tag)
  (:use sicp.chapter2.rational)
  (:require [sicp.chapter2.real :as real]
            [sicp.chapter2.polynomial :as p]
            [sicp.chapter2.generic-term :as t]))

(deftest test-numer
  (is (= (numer (make-rational 2 3)) 2)))

(deftest test-denom
  (is (= (denom (make-rational 2 3)) 3)))

(deftest test-add-rational-number
  (is (=
       (add-rat
        '(2 4)
        '(1 4))
       '(12 16))))

(deftest test-negate
  (is (equal?
       (negate (make-rational 2 3))
       (make-rational -2 3))))
