
(ns sicp.chapter2.raise-test
  (:use clojure.test)
  (:use sicp.chapter2.raise)
  (:require [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]
            [sicp.chapter2.integer :as int]))

(deftest test-raise
  (is (= (raise (real/make-real 2.12)) (c/make-complex-from-real-imag 2.12 0)))
  (is (= (raise (rat/make-rational-number 2 3)) (real/make-real 2/3)))
  (is (= (raise (rat/make-rational-number 2 1)) (real/make-real 2)))
  (is (= (raise (int/make-integer 2)))))
