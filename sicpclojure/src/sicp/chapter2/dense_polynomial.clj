
(ns sicp.chapter2.dense-polynomial
  (:use [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.dense-term :as t]))

(defn strip-leading-zeros
  [term-list]
  (cond (t/empty-termlist? term-list) t/the-empty-termlist
        (not (=zero? (t/first-term term-list))) term-list
        :else (strip-leading-zeros (t/rest-terms term-list))))

(defn make-poly
  [variable term-list]
  (conj (strip-leading-zeros term-list) variable))
