#lang racket

(require "apply-generic-with-table-coercion.rkt")
(require "types-packages-import.rkt")

(provide add mul =zero?)

;; some generic operations 
(define (add x y)
  (apply-generic 'add x y))

(define (mul x y)
  (apply-generic 'mul x y))

(define (=zero? x y)
  (apply-generic '=zero? x y))

