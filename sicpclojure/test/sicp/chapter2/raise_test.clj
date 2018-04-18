
(ns sicp.chapter2.raise-test
  (:use clojure.test)
  (:use sicp.chapter2.raise)
  (:require [sicp.chapter2.real :as real]))

(deftest test-raise
  (is (= (raise (real/make-real 2.12)) '(complex (rectangular (2.12 0))))))

(run-tests)
