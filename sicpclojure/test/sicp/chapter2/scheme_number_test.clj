
(ns sicp.chapter2.scheme-number
  (:use clojure.test))

(deftest test-make-scheme-number
  (is (= (make-scheme-number 2) 2)))

(run-tests)
