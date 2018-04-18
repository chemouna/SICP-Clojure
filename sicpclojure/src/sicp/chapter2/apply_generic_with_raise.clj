
(ns sicp.chapter2.apply-generic-with-raise
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.raise :as raise]))

(def tower-of-types '(integer rational real complex))

(defn find-highest-type-helper
  [types tower]
  (cond
    (empty? (rest types)) (first types)
    (= (first types) (first tower)) (find-highest-type-helper (rest types) (rest tower))
    :else (find-highest-type-helper types (rest tower))))

(defn find-highest-type
  [types]
  (find-highest-type-helper types tower-of-types))

(defn raise-types
  [target-type types args result]
  (let [type->target (table/get-coercion (first types) target-type)]
    (if type->tr)

(defn apply-generic-with-raise
  [op & args]
  (let [type-tags (map tag/type-tag args)]
    (let [proc (table/gett op type-tags)]
      (if proc
        (apply proc (map tag/contents args))
        (let [highest-type (find-highest-type type-tags)]
          (raise-types highest-type type-tags args)) ;; todo: solve the issue of passing highest-type here with type-tags too  
        ))))

;(apply-generic-with-raise 'add (rat/make-rational-number 2 3) (real/make-real 2.12))
