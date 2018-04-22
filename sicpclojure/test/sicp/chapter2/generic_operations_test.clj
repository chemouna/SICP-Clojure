
(ns sicp.chapter2.generic-operations-test
  (:use [clojure.test]
        [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

(deftest test-add
  (is (= (add (int/make-integer 2)
              (int/make-integer 3))
         (int/make-integer 5)))

  (is (= (add (rat/make-rational-number 2 3)
              (rat/make-rational-number 11 3))
         (rat/make-rational-number 13 3))))

(deftest test-mul
  (is (= (mul (real/make-real 2/3)
              (real/make-real 3/4))
         (real/make-real 1/2))))

(deftest test-zero?
  (is (=zero? (int/make-integer 0)))
  (is (=zero? (c/make-complex-from-real-imag 0 0))))

(deftest test-negate
  (is (= (negate (real/make-real 2.12)) (real/make-real (- 2.12)))))

;; todo: we will probably need to test with different types
;;        also i need to do the apply generic that works with multiple arg
;;        (the current version gives an error for it)
