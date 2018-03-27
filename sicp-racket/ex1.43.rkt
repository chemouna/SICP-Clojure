#lang racket

(define (compose f g)
  (lambda (x) (f (g x))))

(define (repeated f n)
  (define (rep f n x)
    (if (= n 1) (f x) (f (rep f (- n 1) x))))
  (lambda (x) (rep f n x))) 

(define (repeated2 f n)
  (if (= n 1)
      f
      (compose f (repeated f (- n 1)))))

(define (square x) (* x x))

((repeated square 2) 5)