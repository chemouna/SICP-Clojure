#lang racket

(define (make-segment start end)
  (cons start end))

(define (start-segment x)
  (car x))

(define (end-segment x)
  (cdr x))

(define (make-point x y)
  (cons x y))

(define (x-point p)
  (car p))

(define (y-point p)
  (cdr p))

(define (print-point p)
  (newline)
  (display "(")
  (display (x-point p))
  (display ",")
  (display (y-point p))
  (display ")"))

(define (midpoint-segment s)
  (make-point (/ (+ (x-point (start-segment s)) (x-point (end-segment s))) 2)
              (/ (+ (y-point (start-segment s)) (y-point (end-segment s))) 2)))

(define a (make-point -1 2))
(define b (make-point 3 -6))
(define s (make-segment a b))
(define m (midpoint-segment s))
(print-point m)

(define a2 (make-point 6.4 3))
(define b2 (make-point -10.7 4))
(define s2 (make-segment a2 b2))
(define m2 (midpoint-segment s2))
(print-point m2)
