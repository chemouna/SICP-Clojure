#lang racket

(require "table.rkt")

(provide make-scheme-number)

; install procedure 
(define (install-scheme-number)
  (put 'add '(scheme-number scheme-number)
       (lambda (x y) (+ x y)))
  (put 'sub '(scheme-number scheme-number)
       (lambda (x y) (- x y)))
  (put 'mul '(scheme-number scheme-number)
       (lambda (x y) (* x y)))
  (put 'div '(scheme-number scheme-number)
       (lambda (x y) (/ x y)))
  (put 'make 'scheme-number (lambda (x) x))
  (put 'super-type 'scheme-number 'complex)
  (put 'equal? '(scheme-number scheme-number)
       (lambda (x y) (= x y)))
  'done)

; install
(install-scheme-number)

(define (make-scheme-number n)
  ((get 'make 'scheme-number) n))
