#lang racket

(define a 1)
(define b 1)

(define (foo x y)
  (+ x y y))

(define res (foo
             (begin (set! a (+ a 1))
                    a)
             (begin (set! b (* b 2))
                    b)))

(define v (cons a (cons b '())))

(cons res v)

(define applicative?
  (lambda ()
   (call/cc (lambda (exit)
    (let ((e (begin (display 'applicative) (exit #f))))
      (display 'not-applicative))))))

