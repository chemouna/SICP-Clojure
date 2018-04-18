
(ns sicp.chapter2.integer
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.rational :as rat]))

(defn tag
  [x]
  (tag/attach-tag 'integer x))

(defn integer->rational
  [n]
  (rat/make-rational-number n 1))

(table/putt 'add '(integer integer)
       #(tag (+ %1 %2)))
(table/putt 'sub '(integer integer)
            #(tag (- %1 %2)))

(table/putt 'mul '(integer integer)
            #(tag (* %1 %2)))

(table/putt 'div '(integer integer)
            #(rat/make-rational-number %1 %2))

(table/putt 'equ? '(integer integer) =)

(table/putt '=zero? '(integer)
       #(= 0 %1))

(table/putt 'make 'integer
       #(if (integer? %1)
          (tag %1)
          (println "non-integer value" %1)))

(table/put-coercion 'integer 'rational integer->rational)

(table/putt 'project '(integer) identity)

(defn make-integer
  [n]
  ((table/gett 'make 'integer) n))
