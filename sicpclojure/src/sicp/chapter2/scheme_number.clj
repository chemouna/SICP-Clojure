
(ns sicp.chapter2.scheme-number
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.common :as cm]
            [sicp.chapter2.rational :as rat]))

(defn- equal?
  "Checks if two numbers are equal"
  [x y]
  (= x y))

(defn negate
  [x]
  (- x))

; interface to the rest of the system
(table/putt 'add '(scheme-number scheme-number)
     #(+ %1 %2))

(table/putt 'sub '(scheme-number scheme-number)
     #(- %1 %2))

(table/putt 'mul '(scheme-number scheme-number)
     #(* %1 %2))

(table/putt 'div '(scheme-number scheme-number)
     #(/ %1 %2))

(table/putt 'make 'scheme-number identity)

(table/putt 'equal? '(scheme-number scheme-number) equal?)

(table/putt '=zero? '(scheme-number)
      #(= %1 0))

(table/putt 'exp '(scheme-number scheme-number)
     #(cm/expt %1 %2))

(table/putt 'negate '(scheme-number) negate)

(table/putt 'div '(scheme-number scheme-number)
            #(rat/make-rational-number %1 %2))

(defn make-scheme-number
  "Creates a number"
  [n]
  ((table/gett 'make 'scheme-number) n))
