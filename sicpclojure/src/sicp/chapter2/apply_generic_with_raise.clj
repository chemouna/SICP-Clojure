(ns sicp.chapter2.apply-generic-with-raise
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.raise :as r]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.complex :as c]
            [clojure.tools.trace :as trace])
  (:use [sicp.chapter2.tower]))

(trace/trace-ns 'sicp.chapter2.apply-generic-with-raise)

(defn index-of [e coll] (first (keep-indexed #(if (= e %2) %1) coll)))

(defn find-highest-type
  [types]
  (println "find-highest-type: " types)
  (last (sort-by #(index-of % tower-of-types) types)))

;(find-highest-type '(rational real integer))
;(map #(index-of % tower-of-types) '('rational))

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
  (println "raise-values: "(list target type-tags args result))
  (if (empty? type-tags)
    result
    (raise-values target
                  (rest type-tags)
                  (rest args)
                  (conj result (raise-to target (first type-tags) (first args))))))

(defn apply-generic-with-raise
  [op & args]
  (println "apply-generic-with-raise: " (list op args))
  (let [type-tags (map tag/type-tag args)]
    (let [proc (table/gett op type-tags)]
      (if proc
          (apply proc (map tag/contents args))
          (let [highest-type (find-highest-type type-tags)
              raised-values (raise-values highest-type type-tags args '())]
          (if (empty? raised-values)
              (println "raised-values was empty")
              (apply apply-generic-with-raise op raised-values)))))))

