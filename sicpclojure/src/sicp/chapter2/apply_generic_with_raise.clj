
(ns sicp.chapter2.apply-generic-with-raise
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.raise :as r]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.real :as real]))

(def tower-of-types '('integer 'rational 'real 'complex))

(defn find-highest-type-helper
  [types tower]
  (cond
    (empty? (rest types)) (first types)
    (= (first types) (first tower)) (find-highest-type-helper (rest types) (rest tower))
    :else (find-highest-type-helper types (rest tower))))

(defn find-highest-type
  [types]
  (find-highest-type-helper types tower-of-types))

(defn raise-to
  [target type v]
  (if (or (nil? type) (nil? v))
    v
    (if (= type target)
      v
      (let [r (r/raise v)]
        (raise-to target (tag/type-tag r) r)))))

;(r/raise (int/make-integer 2))
;(r/raise (rat/make-rational-number 2 1))
;(raise-to 'complex 'integer (int/make-integer 2))
;(raise-to 'integer 'integer (int/make-integer 2))

(defn raise-values
  [target type-tags args result]
  (if (empty? type-tags)
    result
    (raise-values target
                  (rest type-tags)
                  (rest args)
                  (conj result (raise-to target (first type-tags) (tag/contents (first args)))))))

(defn apply-generic-with-raise
  [op & args]
  (let [type-tags (map tag/type-tag args)]
    (let [proc (table/gett op type-tags)]
      (if proc
        (apply proc (map tag/contents args))
        (let [highest-type (find-highest-type type-tags)
              raised-values (raise-values highest-type type-tags args '())]
          (if (empty? raised-values)
            (println "error ")
            (apply op raised-values)))))))

(apply-generic-with-raise 'add (rat/make-rational-number 2 3) (real/make-real 2.12) (int/make-integer 2))


;; todo: solve the issue of passing highest-type here with type-tags too

