(ns sicp.chapter2.dense-term
  (:use [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.generic-term :as gt]))

(def zero (int/make-integer 0))

(defn- term-list-order
  [term-list]
  (- (count term-list) 1))

(defn- adjoin-term
  [term-order term-coeff term-list]
  (cond
    (=zero? term-coeff) term-list

    (= term-order (+ 1 (term-list-order term-list))) (cons term-coeff term-list)

    (> term-order (term-list-order term-list)) (adjoin-term term-order
                                                            term-coeff
                                                            (conj term-list zero))

     :else (println "Cannot adjoin term of lower order than term list -- ADJOIN-TERM"
                    (list term-order term-coeff term-list))))

(defn- the-empty-termlist
  []
  '())

(defn- first-term
  [term-list]
  (first term-list))

(defn- rest-terms
  [term-list]
  (rest term-list))

(defn- empty-termlist?
  [term-list]
  (empty? term-list))

(defn- ensure-valid-term-list
  [terms]
  (if (empty-termlist? terms)
    (list zero)
    terms))

(defn- make-from-coeffs
  [coeffs]
  coeffs)

(defn- strip-leading-zeros
  [coeffs]
  (cond
    (empty-termlist? coeffs) (the-empty-termlist)
    (not (=zero? (first-term coeffs))) coeffs
    :else (make-from-coeffs (rest-terms coeffs))))

(defn- insert-term
  [term terms]
  (if (empty-termlist? terms)
      (adjoin-term (gt/order term) (coeff term) (the-empty-termlist))
      (let [head-order (term-list-order terms)
            term-order (order term)]
        (cond
          (> term-order head-order) (adjoin-term term-order (coeff term) terms)
          (= term-order head-order) (adjoin-term term-order
                                                 (add (coeff term) (first terms))
                                                 (rest-terms terms))
          :else (adjoin-term head-order (first terms) (insert-term term (rest-terms terms)))))))

(defn- build-terms
  [terms result]
  (if (empty? terms)
      result
      (build-terms (rest terms) (insert-term (first terms) result))))

(defn- make-from-terms
  [terms]
  (build-terms terms (the-empty-termlist)))

(defn- add-terms
  [L1 L2]
  (cond (empty-termlist? L1) L2
        (empty-termlist? L2) L1
        :else (let [t1 (first-term L1)
                    t2 (first-term L2)
                    o1 (term-list-order L1)
                    o2 (term-list-order L2)]
                (cond
                  (> o1 o2) (adjoin-term o1 t1 (add-terms (rest-terms L1) L2))
                  (< o1 o2) (adjoin-term o2 t2 (add-terms L1 (rest-terms L2)))
                  :else (adjoin-term o1
                                     (add t1 t2)
                                     (add-terms (rest-terms L1) (rest-terms L2)))))))

(defn- mul-term-by-all-terms
  [o1 c1 L]
  (if (empty-termlist? L)
    (the-empty-termlist)
    (let [t2 (first-term L)
          new-order (+ o1 (term-list-order L))]
      (adjoin-term
       new-order
       (mul c1 t2)
       (mul-term-by-all-terms o1 c1 (rest-terms L))))))

(defn- mul-terms
  [L1 L2]
  (if (empty-termlist? L1)
    (the-empty-termlist)
    (add-terms (mul-term-by-all-terms (term-list-order L1) (first-term L1) L2)
               (mul-terms (rest-terms L1) L2))))

(defn- =zero-terms?
  [L]
  (cond
    (empty-termlist? L) true
    (not (=zero? (first-term L))) false
    :else (=zero-terms? (rest-terms L))))

(defn- negate-terms
  [L]
  (if (empty-termlist? L)
      (the-empty-termlist)
      (let [term (first-term L)]
        (adjoin-term (term-list-order L)
                     (negate term)
                     (negate-terms (rest-terms L))))))

(defn- eq-terms?
  [L1 L2]
  (cond
    (empty-termlist? L1) (empty-termlist? L2)
    (empty-termlist? L2) false
    :else (and (equal? (first-term L1) (first-term L2))
               (eq-terms? (rest-terms L1) (rest-terms L2)))))

(defn- dense-terms->sparse-terms
  [L]
  ((get 'make-from-coeffs 'sparse-terms) L))

(defn- count-zero-terms
  [L]
  (if (empty-termlist? L)
    0
    (+ (if (=zero? (first-term L)) 1 0)
           (count-zero-terms (rest-terms L)))))

(defn- to-best-representation
  [L]
  (if (store-as-sparse? (term-list-order L) (count-zero-terms L))
      (dense-terms->sparse-terms L)
      (tag L)))

;; interface to rest of the system
(defn tag
  [t]
  (tag/attach-tag 'dense-terms (ensure-valid-term-list t)))

(table/putt 'add '(dense-terms dense-terms)
            #(to-best-representation (add-terms %1 %2)))

(table/putt 'mul '(dense-terms dense-terms)
            #(to-best-representation (mul-terms %1 %2)))

(table/put 'equal? '(dense-terms dense-terms) eq-terms?)

(table/putt '=zero? '(dense-terms) =zero-terms?)

(table/putt 'negate '(dense-terms)
            #(to-best-representation (negate-terms t)))

(table/putt 'make-from-terms 'dense-terms
            #(tag (make-from-terms terms)))

(table/putt 'make-from-coeffs 'dense-terms
            #(tag (make-from-coeffs coeffs)))

(table/put-coercion 'dense-terms 'sparse-terms dense-terms->sparse-terms)
