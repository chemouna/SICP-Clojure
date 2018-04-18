
(ns sicp.chapter2.raise-test
  (:use clojure.test)
  (:use sicp.chapter2.raise)
  (:require [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as complex]
            [sicp.chapter2.integer :as int]))

(deftest test-raise
  (is (= (raise (real/make-real 2.12)) '(complex (rectangular (2.12 0)))))
  (is (= (raise (rat/make-rational-number 2 3)) '(real 2/3)))
  (is (= (raise (complex/make-complex-from-real-imag 2 3)) '(complex (rectangular (2 3)))))
  (is (= (raise (int/make-integer 2)))))

(run-tests)
