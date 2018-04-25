(ns sicp.chapter2.polynomial-test
  (:use [sicp.chapter2.polynomial]
        [sicp.chapter2.generic-operations]
        [sicp.chapter2.generic-term]
        [clojure.test])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

;; todo: add some test variants with make-polynomial-from-coeffs

(deftest test-make-poly
  (is (equal? (make-polynomial-from-terms 'x '())
              (make-polynomial-from-terms 'x '())))

  (is (equal? (make-polynomial-from-terms 'x (list (make-term 2 3)))
              (make-polynomial-from-terms 'x (list (make-term 2 3)))))

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
                                                        (make-term 0 (real/make-real 2.3)))))))
  (is (=zero? (make-polynomial-from-terms 'x (list (make-term 3 (real/make-real 0))
                                                   (make-term 2 (rat/make-rational-number 0 4))
                                                   (make-term 1 (int/make-integer 0)))))))

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
  (is (equal? (negate
                 (make-polynomial-from-terms 'x (list (make-term 2 3) (make-term 5 7))))
                (make-polynomial-from-terms 'x (list (make-term 2 -3) (make-term 5 -7)))))

  (is (equal? (negate
                 (make-polynomial-from-terms 'x (list (make-term (int/make-integer 3) (rat/make-rational-number 2 5)))))
              (make-polynomial-from-terms 'x (list (make-term (int/make-integer 3) (rat/make-rational-number (- 2) 5)))))))


(deftest test-add-poly
  (is (= (add (make-polynomial-from-terms 'x (list (make-term 2 3)))
              (make-polynomial-from-terms 'y (list (make-term 5 7))))

         (make-polynomial-from-terms )))

  (is (not (nil? (add (make-polynomial-from-terms 'x (list (make-term 2 3)))
                      (make-polynomial-from-terms 'x (list (make-term 2 7))))))))



(deftest test-sub-poly
  (is (equal?
       (sub
        (make-polynomial-from-terms 'x (list (make-term 3 5) (make-term 7 6)))
        (make-polynomial-from-terms 'x (list (make-term 3 2) (make-term 7 4))))
       (make-polynomial-from-terms 'x (list (make-term 3 3) (make-term 7 2))))))


;; div tests

;; tests data
(def zero (int/make-integer 0))

(def sparse-numerator-1
  (make-polynomial-from-terms 'x
                              (list (make-term 5 (int/make-integer 1))
                                    (make-term 0 (int/make-integer -1)))))

(def sparse-denominator-1
  (make-polynomial-from-terms 'x
                              (list (make-term 2 (int/make-integer 1))
                                    (make-term 0 (int/make-integer -1)))))

(def sparse-numerator-2
  (make-polynomial-from-terms 'x
                              (list (make-term 2 (int/make-integer 2))
                                    (make-term 0 (int/make-integer 2)))))

(def sparse-denominator-2
  (make-polynomial-from-terms 'x
                              (list (make-term 2 (int/make-integer 1))
                                    (make-term 0 (int/make-integer 1)))))

(def sparse-numerator-3
  (make-polynomial-from-terms 'x
                              (list (make-term 4 (int/make-integer 3))
                                    (make-term 3 (int/make-integer 7))
                                    (make-term 0 (int/make-integer 6)))))

(def sparse-denominator-3
  (make-polynomial-from-terms 'x
                              (list (make-term 4 (real/make-real 0.5))
                                    (make-term 3 (int/make-integer 1))
                                    (make-term 0 (int/make-integer 3)))))

(def dense-numerator-1
  (make-polynomial-from-coeffs 'x
                               (list (int/make-integer 1)
                                     zero
                                     zero
                                     zero
                                     zero
                                     (int/make-integer -1))))

(def dense-denominator-1
  (make-polynomial-from-coeffs 'x
                               (list (int/make-integer 1)
                                     zero
                                     (int/make-integer -1))))

(def dense-numerator-2
  (make-polynomial-from-coeffs 'x
                               (list (int/make-integer 2)
                                     zero
                                     (int/make-integer 2))))

(def dense-denominator-2
  (make-polynomial-from-coeffs 'x
                               (list (int/make-integer 1)
                                     zero
                                     (int/make-integer 1))))

(def dense-numerator-3
  (make-polynomial-from-coeffs 'x
                               (list (int/make-integer 3)
                                     (int/make-integer 7)
                                     zero
                                     zero
                                     (int/make-integer 6))))

(def dense-denominator-3
  (make-polynomial-from-coeffs 'x
                               (list (real/make-real 0.5)
                                     (int/make-integer 1)
                                     zero
                                     zero
                                     (int/make-integer 3))))

(deftest test-div
  (is (= (div sparse-numerator-1 sparse-denominator-1)
         '((polynomial x sparse-terms (term 3 (integer 1)) (term 1 (integer 1)))
           (polynomial x dense-terms (integer  1) (integer  -1))))))


