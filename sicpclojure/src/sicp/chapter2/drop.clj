
(ns sicp.chapter2.drop
  (:require [sicp.chapter2.apply-generic-with-raise :as agr]
            [sicp.chapter2.raise :as r]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]))

(defn equ?
  [x y]
  (agr/apply-generic-with-raise 'equal? x y))

(defn project
  [x]
  (agr/apply-generic-with-raise 'project x))

(defn drop
  [x]
  (let [project-proc (table/gett 'project (list (tag/type-tag x)))]
    (if project-proc
      (let [projected (project x)
            raised (r/raise projected)]
        (if (and (not (= (tag/type-tag x) (tag/type-tag projected))) (equ? raised x))
          (drop projected)
          x))
      x)))

;(drop (int/make-integer 2))

;; todo: handle the case where project is not found for that type

(defn new-apply-generic
  [op & args]
  (drop (apply agr/apply-generic-with-raise op args)))

