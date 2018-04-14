#lang racket

(require "table.rkt")
(require "apply-generic-with-table-coercion.rkt")
(require "complex-number.rkt")
(require "scheme-number.rkt")

(define (scheme-number->scheme-number n) n)
(define (complex->complex z) z)
(put-coercion 'scheme-number 'scheme-number
              scheme-number->scheme-number)
(put-coercion 'complex 'complex complex->complex)

(define (exp x y) (apply-generic 'exp x y))

(exp (make-scheme-number 2) (make-scheme-number 4))
(exp (make-complex-from-real-imag 2 3) (make-complex-from-real-imag 4 5))

; a/ with two complex numbers we get an error :
; application: not a procedure;
; expected a procedure that can be applied to arguments
;  given: '()
;  arguments...:

;(define (equ? x y) (apply-generic 'equal? x y))

;(equ? (make-scheme-number 2) (make-scheme-number 4))
;(equ? (make-complex-from-real-imag 2 3) (make-complex-from-real-imag 4 5))

; b/ 