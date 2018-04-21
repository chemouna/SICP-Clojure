
(ns sicp.chapter2.polynomial-test
  (:use [sicp.chapter2.polynomial]
        [clojure.test])
  (:require [sicp.chapter2.term :as t]))

(deftest test-make-poly
  (is (= (make-poly 'x (list (t/make-term 2 3))) '(x (2 3)))))

(deftest test-term-list
  (is (= (term-list (make-poly 'x (list (t/make-term 2 3)))) '((2 3)))))

(deftest test-zero?
  (is (=zero? (make-poly 'x '())))
  (is (=zero? (make-poly 'x (list (t/make-term 2 0)))))
  (is (not (=zero? (make-poly 'x (list (t/make-term 2 3)))))))

