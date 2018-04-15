#lang racket

(require "tag.rkt")
(require "table.rkt")
(require "rational-number.rkt")

(provide make-integer)

; Integer package
(define (install-integer-package)
  (define (tag x)
    (attach-tag 'integer x))
  (define (integer->rational n)
    (make-rational-number n 1))
  (put 'add '(integer integer)
       (lambda (x y) (tag (+ x y))))
  (put 'sub '(integer integer)
       (lambda (x y) (tag (- x y))))
  (put 'mul '(integer integer)
       (lambda (x y) (tag (* x y))))
  (put 'div '(integer integer)
       (lambda (x y) (make-rational-number x y)))
  (put 'equ? '(integer integer) =)
  (put '=zero? '(integer)
       (lambda (x) (= 0 x)))
  (put 'make 'integer
       (lambda (x) (if (integer? x)
                       (tag x)
                       (error "non-integer value" x))))
  (put-coercion 'integer 'rational integer->rational)
  'done)

(install-integer-package)

(define (make-integer n)
  ((get 'make 'integer) n))