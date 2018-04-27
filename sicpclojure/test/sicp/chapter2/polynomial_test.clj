(ns sicp.chapter2.polynomial-test
  (:use [sicp.chapter2.polynomial]
        [sicp.chapter2.generic-operations]
        [sicp.chapter2.generic-term]
        [clojure.test])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

;; todo : for these tests accept terms in the form of just lists without make-term ctor used

(deftest test-make-poly
  (is (= (make-poly 'x '(2 3))
         '(x (2 3)))
      (= (make-poly 'x '())
         '(x ()))))

(deftest test-variable
  (is (variable? 'x))
  (is (variable? 'unbound))
  (is (false? (variable? 2))))

(deftest test-same-variable
  (is (same-variable? 'x 'x))
  (is (false? (same-variable? 'x 'y))))

(deftest test-make-poly
  (is (= (make-poly 'x '((2 3) (5 6))) '(x ((2 3) (5 6)))))
  (is (= (make-poly 'x '()) '(x ()))))

(deftest test-make-polynomial-from-terms
  (is ()))


(deftest test-make-poly
  (is (equal? (make-polynomial-from-terms 'x '())
              (make-polynomial-from-terms 'x '())))

  (is (equal? (make-polynomial-from-terms 'x '(2 3))
              (make-polynomial-from-terms 'x '(2 3))))

  (is (not (equal? (make-polynomial-from-terms 'x (list (make-term 2 3)))
                   (make-polynomial-from-terms 'x (list (make-term 5 7))))))

  (is (not (equal? (make-polynomial-from-terms 'x (list (make-term 2 3)))
                   (make-polynomial-from-terms 'y (list (make-term 2 3)))))))

(deftest test-zero?
  (is (=zero? (make-polynomial-from-terms 'x '())))
  (is (=zero? (make-polynomial-from-terms 'x (list (make-term 2 (int/make-integer 0))))))
  (is (not (=zero? (make-polynomial-from-terms 'x (list (make-term 2 3))))))
  (is (not (=zero? (make-polynomial-from-terms 'x (list (make-term 4 (int/make-integer 3))
                                                        (make-term 2 (int/make-integer 1))
                                                        (make-term 0 (real/make-real 2.3))))))))
(deftest test-eq-poly?
  (is (equal? (make-polynomial-from-terms 'x '()) (make-polynomial-from-terms 'x '())))

  (is (not (equal? (make-polynomial-from-terms 'x '()) (make-polynomial-from-terms 'y '()))))

  (is (equal? (make-polynomial-from-terms 'x (list (make-term 2 3))) (make-polynomial-from-terms 'x (list (make-term 2 3)))))

  (is (not (equal? (make-polynomial-from-terms 'x (list (make-term 2 3))) (make-polynomial 'x (list (make-term 5 3))))))

  (is (equal? (make-polynomial-from-terms 'x (list (make-term 2 3) (make-term 5 7)))
                (make-polynomial-from-terms 'x (list (make-term 2 3) (make-term 5 7)))))

  (is (equal? (make-polynomial-from-terms 'x (list (make-term 2 -3)))
                (make-polynomial-from-terms 'x (list (make-term 2 -3))))))

(deftest test-negate-poly
  (is (equal?
       (negate
        (make-polynomial-from-terms 'x
                                    (list
                                     (make-term 2 3)
                                     (make-term 5 7))))
       (make-polynomial-from-terms 'x
                                   (list
                                    (make-term 2 -3)
                                    (make-term 5 -7))))))


(deftest test-add-poly
  (is (not (nil? (add (make-polynomial-from-terms 'x (list (make-term 2 3)))
                      (make-polynomial-from-terms 'x (list (make-term 2 7))))))))

(deftest test-sub-poly
  (is (equal?
       (sub
        (make-polynomial-from-terms 'x (list (make-term 3 5) (make-term 7 6)))
        (make-polynomial-from-terms 'x (list (make-term 3 2) (make-term 7 4))))
       (make-polynomial-from-terms 'x (list (make-term 3 3) (make-term 7 2))))))

;(make-poly 'x '(2 4))
;(def mfc #'sicp.chapter2.polynomial/make-from-coeffs)
;(mfc 'x '(2 3))

(def sparse-numerator-1 (make-polynomial-from-coeffs 'x '(5 1)))

(def sparse-denominator-1
  (make-polynomial-from-terms 'x
                              (list (make-term 2 (int/make-integer 1))
                                    (make-term 0 (int/make-integer -1)))))


(deftest test-div-poly
  (is (= (div sparse-numerator-1 sparse-denominator-1)
         '((polynomial (x dense-terms ((integer 0))))
           (polynomial (x sparse-terms ((term (5 (integer 1))) (term (0 (integer -1))))))))))


