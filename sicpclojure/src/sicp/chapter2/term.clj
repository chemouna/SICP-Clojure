
(ns sicp.chapter2.term
  (:use [sicp.chapter2.generic-operations]))

(defn coeff
  [term]
  (second term))

(defn adjoin-term
  [term term-list]
  (if (=zero? (coeff term))
    term-list
    (conj term-list term)))

(def the-empty-termlist '())

(defn first-term
  [term-list]
  (first term-list))

(defn rest-terms
  [term-list]
  (rest term-list))

(defn empty-termlist?
  [term-list]
  (empty? term-list))

(defn make-term
  [order coeff]
  (list order coeff))

(defn order
  [term]
  (first term))

(defn add-terms
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

(defn mul-term-by-all-terms
  [t1 L]
  (if (empty-termlist? L)
    (the-empty-termlist)
    (let [t2 (first-term L)]
      (adjoin-term
       (make-term (+ (order t1) (order t2))
                  (mul (coeff t1) (coeff t2)))
       (mul-term-by-all-terms t1 (rest-terms L))))))

(defn mul-terms
  [L1 L2]
  (if (empty-termlist? L1)
      (the-empty-termlist)
      (add-terms (mul-term-by-all-terms (first-term L1) L2)
                 (mul-terms (rest-terms L1) L2))))

(defn =zero-terms?
  [termlist]
  (cond
    (empty-termlist? termlist) true
    (not (=zero? (coeff (first-term termlist)))) false
    :else (=zero-terms? (rest-terms termlist))))


