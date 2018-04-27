
(ns sicp.chapter2.polynomial
  (:use [sicp.chapter2.generic-operations]
        [sicp.chapter2.generic-term])
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [clojure.tools.trace :as trace]))

(trace/trace-ns 'sicp.chapter2.polynomial)

(defn variable?
  [v]
  (symbol? v))

(defn same-variable?
  [v1 v2]
  (and (variable? v1)
       (variable? v2)
       (or (= v1 v2)
           (= v1 'unbound)
           (= v2 'unbound))))

(defn make-poly
  [var term-list]
  ;(conj term-list var))
  (list var term-list))

(defn- make-from-coeffs
  [variable coeffs]
  (make-poly variable
             ((table/gett 'make-from-coeffs 'dense-terms) coeffs)))

(defn- make-from-terms
  [variable terms]
  (make-poly variable
             ((table/gett 'make-from-terms 'sparse-terms) terms)))

(defn- variable
  [p]
  (first p))

(defn- select-variable
  [p1 p2]
  (let [v1 (variable p1)]
    (if (= v1 'unbound)
      (variable p2)
      v1)))

(defn term-list
  [p]
  (rest p))

(defn- =zero-poly?
  [p]
  (=zero? (term-list p)))

(defn- add-poly
  [p1 p2]
  (if (same-variable? (variable p1) (variable p2))
    (make-poly (variable p1)
               (add (term-list p1)
                          (term-list p2)))
    (println "Polys not in same var -- ADD-POLY"
           (list p1 p2))))

(defn- mul-poly
  [p1 p2]
  (if (same-variable? (variable p1) (variable p2))
    (make-poly (variable p1)
               (mul (term-list p1)
                    (term-list p2)))
    (println "Polys not in same var -- MUL-POLY"
           (list p1 p2))))

(defn tag
  [p]
  (tag/attach-tag 'polynomial p))

(defn make-polynomial
  [var terms]
  (tag (make-poly var terms)))

(defn- negate-poly
  [p]
  (make-poly (variable p) (negate (term-list p))))

(defn- sub-poly
  [p1 p2]
  (add-poly p1 (negate-poly p2)))

(defn- eq-poly?
  [p1 p2]
  (cond
    (not (same-variable? (variable p1) (variable p2))) false
    :else (equal? (term-list p1) (term-list p2))))

(defn- div-poly
  [p1 p2]
  (if (same-variable? (variable p1) (variable p2))
    (let [res-terms (div (term-list p1) (term-list p2))]
      (list
       (make-polynomial (variable p1) (first res-terms))
       (make-polynomial (variable p1) (second res-terms))))
    (println "error: Can't do the division: divisor and divident do not have the same variable")))

;(defn polynomial->complex
;  [p]
;  (let [constant (get-constant (term-list p))]
;    (if (is-lower? constant 'complex)
;      (raise-to 'complex constant)
;     constant)))

(table/putt 'add '(polynomial polynomial)
            #(tag (add-poly %1 %2)))

(table/putt 'mul '(polynomial polynomial)
     #(tag (mul-poly %1 %2)))

(table/putt 'make 'polynomial make-polynomial)

(table/putt '=zero? '(polynomial) =zero-poly?)

(table/putt 'sub '(polynomial polynomial)
            #(tag (sub-poly %1 %2)))

(table/putt 'equal? '(polynomial polynomial) eq-poly?)

(table/putt 'negate '(polynomial)
            #(tag (negate-poly %1)))

(table/putt 'div '(polynomial polynomial)
            #(div-poly %1 %2))

(table/putt 'make 'polynomial
            #(tag (make-poly %1 %2)))

(table/putt 'make-from-terms 'polynomial
            #(tag (make-from-terms %1 %2)))

(table/putt 'make-from-coeffs 'polynomial
            #(tag (make-from-coeffs %1 %2)))

;(table/put-coercion 'polynomial 'complex polynomial->complex)

(defn make-polynomial-from-coeffs
  [variable coeffs]
  ((table/gett 'make-from-coeffs 'polynomial) variable coeffs))

(defn make-polynomial-from-terms
  [variable terms]
  ((table/gett 'make-from-terms 'polynomial) variable terms))

(defn make-zero-order-polynomial-from-coeff
  [coeff]
  ((table/gett 'make-from-coeffs 'polynomial) 'unbound (list coeff)))

