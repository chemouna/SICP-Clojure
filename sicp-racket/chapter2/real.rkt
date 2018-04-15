#lang racket

(require "tag.rkt")
(require "table.rkt")

(provide make-real)

; Real package
(define (install-real-package)
  (define (tag x)
    (attach-tag 'real x))    
  (put 'add '(real real)
       (lambda (x y) (tag (+ x y))))
  (put 'sub '(real real)
       (lambda (x y) (tag (- x y))))
  (put 'mul '(real real)
       (lambda (x y) (tag (* x y))))
  (put 'div '(real real)
       (lambda (x y) (tag (/ x y))))
  (put 'equ? '(real real) =)
  (put '=zero? '(real)
       (lambda (x) (= 0 x)))
  (put 'make 'real
       (lambda (x) (if (real? x)
                       (tag x)
                       (error "non-real value" x))))
  'done)

(define (make-real n)
  ((get 'make 'real) n))

