
(ns sicp.chapter2.real-test
  (:use sicp.chapter2.real)
  (:use clojure.test)
  (:require [sicp.chapter2.rational :as rat]))

(deftest test-make-real
  (is (= (make-real 2.13) '(real 2.13))))

(run-tests)

