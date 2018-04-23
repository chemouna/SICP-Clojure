(ns sicp.chapter2.sparse-term
  (:use [sicp.chapter2.generic-operations])
  (:require [clojure.tools.trace :as trace]
            [sicp.chapter2.table :as table]
            [sicp.chapter2.tag :as tag]
            [sicp.chapter2.generic-term :as gt]
            [sicp.chapter2.integer :as int]))

(defn- adjoin-term
  [term term-list]
  (if (=zero? (coeff term))
    term-list
    (conj term-list term)))

(def the-empty-termlist '())

(defn- first-term
  [term-list]
  (first term-list))

(defn- rest-terms
  [term-list]
  (rest term-list))

(defn- empty-termlist?
  [term-list]
  (empty? term-list))

(defn- add-terms
  [L1 L2]
  (cond (empty-termlist? L1) L2
        (empty-termlist? L2) L1
        :else
        (let [t1 (first-term L1)
              t2 (first-term L2)]
          (cond (> (order t1) (order t2))
                (adjoin-term
                 t1 (add-terms (rest-terms L1) L2))

                (< (order t1) (order t2))
                 (adjoin-term
                  t2 (add-terms L1 (rest-terms L2)))

                 :else
                  (adjoin-term
                   (make-term (order t1)
                              (add (coeff t1) (coeff t2)))
                   (add-terms (rest-terms L1)
                              (rest-terms L2)))))))

(defn- mul-term-by-all-terms
  [t1 l]
  (if (empty-termlist? l)
    the-empty-termlist
    (let [t2 (first-term l)]
      (adjoin-term
       (make-term (+ (order t1) (order t2))
                  (mul (coeff t1) (coeff t2)))
       (mul-term-by-all-terms t1 (rest-terms l))))))

(defn- mul-terms
  [L1 L2]
  (if (empty-termlist? L1)
      the-empty-termlist
      (add-terms (mul-term-by-all-terms (first-term L1) L2)
                 (mul-terms (rest-terms L1) L2))))

(defn- =zero-terms?
  [termlist]
  (cond
    (empty-termlist? termlist) true
    (not (=zero? (coeff (first-term termlist)))) false
    :else (=zero-terms? (rest-terms termlist))))

(defn- negate-term
  [term]
  (make-term (order term) (negate (coeff term))))

(defn- eq-term?
  [t1 t2]
  (and (= (order t1) (order t2)) (= (coeff t1) (coeff t2))))

(defn- eq-terms?
  [l1 l2]
  (cond
    (and (empty-termlist? l1) (empty-termlist? l2)) true
    (or (empty-termlist? l1) (empty-termlist? l2)) false
    (not (eq-term? (first-term l1) (first-term l2))) false
    :else (eq-terms? (rest-terms l1) (rest-terms l2))))

(defn- negate-terms
  [terms]
  (cond
    (empty-termlist? terms) the-empty-termlist
    :else (adjoin-term (negate-term (first-term terms))
                       (negate-terms (rest-terms terms)))))

(defn- ensure-valid-term-list
  [terms]
  (if (empty-termlist? terms)
    (list (gt/make-term 0 (int/make-integer 0)))
    terms))

(defn- insert-term
  [term terms]
  (if (empty-termlist? terms)
    (adjoin-term term (the-empty-termlist))
    (let [head (first-term terms)
          head-order (order head)
          term-order (order term)]
      (cond (> term-order head-order) (adjoin-term term terms)
            (= term-order head-order)
             (adjoin-term (make-term term-order (add (coeff term) (coeff head)))
                          (rest-terms terms)))
      :else (adjoin-term head (insert-term term (rest-terms terms))))))

(defn- build-terms
  [terms result]
  (if (empty? terms)
    result
    (build-terms (rest terms) (insert-term (first terms) result))))

(defn- make-from-terms
  [terms]
  (build-terms terms (the-empty-termlist)))

(defn- convert-to-term-list
  [coeffs]
  (if (empty? coeffs)
    (the-empty-termlist)
    (adjoin-term (make-term (- (count coeffs) 1) (first coeffs))
                 (convert-to-term-list (rest coeffs)))))

(defn- make-from-coeffs
  [coeffs]
  (convert-to-term-list coeffs))

;; Coercion
(defn- calculate-zero-terms
  [first rest]
  (if (empty-termlist? rest)
    (order first)
    (let [next (first-term rest)]
      (+ (- (order first) (order next) 1)
         (calculate-zero-terms next (rest-terms rest)))))) 

(defn- store-as-sparse?
  [highest-order zero-terms]
  (if (>= highest-order 10)
    (> (/ zero-terms highest-order) 0.1)
    (> zero-terms (/ highest-order 5))))

(defn- keep-as-sparse?
  [L]
  (if (empty-termlist? L)
    false
    (let [highest-order (order (first-term L))
          zero-terms (calculate-zero-terms (first-term L) (rest-terms L))]
      (store-as-sparse? highest-order zero-terms))))

(defn- sparse-terms->dense-terms
  [L]
  (if (keep-as-sparse? L)
    (tag L)
    ((get 'make-from-terms 'dense-terms) L)))

;; interface to the rest of the system
(defn- tag
  [t]
  (tag/attach-tag 'sparse-terms (ensure-valid-term-list t)))

(table/putt 'add '(sparse-terms sparse-terms)
     #(tag (add-terms %1 %2)))

(table/putt 'mul '(sparse-terms sparse-terms)
     #(tag (mul-terms %1 %2)))

(table/putt 'equal? '(sparse-terms sparse-terms) eq-terms?)

(table/putt '=zero? '(sparse-terms) =zero-terms?)

(table/putt 'negate '(sparse-terms)
     #(tag (negate-terms %1)))

(table/putt 'make-from-terms 'sparse-terms
     #(tag (make-from-terms %1)))

(table/putt 'make-from-coeffs 'sparse-terms
            #(tag (make-from-coeffs %1)))

(table/put-coercion 'sparse-terms 'dense-terms sparse-terms->dense-terms)

