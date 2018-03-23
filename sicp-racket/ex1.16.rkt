#lang racket

(define (even? n)
   (= (remainder n 2) 0))

(define (square x)
   (* x x))

(define (expt-iter b n p)
   (cond ((= n 0) p)
         ((even? n) (expt-iter (square b) (/ n 2) p))
         (else (expt-iter  b (- n 1) (* p b)))))

(define (fast-expt b n)
   (expt-iter b n 1))