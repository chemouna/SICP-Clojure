
(ns sicp.chapter2.rectangular
  (:require [sicp.chapter2.common :as cm]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]))

; internal procedures
(defn- real-part
  [z]
  (first z))

(defn- imag-part
  [z]
  (rest z))

(defn- make-from-real-imag
  [x y]
  (conj y x))

(defn- magnitude
  [z]
  (Math/sqrt (+ (cm/square (real-part z))
                (cm/square (imag-part z)))))

(defn- angle
  [z]
  (cm/atan (imag-part z) (real-part z)))

(defn- make-from-mag-ang
  [r a]
  (conj (* r (Math/sin a)) (* r (Math/cos a))))

; interface to the rest of the system
(defn tag
  [x]
  (tag/attach-tag 'rectangular x))

(table/putt 'real-part '(rectangular) real-part)

(table/putt 'imag-part '(rectangular) imag-part)

(table/putt 'magnitude '(rectangular) magnitude)

(table/putt 'angle '(rectangular) angle)

(table/putt 'make-from-real-imag 'rectangular
            #(tag (make-from-real-imag %1 %2)))

(table/putt 'make-from-mag-ang 'rectangular
       #(tag (make-from-mag-ang %1 %2)))

