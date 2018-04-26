
(ns sicp.chapter2.sparse-term-test
  (:use [sicp.chapter2.sparse-term]
        [sicp.chapter2.generic-term]
        [clojure.test])
  (:require [sicp.chapter2.table :as table]))

;; test data
(def t1 (make-term 2 5))
(def t2 (make-term 7 3))

(def adjoin (table/gett 'adjoin-term '(term sparse-terms)))
(def equal? (table/gett 'equal? '(sparse-terms sparse-terms)))

(deftest test-sparse-terms-from-terms
  ;; test that the result comes back tagged correctly
  (is (= (first
          ((table/gett 'make-from-terms 'sparse-terms)
           (list t1 t2)))
         'sparse-terms)))


(deftest test-sparse-terms-from-coeffs
  ;; test that the result comes back tagged correctly
  (is (= (first
          ((table/gett 'make-from-coeffs 'sparse-terms)
           '(2 5)))
         'sparse-terms)))

;; test adjoin-term (privately :))
(deftest test-adjoin-term
  (is (equal? (adjoin t1 '()) (list t1))))

(comment "
(def sparse-to-dense #'sicp.chapter2.sparse-term/sparse-terms->dense-terms)
(deftest test-sparse-terms->dense-terms
  (is (= (sparse-to-dense
          ((table/gett 'make-from-terms 'sparse-terms)
           (list t1 t2)))
         ))
")

