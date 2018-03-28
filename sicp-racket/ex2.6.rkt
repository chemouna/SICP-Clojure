#lang racket

(define zero (lambda (f) (lambda (x) x)))

(define (add-1 n)
  (lambda (f) (lambda (x) (f ((n f) x)))))

(define (inc n)
   (+ n 1))

(define one (add-1 zero))
(define two (add-1 one))

(define one_v2
   (lambda (f) (lambda (x) (f x))))

(define two_v2
   (lambda (f) (lambda (x) (f (f x)))))

;; ((one_v2 inc) 0)

(define (add-church-num m n)
  (lambda (f) (lambda (x) ((m f) ((n f) x)))))

(define three (add-church-num one two))
(define four (add-church-num two two))

((three inc) 0)
((four inc) 0)
