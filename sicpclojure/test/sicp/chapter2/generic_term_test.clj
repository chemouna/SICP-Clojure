
(ns sicp.chapter2.generic-term-test
  (:use [sicp.chapter2.generic-term]
        [clojure.test])
  (:require [sicp.chapter2.table :as table]))

(deftest test-make-term
  (let [t (make-term 2 3)]
    (is (= (first t) 'term))
    (is (= (order t) 2))
    (is (= (coeff t) 3))))

(deftest test-equal-term
  (is ((table/gett 'equal? '(term term))
       (make-term 2 3)
       (make-term 2 3))))
