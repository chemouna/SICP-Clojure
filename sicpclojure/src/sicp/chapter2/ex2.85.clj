
(ns sicp.chapter2.ex2.85
  (:require [sicp.chapter2.apply-generic-with-raise :as agr]
            [sicp.chapter2.raise :as r]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

(defn equ?
  [x y]
  (agr/apply-generic-with-raise 'equal? x y))

(defn project
  [x]
  (agr/apply-generic-with-raise 'project x))

(defn drop
  [x]
  (cond
    (equ? (r/raise (project x)) x) (project x)
    :else x))

;; todo: put it in a test 
;(drop (rat/make-rational-number 2 1))

