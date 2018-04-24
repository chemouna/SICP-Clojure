
(ns sicp.chapter2.generic-operations
  (:require [sicp.chapter2.apply-generic-with-coercion :as ap]
            [clojure.tools.trace :as trace]))

(trace/trace-ns 'sicp.chapter2.generic-operations)

(defn add
  [x y]
  (ap/apply-generic 'add x y))

(defn sub
  [x y]
  (ap/apply-generic 'sub x y))

(defn mul
  [x y]
  (ap/apply-generic 'mul x y))

(defn =zero?
  [x]
  (ap/apply-generic '=zero? x))

(defn equal?
  [x y]
  (ap/apply-generic 'equal? x y))

(defn negate
  [x]
  (ap/apply-generic 'negate x))
