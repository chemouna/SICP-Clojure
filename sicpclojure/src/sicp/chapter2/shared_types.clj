
;; this file is to break cyclic dependencies between types
;; and put their common code here

(ns sicp.chapter2.shared-types
  (:require [sicp.chapter2.real :as real]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.common :as cm]))

(defn rational->real
  [r]
  (real/make-real (/ (rat/numer r) (rat/denom r))))

(table/put-coercion 'rational 'real rational->real)

(defn real->complex
  [r]
  (c/make-complex-from-real-imag (tag/contents r) 0))

(table/put-coercion 'real 'complex real->complex)

(defn project-rational
  [r]
  (int/make-integer (int (Math/floor (/ (rat/numer r) (rat/denom r))))))

(table/putt 'project '(rational) project-rational)

(defn project-real
  [x]
  (let [r (rationalize (tag/contents x))]
    (rat/make-rational-number (numerator r) (denominator r))))

(table/putt 'project '(real) project-real)

(defn project-complex
  [z]
  (let [r (c/real-part z)]
    (cond
      (not (= (c/imag-part z) 0)) z
      :else (if (cm/real? r)
              (real/make-real r)
              (int/make-integer r)))))

(table/putt 'project '(complex) project-complex)

