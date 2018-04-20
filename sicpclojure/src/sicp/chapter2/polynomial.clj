
(ns sicp.chapter2.polynomial
  (:require [sicp.chapter2.term :as t]))

(defn- variable?
  [v]
  (symbol? v))

(defn- same-variable?
  [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn- make-poly
  [var term-list]
  (conj term-list var))

(defn- variable
  [p]
  (first p))

(defn- term-list
  [p]
  (rest p))

(defn- =zero?
  [p]
  (t/=zero-terms? (term-list p)))

