(ns sicp.chapter2.apply-generic-with-coercion
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [clojure.tools.trace :as trace]))

(defn not-nil?
  [x]
  (not (nil? x)))

(defn no-method
  [op types]
  (println "No method for these types and operation"
           (list op types)))

(defn apply-generic
  [op & args]
  (let [type-tags (map tag/type-tag args)]
    (let [proc (table/gett op type-tags)]
      (if proc
        (apply proc (map tag/contents args))
        (if (= (count args) 2)
          (let [type1 (first type-tags)
                type2 (second type-tags)
                a1 (first args)
                a2 (second args)]
            (if (not (= type1 type2))
              (let [t1->t2 (table/get-coercion type1 type2)
                    t2->t1 (table/get-coercion type2 type1)]
                (cond
                  (not-nil? t1->t2) (apply-generic op (t1->t2 a1) a2)
                  (not-nil? t2->t1) (apply-generic op a1 (t2->t1 a2))
                  :else (no-method op type-tags)))
              (no-method op type-tags)))
          (no-method op type-tags))))))
