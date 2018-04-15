#lang racket

(require "table.rkt")
(require racket/trace)

(provide make-scheme-number)

; install procedure 
(define (install-scheme-number)
  (define (equal? x y) (= x y))
  (trace equal?)
  ; interface to the rest of the system
  (put 'add '(scheme-number scheme-number)
       (lambda (x y) (+ x y)))
  (put 'sub '(scheme-number scheme-number)
       (lambda (x y) (- x y)))
  (put 'mul '(scheme-number scheme-number)
       (lambda (x y) (* x y)))
  (put 'div '(scheme-number scheme-number)
       (lambda (x y) (/ x y)))
  (put 'make 'scheme-number (lambda (x) x))
  (put 'equal? '(scheme-number scheme-number) equal?)
  (put '=zero? '(scheme-number)
       (lambda (x) (= x 0)))
  (put 'exp '(scheme-number scheme-number)
       (lambda (x y) (expt x y)))
  'done)

; install
(install-scheme-number)

(define (make-scheme-number n)
  ((get 'make 'scheme-number) n))

;(trace make-scheme-number)
