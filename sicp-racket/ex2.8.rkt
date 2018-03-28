#lang racket

(define (make-interval x y)
  (cons x y))

(define (lower-bound i)
  (car i))

(define (upper-bound i)
  (cdr i))

(define (sub-interval x y)
  (make-interval (- (lower-bound x) (upper-bound y))
                 (- (upper-bound x) (lower-bound y))))


(define a (make-interval 1 10))
(define b (make-interval 50 100))
(define c (make-interval 5 20))
(sub-interval b a)
(sub-interval a b)
(sub-interval a c)
