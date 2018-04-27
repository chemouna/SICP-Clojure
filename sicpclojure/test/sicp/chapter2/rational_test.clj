
(ns sicp.chapter2.rational-test
  (:use clojure.test)
  (:use sicp.chapter2.tag)
  (:use sicp.chapter2.rational)
  (:require [sicp.chapter2.real :as real]
            [sicp.chapter2.polynomial :as p]))

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


(def p1 (p/make-polynomial 'x '((2 1)(0 1))))
(def p2 (p/make-polynomial 'x '((3 1)(0 1))))
(def rf (make-rational p2 p1))
