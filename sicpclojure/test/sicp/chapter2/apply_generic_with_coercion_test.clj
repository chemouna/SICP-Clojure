
(ns sicp.chapter2.apply-generic-with-coercion
  (:use sicp.chapter2.apply-generic-with-coercion)
  (:use clojure.test)
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as complex]))

(deftest test-apply-generic
  (is (= (apply-generic 'add
                        (real/make-real 2.1)
                        (real/make-real 1.2))
         '(real 3.3)))
  (is (= (apply-generic 'mul (real/make-real 2.2) (real/make-real 3.4)) '(real 7.48)))
  (is (= (apply-generic 'add (rat/make-rational 1 4) (real/make-real 2.25)) '(real 2.5)))
  (is (= (apply-generic 'add (complex/make-complex-from-real-imag 2 3) (real/make-real 3.12))
         '(complex (rectangular (5.12 5.12))))))

(run-tests)

