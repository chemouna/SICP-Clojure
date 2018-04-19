(ns sicp.chapter2.apply-generic-with-raise-test
  (:use clojure.test)
  (:use sicp.chapter2.apply-generic-with-raise)
  (:require [sicp.chapter2.rational :as rat]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.complex :as c]))

(deftest test-raise-to
  (is (= (raise-to 'real 'rational (rat/make-rational-number 2 5)) (real/make-real 2/5)))
  (is (= (raise-to 'rational 'integer (int/make-integer 2)) (rat/make-rational-number 2 1)))
  (is (= (raise-to 'complex 'integer (int/make-integer 2)) (c/make-complex-from-real-imag 2 0)))
  (is (= (raise-to 'complex 'rational (rat/make-rational-number 2 3)) (c/make-complex-from-real-imag 2/3 0)))
  (is (= (raise-to 'complex 'complex (c/make-complex-from-real-imag 2 3)) (c/make-complex-from-real-imag 2 3)))
  (is (= (raise-to 'integer 'integer (int/make-integer 2)) (int/make-integer 2))))

                                        ; raise-values real (rational real integer) ((rational (2 5)) (real 2.5) (integer 5)) ())
                                        ; raise-to real rational (rational (2 5)))


(run-tests)
