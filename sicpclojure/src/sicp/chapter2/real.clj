(ns sicp.chapter2.real
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.common :as cm]))

(defn tag
  [x]
  (tag/attach-tag 'real x))

(table/putt 'add '(real real)
            #(tag (+ %1 %2)))

(table/putt 'sub '(real real)
            #(tag (- %1 %2)))

(table/putt 'mul '(real real)
            #(tag (* %1 %2)))

(table/putt 'div '(real real)
            #(tag (/ %1 %2)))

(table/putt 'equ? '(real real) =)

(table/putt '=zero? '(real)
            #(= 0 %1))

(table/putt 'make 'real
            #(tag %1))

(table/putt 'addd '(real real real)
           #(tag (+ %1 %2 %3)))

(table/putt 'project '(real) identity)

(defn make-real
  [n]
  ((table/gett 'make 'real) n))

