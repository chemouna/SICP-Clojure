#lang racket

(require "tag.rkt")
(require "apply-generic.rkt")
(require "scheme-number.rkt")
(require "rational-number.rkt")
(require "complex-number.rkt")

(define (equ? x y)
  (apply-generic 'equal? x y))

(define sn1 (make-scheme-number 2))
(define sn2 (make-scheme-number 2))
(define sn3 (make-scheme-number 3))

(define rn1 (make-rational-number 2 3))
(define rn2 (make-rational-number 2 3))
(define rn3 (make-rational-number 2 4))

(define cn1 (make-complex-from-real-imag 2 3))
(define cn2 (make-complex-from-real-imag 2 3))
(define cn3 (make-complex-from-real-imag 2 4))

;(equ? sn1 sn2)
;(equ? sn1 sn3)

;(equ? rn1 rn2)
;(equ? rn1 rn3)

;(equ? cn1 cn2)
;(equ? cn1 cn3)
