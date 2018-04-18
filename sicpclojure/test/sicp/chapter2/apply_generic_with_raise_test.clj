
(ns sicp.chapter2.apply-generic-with-raise-test
  (:use clojure.test)
  (:use sicp.chapter2.apply-generic-with-raise))

(deftest test-find-highest-type
  (is (= (find-highest-type '(integer real complex)) 'complex))
  (is (= (find-highest-type '(rational real)) 'real)))

(run-tests)

