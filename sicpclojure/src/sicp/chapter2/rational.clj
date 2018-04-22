
(ns sicp.chapter2.rational
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.common :as cm]
            [clojure.tools.trace :as trace]
            [sicp.chapter2.generic-operations :as g]))

; internal procedures
(defn numer
  [x]
  (if (number? (first x))
    (first x)
    (first (tag/contents x)))) ;; todo: this if is uncessary and hides bugs -> fix it

(defn denom
  [x]
  (if (number? (second x))
    (second x)
    (second (tag/contents x)))) ;; todo: this if is uncessary and hides bugs -> fix it

(defn make-rat
  [n d]
  (let [g (cm/gcd n d)]
    (list (/ n g) (/ d g))))

(defn add-rat
  [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn- sub-rat
  [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn- mul-rat
  [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn- div-rat
  [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal?
  [x y]
  (and (= (numer x) (numer y))
       (= (denom x) (denom y))))

(defn- =zero?
  [x]
  (and (= (numer x) 0) (not (= (denom x) 0))))

(defn tag
  [x]
  (tag/attach-tag 'rational x))

(defn negate
  [x]
  (tag (make-rat (g/negate (numer x)) (denom x))))

; interface to rest of the system
(table/putt 'add '(rational rational)
            #(tag (add-rat %1 %2)))

(table/putt 'sub '(rational rational)
            #(tag (sub-rat %1 %2)))

(table/putt 'mul '(rational rational)
            #(tag (mul-rat %1 %2)))

(table/putt 'div '(rational rational)
            #(tag (div-rat %1 %2)))

(table/putt 'make 'rational
            #(tag (make-rat %1 %2)))

(table/putt 'equal? '(rational rational) equal?)

(table/putt '=zero? '(rational) =zero?)

(table/putt 'negate '(rational) negate)

(defn make-rational-number
  [n d]
  ((table/gett 'make 'rational) n d))


