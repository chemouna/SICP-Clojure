
(ns sicp.chapter2.scheme-number
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.common :as cm]))

(defn- equal?
  "Checks if two numbers are equal"
  [x y]
  (= x y))

; interface to the rest of the system
(table/putt 'add '(scheme-number scheme-number)
     #(+ %1 %2))

(table/putt 'sub '(scheme-number scheme-number)
     #(- %1 %2))

(table/putt 'mul '(scheme-number scheme-number)
     #(* %1 %2))

(table/putt 'div '(scheme-number scheme-number)
     #(/ %1 %2))

(table/putt 'make 'scheme-number  #(%1))

(table/putt 'equal? '(scheme-number scheme-number) equal?)

(table/putt '=zero? '(scheme-number)
      #(= %1 0))

(table/putt 'exp '(scheme-number scheme-number)
     #(cm/expt %1 %2))

(defn make-scheme-number
  "Creates a number"
  [n]
  ((table/gett 'make 'scheme-number) n))
