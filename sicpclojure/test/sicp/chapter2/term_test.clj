(ns sicp.chapter2.term-test
  (:use [clojure.test]
        [sicp.chapter2.term]
        [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

(deftest test-make-term-order
  (is (= (order (make-term 2 3)) 2)))

(deftest test-make-term-coeff
  (is (= (coeff (make-term 3 5)) 5)))

(deftest test-adjoin-term
  (is (= (adjoin-term (make-term 2 3) the-empty-termlist) '((2 3))))
  (is (= (adjoin-term (make-term 1 5) '((3 2))) '((1 5) (3 2))))
  (is (= (adjoin-term (make-term 4 6) '((5 4) (7 2))) '((4 6) (5 4) (7 2)))))

(deftest test-add-terms
  (is (= (add-terms '((2 3)) '((3 7)))))
  (is (= (add-terms '() '((5 7))) '((5 7)))))

(deftest test-mul-term-by-all-terms
  (is (= (mul-term-by-all-terms '(2 3) '((3 5) (1 3))) '((5 15) (3 9)))))

(deftest test-mul-terms
  (is (= (mul-terms '((2 3)) '((3 7))) '((5 21))))
  (is (= (mul-terms '() '()))))

(deftest test-negate-term
  (is (eq-term? (negate-term
                 (make-term 2 3))
                (make-term 2 -3)))

  (is (eq-term? (negate-term
                 (make-term 2 (rat/make-rational 3 5)))
                (make-term 2 (rat/make-rational -3 5)))))

(deftest test-negate-terms
  (is (= (negate-terms '()) '()))
  (is (eq-terms? (negate-terms (list
                               (make-term 2 3)))
                (list (make-term 2 -3))))
  (is (eq-terms? (negate-terms (list
                               (make-term 2 3)
                               (make-term 3 5)))
                (list (make-term 2 -3)
                      (make-term 3 -5))))

  (is (eq-terms? (negate-terms
                  (list
                   (make-term 2 (c/make-complex-from-real-imag 3 2))
                   (make-term 4 (int/make-integer 2))))
                 (list
                  (make-term 2 (c/make-complex-from-real-imag -3 2))
                  (make-term 4 -2)))))

(deftest test-eq-terms
  (is (eq-terms?
       (list (make-term 2 3) (make-term 5 7))
       (list (make-term 2 3) (make-term 5 7))))

  (is (not (eq-terms?
            (list (make-term 2 3) (make-term 5 7))
            (list (make-term 5 3) (make-term 9 2)))))

  (is (eq-terms? '() '())))
