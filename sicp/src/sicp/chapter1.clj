(ns sicp.chapter1)

(defn abs [n] (max n (- n)))

(defn good-enough? [guess x]
  (< (abs (- (* guess guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

;; Exercise 1.7
(defn good-enough? [old-guess new-guess]
  (< (/ (abs (- old-guess new-guess)) old-guess) 0.001))

;; Exercice 1.8



