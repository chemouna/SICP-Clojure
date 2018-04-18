
(ns sicp.chapter2.rational
  (:require [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.common :as cm]
            [sicp.chapter2.real :as real]
            [clojure.tools.trace :as trace]))

;(trace/trace-ns 'sicp.chapter2.tag)
;(trace/trace-ns 'sicp.chapter2.table)
;(trace/trace-ns 'sicp.chapter2.rational)

; internal procedures
(defn numer
  [x]
  (first x))

(defn denom
  [x]
  (second x))

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

(defn rational->real
  [r]
  (real/make-real (/ (numer (tag/contents r)) (denom (tag/contents r)))))

;(defn rational->real
;  [r]
;  (real/make-real (/ (numer r) (denom r))))

; interface to rest of the system
(defn tag
  [x]
  (tag/attach-tag 'rational x))

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

(table/put-coercion 'rational 'real rational->real)

(defn make-rational-number
  [n d]
  ((table/gett 'make 'rational) n d))

;(rational->real (make-rational-number 2 1))
