#lang racket

(require "apply-generic.rkt")
(require "complex-number-package.rkt")

(define (magnitude z) (apply-generic 'magnitude z))

(magnitude (make-complex-from-real-imag 3 4))
