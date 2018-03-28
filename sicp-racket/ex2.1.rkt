#lang racket

(define (numer x) (car x))
(define (denom x) (cdr x))

(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))

(define (make-rat n d)
  (let* ([g (gcd n d)]
         [ng (/ n g)]
         [dg (/ d g)])
    (cond ((and (< d 0) (> n 0)) (cons (- ng) (abs dg)))
          ((and (< d 0) (< g 0)) (cons (- ng) (- dg)))
          [else (cons ng dg)]))) 

(define one-half (make-rat 1 2))
(define one-third (make-rat 1 3))
(print-rat (add-rat one-third one-third))

;; (define x (make-rat 1 -2))
;; (print-rat x)