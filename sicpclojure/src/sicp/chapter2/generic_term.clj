
(ns sicp.chapter2.generic-term
  (:use [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.apply-generic-with-coercion :as agc])) ;; not sure, maybe better to use _with_raise ?

(defn coeff-term
  [term]
  (second term))

(defn make-term
  [order coeff]
  (list order coeff))

(defn order-term
  [term]
  (first term))

(defn eq-term?
  [t1 t2]
  (and (equal? (order t1) (order t2))
       (equal? (coeff t1) (coeff t2))))

(defn tag
  [t]
  (tag/attach-tag 'term t))

(defn make-term
  [order coeff]
  ((get 'make 'term) order coeff))

(defn order
  [term]
  (agc/apply-generic 'order term))

(defn coeff
  [term]
  (agc/apply-generic 'coeff term))

(table/putt 'order '(term) order-term)

(table/putt 'coeff '(term) coeff-term)

(table/putt 'equal? '(term term) eq-term?)

(table/putt 'make 'term
     #(tag (make-term %1 %2)))
