
(ns sicp.chapter2.tag
  (:use sicp.chapter2.common))

(defn type-tag
  [datum]
  (cond
    (number? datum) 'scheme-number
    (pair? datum) (first datum)
    :else (println "Bad tagged datum: TYPE-TAG" datum)))


