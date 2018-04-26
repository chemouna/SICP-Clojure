
(ns sicp.chapter2.sparse-term-test
  (:use [sicp.chapter2.sparse-term]
        [sicp.chapter2.generic-term]
        [clojure.test])
  (:require [sicp.chapter2.table :as table]))

(deftest test-sparse-terms-from-coeffs
  ;; test that the result comes back tagged correctly
  (is (= (first
          ((table/gett 'make-from-terms 'sparse-terms)
           '((2 5) (2 3)))))
         'sparse-terms))

;; test data
(def t1 (make-term 2 5))
(def adjoin (table/gett 'adjoin-term '(term sparse-terms)))
(def equal? (table/gett 'equal? '(sparse-terms sparse-terms)))

;; test from coeffs implementation


;; lets test adjoin-term (privately :))
(deftest test-adjoin-term
  (is (equal? (adjoin t1 '()) (list t1))))
