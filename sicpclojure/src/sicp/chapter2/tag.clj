
(ns sicp.chapter2.tag
  (:use sicp.chapter2.common))

(defn type-tag
  [datum]
  (cond
    (number? datum) 'scheme-number
    (pair? datum) (first datum)
    :else (println "Bad tagged datum: TYPE-TAG" datum)))

(defn contents
  [datum]
  (cond
    (number? datum) datum
    (pair? datum) (rest datum)
    :else (println "Bad tagged datum: CONTENTS" datum)))

(defn attach-tag
  [& args]
  (cond
    (= (count args) 1) (first args)
    (= (count args) 2) (conj (second args) (first args))
    :else (println "Bad arguments: ATTACH-TAG" args)))




