#lang racket


;; g produces f

(define (average x y z) (/ (+ x y z) 3))

(define (smooth f dx)
  (lambda (x) (average (f (- x dx)) (f x) (f (+ x dx)))))

(define (repeated f n)
  (if (= n 1)
      f
      (compose f (repeated f (- n 1)))))

(define (n-fold-smooth f dx n)
  (repeated (smooth f dx) n))