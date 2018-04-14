#lang racket

(require "table.rkt")
(require "apply-generic.rkt")

(define (install-scheme-number-package)
  (put 'add '(scheme-number scheme-number)
       (lambda (x y) (+ x y)))
  (put 'sub '(scheme-number scheme-number)
       (lambda (x y) (- x y)))
  (put 'mul '(scheme-number scheme-number)
       (lambda (x y) (* x y)))
  (put 'div '(scheme-number scheme-number)
       (lambda (x y) (/ x y)))
  (put 'make 'scheme-number
       (lambda (x) x))
  'done)

(install-scheme-number-package)

(define (make-scheme-number n)
  ((get 'make 'scheme-number) n))

(define x (make-scheme-number 2))
(define y (make-scheme-number 4))

(define (add x y)
  (apply-generic 'add x y))

(define z (add x y))

; What is the lesson from this exercice (ex 2.78) ? 
; -> that a part of the program can be changed independently (as we did in tag.rkt to make this change)
; -> another possibility is that the straightforward solution is not correct and instead we should think of making an additive change like 
;    mentionned here http://jots-jottings.blogspot.co.uk/2012/02/sicp-exercise-278-using-primitive-types.html

