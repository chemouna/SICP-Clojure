
(ns sicp.chapter2.polar
  (:require [sicp.chapter2.common :as cm]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]))

; internal procedures
(defn- magnitude
  [z]
  (first z))

(defn- angle
  [z]
  (rest z))

(defn- make-from-mag-ang
  [r a]
  (conj a r))

(defn- real-part
  [z]
  (* (magnitude z) (Math/cos (angle z))))

(defn- imag-part
  [z]
  (* (magnitude z) (Math/sin (angle z))))

(defn- make-from-real-imag
  [x y]
  (conj (cm/atan y x)) (Math/sqrt (+ (cm/square x) (cm/square y))))

; interface to the rest of the system
(defn tag
  [x]
  (tag/attach-tag 'polar x))

(table/putt 'real-part '(polar) real-part)

(table/putt 'imag-part '(polar) imag-part)

(table/putt 'magnitude '(polar) magnitude)

(table/putt 'angle '(polar) angle)

(table/putt 'make-from-real-imag 'polar
            #(tag (make-from-real-imag %1 %2)))

(table/putt 'make-from-mag-ang 'polar
            #(tag (make-from-mag-ang %1 %2)))
