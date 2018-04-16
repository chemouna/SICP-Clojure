
(ns sicp.chapter2.common)

(defn expt
  [x n]
  (reduce * (repeat n x)))

(defn pair?
  [coll]
  (if (or (empty? coll) (empty? (rest coll)))
    false
    (empty? (rest (rest coll)))))

(defn square
  [x]
  (* x x))

(defn atan
  ([x] (Math/atan (double x)))
  ([x y] (Math/atan2 (double x) (double y))))


