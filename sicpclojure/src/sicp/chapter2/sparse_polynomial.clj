
(ns sicp.chapter2.sparse-polynomial
  (:require [sicp.chapter2.term :as t]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [clojure.tools.trace :as trace]))

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

(defn negate-poly
  [p]
  (make-poly (variable p) (t/negate-terms (term-list p))))

(defn sub-poly
  [p1 p2]
  (add-poly p1 (negate-poly p2)))

(defn sparse-poly-tag
  [p]
  (tag/attach-tag 'polynomial p))

(defn make-polynomial
  [var terms]
  (tag (make-poly var terms)))

(defn eq-poly?
  [p1 p2]
  (cond
    (not (same-variable? (variable p1) (variable p2))) false
    :else (t/eq-terms? (term-list p1) (term-list p2))))

(table/putt 'add '(polynomial polynomial)
            #(tag (add-poly %1 %2)))

(table/putt 'mul '(polynomial polynomial)
     #(sparse-poly-tag (mul-poly %1 %2)))

(table/putt 'make 'polynomial make-polynomial)

(table/putt '=zero? '(polynomial) =zero?)
(table/putt 'sub '(polynomial polynomial) sub-poly)

(table/putt 'equal? '(polynomial polynomial) eq-poly?)

(table/putt 'negate '(polynomial) negate-poly)

(defn make-polynomial
  [var terms]
  ((table/gett 'make 'polynomial) var terms))


