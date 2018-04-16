
(ns sicp.chapter2.common)

(defn expt
  [x n]
  (reduce * (repeat n x)))

(defn pair?
  [coll]
  (if (or (empty? coll) (empty? (rest coll)))
    false
    (empty? (rest (rest coll)))))

