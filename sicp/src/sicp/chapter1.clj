(ns sicp.chapter1)

(defn good-enough? [guess x]
  (< (Math/abs (- (* guess guess) x)) 0.001))

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

;; (good-enough? 1 1.001)


;; Exercise 1.7

;; (sqrt 0.0001) yields 0.03230844833048122 instead of the expected 0.01 (an error of over 200%).


