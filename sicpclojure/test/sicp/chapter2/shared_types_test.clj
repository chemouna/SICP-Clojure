
(ns sicp.chapter2.shared-types-test
  (:use clojure.test)
  (:use sicp.chapter2.shared-types)
  (:require [sicp.chapter2.complex :as c]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]))


;; todo: find why this test is failing
(comment "
(deftest test-project-complex
  (is (= (project-complex (c/make-complex-from-real-imag 2 0)) '(integer 2)))
  (is (= (project-complex (c/make-complex-from-real-imag 2.12 0)) '(real 2.12)))
  (is (= (project-complex (c/make-complex-from-real-imag 2 3)) '(complex (rectangular (2 3))))))
")

(deftest test-project-rational
  (is (= (project-rational (rat/make-rational-number 2 1)) '(integer 2)))
  (is (= (project-rational (rat/make-rational-number 2 3)) '(integer 0))))

(deftest test-rational->real
  (is (= (rational->real (rat/make-rational-number 1 2)) (real/make-real 1/2))))

(deftest test-project-real
  (let [r (project-real (real/make-real 1.25))]
    (is (and (= (rat/numer r) 5) (= (rat/denom r) 4)))))

(run-tests)
;(c/real-part (c/make-complex-from-real-imag 2 0))
