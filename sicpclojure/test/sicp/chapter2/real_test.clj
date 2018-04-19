
(ns sicp.chapter2.real-test
  (:use sicp.chapter2.real)
  (:use clojure.test)
  (:require [sicp.chapter2.rational :as rat]))

(deftest test-make-real
  (is (= (make-real 2.13) '(real 2.13))))

(deftest test-make-project
  (let [r (project (make-real 1.25))]
    (is (and (= (rat/numer r) 5) (= (rat/denom r) 4)))))

(run-tests)

