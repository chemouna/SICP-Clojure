#lang racket

(define (product term a next b)
  (if (> a b) 1
      (* (term a)
         (product term (next a) next b))))

(define (identity x) x)
(define (inc n) (+ n 1))

(define (product-integers a b)
  (product identity a inc b))

(define (factorial n)
  (product identity 1 inc n))

 (define (pi k)
  (define (pterm x)
    (/ (* 4 x x) (- (* 4 x x) 1)))
  (* 2 (product pterm 1 inc k)))
  
(define (producti term a next b)
  (define (iter a result) 
    (if (> a b) result
        (iter (next a) (* result (term a)))))
  (iter a 1))