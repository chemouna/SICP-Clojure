#lang racket

(define (make-vector x y)
             (cons x y))

(define (xcor-vect v)
  (car v))

(define (ycor-vect v)
  (cdr v))

;(define (make-segment origin start-point end-point)
;  (cons (make-vector origin start-point) (make-vector origin end-point)))

(define (make-segment start end)
 (cons start end))
  
(define (start-segment s)
  (car s))

(define (end-segment s)
  (cdr s))

