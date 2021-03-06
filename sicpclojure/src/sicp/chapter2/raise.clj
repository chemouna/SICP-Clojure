(ns sicp.chapter2.raise
  (:require [sicp.chapter2.tag :as tag]
            [sicp.chapter2.table :as table]
            [clojure.tools.trace :as trace]
            [clojure.tools.trace])
  (:use sicp.chapter2.raise)
  (:use sicp.chapter2.tower)
  (:use sicp.chapter2.shared-types))

(defn apply-raise
  [v types]
  (cond
    (empty? types) (println "Type not found in the tower-of-types")
    (= (tag/type-tag v) (first types))
    (if (empty? (rest types))
      v
     (let [raise-proc (table/get-coercion (tag/type-tag v) (second types))]
       (if raise-proc
           (raise-proc (tag/contents v))
           (println "No coercion found for the types "
                    (list (tag/type-tag v) (first types))))))
    :else (apply-raise v (rest types))))

(defn raise
  [v]
  (apply-raise v tower-of-types))
