
(ns sicp.chapter2.dense-term
  (:use [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.integer :as int]))

(defn make-term
  [order coeff]
  coeff)

(defn coeff
  [term]
  term)

(defn order
  [termlist]
  (- (count termlist) 1))

(defn first-term
  [term-list]
  (first term-list))

(defn rest-terms
  [term-list]
  (rest term-list))

(defn zero-term
  []
  (int/make-integer 0))

(defn empty-termlist?
  [term-list]
  (empty? term-list))

(defn adjoin-term
  [term-order term-coeff termlist]
  (cond
    (=zero? coeff) termlist

    (= term-order (+ 1 (order termlist)))
    (conj (make-term term-order term-coeff) termlist)

    (> term-order (order termlist))
    (adjoin-term term-order term-coeff (conj termlist zero-term))

    :else (println "Cannot adjoin term with a lower order " (list term-order term-coeff))))

(def the-empty-termlist '())

(defn empty-termlist?
  [term-list]
  (empty? term-list))

(defn add-terms
  [L1 L2]
  (cond (empty-termlist? L1) L2
        (empty-termlist? L2) L1
        :else
        (let [t1 (first-term L1)
              t2 (first-term L2)
              o1 (order t1)
              o2 (order t2)]
          (cond
            (> o1 o2) (adjoin-term t1 (add-terms (rest-terms L1) L2))
            (< o1 o2) (adjoin-term t2 (add-terms L1 (rest-terms t2)))
            :else (adjoin-term o1 (add (coeff t1) (coeff t2)) (add-terms (rest-terms L1) (rest-terms L2)))))))

(defn mul-term-by-all-terms
  [o1 c1 L]
  (if (empty-termlist? L)
    (the-empty-termlist)
    (let* [t2 (first-term L)
           new-order (+ o1 (order L))]
      (adjoin-term
       new-order
       (mul c1 (coeff t2))
       (mul-term-by-all-terms o1 c1 (rest-terms L))))))

(defn mul-terms
  [L1 L2]
  (if (empty-termlist? L1)
    (the-empty-termlist)
    (add-terms (mul-term-by-all-terms (order L1) (coeff (first-term L1)) L2)
               (mul-terms (rest-terms L1) L2))))

(defn negate-terms
  [terms]
  (cond
    (empty-termlist? terms) the-empty-termlist
    :else (adjoin-term (order terms) (negate (coeff (first-term terms)))
                       (negate-terms (rest-terms terms)))))



