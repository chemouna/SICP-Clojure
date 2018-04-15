#lang racket

(require "integer.rkt")
(require "real.rkt")
(require "rational-number.rkt")
(require "apply-generic-with-table-coercion.rkt")
(require "complex-number.rkt")
(require "table.rkt")
(require "raise.rkt")
(require "tag-without-number-special-case.rkt")

(define (numer r) (apply-generic 'numer r))
(define (denom r) (apply-generic 'denom r))

(define (integer->rational n)
  (make-rational-number n 1))

(define (rational->real r)
  (make-real (/ (numer r) (denom r))))

(define (real->complex r)
  (make-complex-from-real-imag r 0))

(put-coercion 'integer 'rational integer->rational)
(put-coercion 'rational 'real rational->real)
(put-coercion 'real 'complex real->complex)

;(raise (make-integer 2))
(raise (make-rational-number 3 4))