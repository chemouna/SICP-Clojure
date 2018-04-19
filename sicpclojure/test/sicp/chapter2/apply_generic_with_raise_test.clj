(ns sicp.chapter2.apply-generic-with-raise-test
  (:use clojure.test)
  (:use sicp.chapter2.apply-generic-with-raise)
  (:require [sicp.chapter2.rational :as rat]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.complex :as c]))

(deftest test-find-highest-type
  (is (= (find-highest-type '(integer real complex)) 'complex))
  (is (= (find-highest-type '(rational real)) 'real))
  (is (= (find-highest-type '(rational real integer)) 'real))
  (is (= (find-highest-type '()) nil))
  (is (= (find-highest-type '(real)) 'real))
  (is (= (find-highest-type '(integer)) 'integer)))

(deftest test-raise-to
  (is (= (raise-to real rational (rat/make-rational-number 2 5)))))

; raise-values real (rational real integer) ((rational (2 5)) (real 2.5) (integer 5)) ())
; raise-to real rational (rational (2 5)))

(deftest test-apply-generic-with-raise
  (is (= (apply-generic-with-raise
          'addd
          (rat/make-rational-number 2 5)
          (real/make-real 2.5)
          (int/make-integer 5))
         '(real 7.9)))

  (is (= (apply-generic-with-raise
          'addd
          (c/make-complex-from-real-imag 3 5)
          (c/make-complex-from-real-imag 2 3)
          (c/make-complex-from-real-imag 1 4))
         '(complex (rectangular (6 12)))))

  (is (= (apply-generic-with-raise
          'addd
          (c/make-complex-from-real-imag 2 3)
          (real/make-real 2.15)
          (rat/make-rational-number 3 6))
         '(complex (rectangular (4.65 3))))))

(run-tests)
