
(ns sicp.chapter2.table)

(def op-table (atom {}))

(def coercion-table (atom {}))

(defn putt
  "Puts a procedure in the operation table under the row op and the column type"
  [op type proc]
  (swap! op-table assoc (list op type) proc)) ;; where did it get table from?

(defn gett
  "Gets a procedure from the cell in operation table under the row op and the column type"
  [op type]
  (get @op-table (list op type)))

(defn put-coercion
  "Put a coercion from a type to another in coercion table"
  [type1 type2 proc]
  (swap! coercion-table assoc (list type1 type2) proc))

(defn get-coercion
  "Gets coercion procedure from a type to another"
  [type1 type2]
  (get @coercion-table (list type1 type2)))



