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
; we can get into an infinite loop see details here http://jots-jottings.blogspot.co.uk/2012/02/sicp-exercise-281-coercing-to-same-type.html

; b/ what he means is that it tries to coerce them if they are the same time and the case where the operation
; is just not available so its a waste and not needed.
; so it's not strictly needed since the error will be raised but it's a good thing to add to avoid wasting the extra work.

