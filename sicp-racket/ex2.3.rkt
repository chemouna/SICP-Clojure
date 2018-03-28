#lang racket

(define (make-point x y)
  (cons x y))

(define (x-point p)
  (car p))

(define (y-point p)
  (cdr p))

(define (make-rectangle a l w)
  (cons a (cons l w)))

(define (rect-height x)
  (car (cdr x)))

(define (rect-width x)
  (cdr (cdr x)))

;; another representation that can be used
; join two opposite corners
(define (make-rect2 a b) (cons a b))
  
(define (rect-height2 r)
  (abs (- (y-point (car r)) (y-point (cdr r)))))

(define (rect-width2 r)
  (abs (- (x-point (car r)) (x-point (cdr r)))))
  
(define (perimeter x)
  (+ (* 2 (rect-height x)) (* 2 (rect-width x))))

(define (area x)
  (* (rect-height x) (rect-width x)))
