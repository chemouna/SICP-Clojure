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
(defn new-good-enough? [old-guess new-guess]
  (< (/ (abs (- old-guess new-guess)) old-guess) 0.001))

;; Exercice 1.8
(defn cb-improve [guess x]
  (/ (+ (/ x (* guess guess)) (* 2 guess)) 3))

(defn cb-good-enough? [old-guess guess]
  (< (abs (- guess old-guess)) (* guess 0.001)))

(defn cb-iter [guess old-guess x]
  (if (cb-good-enough? old-guess guess)
    guess
    (cb-iter (cb-improve guess x) guess x)) )

(defn cb [x]
  (cb-iter 1.0 0.0 x))

(cb 2)

