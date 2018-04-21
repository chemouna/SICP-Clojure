
(ns sicp.chapter2.polynomial
  (:require [sicp.chapter2.term :as t]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [clojure.tools.trace :as trace]))

(trace/trace-ns 'sicp.chapter2.polynomial)

(defn- variable?
  [v]
  (symbol? v))

(defn- same-variable?
  [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn make-poly
  [var term-list]
  (conj term-list var))

(defn variable
  [p]
  (first p))

(defn term-list
  [p]
  (rest p))

(defn =zero?
  [p]
  (t/=zero-terms? (term-list p)))

(defn add-poly
  [p1 p2]
  (if (same-variable? (variable p1) (variable p2))
    (make-poly (variable p1)
               (t/add-terms (term-list p1)
                          (term-list p2)))
    (println "Polys not in same var -- ADD-POLY"
           (list p1 p2))))

(defn mul-poly
  [p1 p2]
  (if (same-variable? (variable p1) (variable p2))
    (make-poly (variable p1)
               (t/mul-terms (term-list p1)
                          (term-list p2)))
    (println "Polys not in same var -- MUL-POLY"
           (list p1 p2))))

(defn tag
  [p]
  (tag/attach-tag 'polynomial p))

(defn make-polynomial
  [var terms]
  (tag (make-poly var terms)))

(table/putt 'add '(polynomial polynomial)
            #(tag (add-poly %1 %2)))

(table/putt 'mul '(polynomial polynomial)
     #(tag (mul-poly %1 %2)))

(table/putt 'make 'polynomial make-polynomial)

(table/putt '=zero? '(polynomial) =zero?)

(defn make-polynomial
  [var terms]
  ((table/gett 'make 'polynomial) var terms))

