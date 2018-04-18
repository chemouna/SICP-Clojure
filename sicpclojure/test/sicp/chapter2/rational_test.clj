
(ns sicp.chapter2.rational-test
  (:use clojure.test)
  (:use sicp.chapter2.tag)
  (:use sicp.chapter2.rational))

(deftest test-numer
  (is (= (numer (contents (make-rational-number 2 3))) 2)))

(deftest test-denom
  (is (= (denom (contents (make-rational-number 2 3))) 3)))

(deftest test-add-rational-number
  (is (=
       (add-rat
        '(2 4)
        '(1 4))
       '(3 4))))


(run-tests)
