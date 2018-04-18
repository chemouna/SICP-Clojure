
(ns sicp.chapter2.ex2.85
  (:require [sicp.chapter2.apply-generic-with-raise :as agr]
            [sicp.chapter2.raise :as r]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

(defn project
  [x]
  (agr/apply-generic-with-raise 'project x))

(defn drop
  [x]
  (if (= (r/raise (project x)) x) ;; here i need to use the generic = defined in 2.79
    (project x)
    x))


(project (rat/make-rational-number 2 1))
