
(ns sicp.chapter2.scheme-number
  (:require [sicp.chapter2.table :as table]))

(defn- equal?
  "Checks if two numbers are equal"
  [x y]
  (= x y))

; interface to the rest of the system
(table/put 'add '(scheme-number scheme-number)
     #(+ %1 %2))

(table/put 'sub '(scheme-number scheme-number)
     #(- %1 %2))

(table/put 'mul '(scheme-number scheme-number)
     #(* %1 %2))

(table/put 'div '(scheme-number scheme-number)
     #(/ %1 %2))

(table/put 'make 'scheme-number  #(%1))

(table/put 'equal? '(scheme-number scheme-number) equal?)

(table/put '=zero? '(scheme-number)
      #(= %1 0))

(table/put 'exp '(scheme-number scheme-number)
     #(expt %1 %2))

(defn make-scheme-number
  "Creates a number"
  [n]
  ((get 'make 'scheme-number) n))
