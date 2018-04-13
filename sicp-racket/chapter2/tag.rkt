#lang racket

(provide type-tag contents attach-tag)

(define (type-tag datum)
  (cond ((number? datum) 'scheme-number)
        ((pair? datum) (car datum))
        (else (error "Bad tagged datum: TYPE-TAG" datum))))

(define (contents datum)
  (cond ((number? datum) datum)
        ((pair? datum) (cdr datum))
        (else (error "Bad tagged datum: CONTENTS" datum))))

(define (attach-tag . args)
  (cond ((= (length args) 1) (car args))
        ((= (length args) 2) (cons (car args) (cadr args)))
        (else (error "Bad arguments: ATTACH-TAG" args))))
