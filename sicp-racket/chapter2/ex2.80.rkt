#lang racket

(require "tag.rkt")
(require "apply-generic.rkt")
(require "scheme-number.rkt")
(require "rational-number.rkt")
(require "complex-number.rkt")
(require "table.rkt")

(define (=zero? x)
  (apply-generic '=zero? x))

(define sn1 (make-scheme-number 0))

(define rn1 (make-rational-number 0 3))

(define cn1 (make-complex-from-real-imag 0 0))

(=zero? sn1)
(=zero? rn1)
(=zero? cn1)