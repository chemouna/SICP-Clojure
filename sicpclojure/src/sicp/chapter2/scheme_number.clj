
(ns sicp.chapter2.scheme-number
  (:require [sicp.chapter2.table :as table]))

(defn- equal?
  "Checks if two numbers are equal"
  [x y]
  (= x y))

; interface to the rest of the system
(table/put 'add '(scheme-number scheme-number)
     (lambda (x y) (+ x y)))

(table/put 'sub '(scheme-number scheme-number)
     (lambda (x y) (- x y)))

(table/put 'mul '(scheme-number scheme-number)
     (lambda (x y) (* x y)))

(table/put 'div '(scheme-number scheme-number)
     (lambda (x y) (/ x y)))

(table/put 'make 'scheme-number (lambda (x) x))

(table/put 'equal? '(scheme-number scheme-number) equal?)

(table/put '=zero? '(scheme-number)
     (lambda (x) (= x 0)))

(table/put 'exp '(scheme-number scheme-number)
     (lambda (x y) (expt x y)))

(defn make-scheme-number
  "Creates a number"
  [n]
  ((get 'make 'scheme-number) n))
