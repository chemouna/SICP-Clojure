
(ns sicp.chapter2.dense-term-test
  (:use [sicp.chapter2.dense-term]
        [clojure.test])
  (:require [sicp.chapter2.table :as table]))

(deftest test-dense-terms-from-coeffs
  ;; test that the result comes back tagged correctly
  (is (= (first
          ((table/gett 'make-from-coeffs 'dense-terms)
           '(2 3)))
          'dense-terms)))

;; test from terms implementation

;; todo test the coercion from dense to sparse terms



