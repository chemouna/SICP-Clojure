
(ns sicp.chapter2.apply-generic-with-raise)

(def tower-of-types '(integer rational real complex))

(defn find-highest-type-helper
  [types tower]
  (cond
    (empty? (rest types)) (first types)
    (= (first types) (first tower)) (find-highest-type-helper (rest types) (rest tower))
    :else (find-highest-type-helper types (rest tower))))

(defn find-highest-type
  [types]
  (find-highest-type-helper types tower-of-types))

