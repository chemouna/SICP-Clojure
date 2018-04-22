
(ns sicp.chapter2.complex
  (:require [sicp.chapter2.table :as table]
            ;[sicp.chapter2.apply-generic-with-raise :as agr]
            [sicp.chapter2.apply-generic-with-coercion :as agc]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.common :as cm]
            [sicp.chapter2.generic-operations :as g]
            [clojure.tools.trace :as trace])
  (:use [sicp.chapter2.tower]
        [sicp.chapter2.rectangular]
        [sicp.chapter2.polar]))

;(trace/trace-ns 'sicp.chapter2.complex)

; imported procedures from rectangular and polar packages
(defn make-from-real-imag
  [x y]
  ((table/gett 'make-from-real-imag 'rectangular) x y))

(defn make-from-mag-ang
  [r a]
  ((table/gett 'make-from-mag-ang 'polar) r a))

(defn real-part
  [z]
  (agc/apply-generic 'real-part z))

(defn imag-part
  [z]
  (agc/apply-generic 'imag-part z))

(defn magnitude
  [z]
  (agc/apply-generic 'magnitude z))

(defn angle
  [z]
  (agc/apply-generic 'angle z))

; internal procedures
(defn add-complex
  [z1 z2]
  (make-from-real-imag (+ (real-part z1) (real-part z2))
                       (+ (real-part z1) (real-part z2))))

(defn sub-complex
  [z1 z2]
  (make-from-real-imag (- (real-part z1) (real-part z2))
                       (- (imag-part z1) (imag-part z2))))

(defn mul-complex
  [z1 z2]
  (make-from-mag-ang (* (magnitude z1) (magnitude z2))
                     (+ (angle z1) (angle z2))))

(defn div-complex
  [z1 z2]
  (make-from-mag-ang (/ (magnitude z1) (magnitude z2))
                     (- (angle z1) (angle z2))))

(defn equal?
  [z1 z2]
  (and (= (real-part z1) (real-part z2))
       (= (imag-part z1) (imag-part z2))))

(defn =zero?
  [z]
  (and (= (real-part z) 0) (= (imag-part z) 0)))

(defn addd
  [z1 z2 z3]
  (println " ** addd called with : " (list z1 z2 z3))
  (make-from-real-imag (+ (real-part z1)
                          (real-part z2)
                          (real-part z3))
                       (+ (imag-part z1)
                          (imag-part z2)
                          (imag-part z3))))

(defn tag
  [z]
  (tag/attach-tag 'complex z))

(defn negate-complex
  [z]
  (tag (make-from-real-imag (g/negate (real-part z)) (imag-part z))))

(table/putt 'add '(complex complex)
            #(tag (add-complex %1 %2)))

(table/putt 'sub '(complex complex)
            #(tag (sub-complex %1 %2)))

(table/putt 'mul '(complex complex)
            #(tag (mul-complex %1 %2)))

(table/putt 'div '(complex complex)
            #(tag (div-complex %1 %2)))

(table/putt 'make-from-real-imag 'complex
            #(tag (make-from-real-imag %1 %2)))

(table/putt 'make-from-mag-ang 'complex
            #(tag (make-from-mag-ang %1 %2)))

(table/putt 'real-part '(complex) real-part)

(table/putt 'imag-part '(complex) imag-part)

(table/putt 'magnitude '(complex) magnitude)

(table/putt 'angle '(complex) angle)

(table/putt 'equal? '(complex complex) equal?)

(table/putt '=zero? '(complex) =zero?)

(table/putt 'addd '(complex complex complex)
            #(tag (addd %1 %2 %3)))

(table/putt 'negate '(complex) negate-complex)

(defn make-complex-from-real-imag
  [x y]
  ((table/gett 'make-from-real-imag 'complex) x y))

(defn make-complex-from-mag-ang
  [r a]
  ((table/gett 'make-from-mag-ang 'complex) r a))

(defn real->complex
  [r]
  (make-complex-from-real-imag (tag/contents r) 0))
