(ns sicp.chapter2.apply-generic-with-raise
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.raise :as r]
            [clojure.tools.trace :as trace])
  (:use [sicp.chapter2.tower]))

(defn index-of [e coll] (first (keep-indexed #(if (= e %2) %1) coll)))

(defn find-highest-type
  [types]
  (last (sort-by #(index-of % tower-of-types) types)))

(defn raise-to
  [target type v]
  ;(println "raise to called with: " (list target type v))
  (if (or (nil? type) (nil? v))
    v
    (if (= type target)
      v
      (let [r (r/raise v)]
        ;r))))
        (raise-to target (tag/type-tag r) r)))))


(defn raise-values
  [target type-tags args result]
  (if (empty? type-tags)
    result
    (raise-values target
                  (rest type-tags)
                  (rest args)
                  (conj result (raise-to target (first type-tags) (first args))))))

(defn apply-generic-with-raise
  [op & args]
  (let [type-tags (map tag/type-tag args)]
    (let [proc (table/gett op type-tags)]
      (if proc
          (apply proc (map tag/contents args)))
          (let [highest-type (find-highest-type type-tags)
              raised-values (raise-values highest-type type-tags args '())]
          (if (empty? raised-values)
              (println "raised-values was empty")
              (apply apply-generic-with-raise op raised-values))))))

