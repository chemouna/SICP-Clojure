
(ns sicp.chapter2.sparse-term-test
  (:use [sicp.chapter2.sparse-term]
        [clojure.test])
  (:require [sicp.chapter2.table :as table]))

(deftest test-sparse-terms-from-coeffs
  ;; test that the result comes back tagged correctly
  (is (= (first
          ((table/gett 'make-from-terms 'sparse-terms)
           '((2 5) (2 3)))))
         'sparse-terms))

;; test from terms implementation
