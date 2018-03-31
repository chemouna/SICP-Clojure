#lang racket

(require racket/trace)

(define nil '())

(define (append list1 list2)
  (if (null? list1)
      list2
      (cons (car list1) (append (cdr list1) list2))))

(define (fringe x)
  (cond ((null? x) null)
        ((not (pair? x)) (list x))
        ((append (fringe (car x)) (fringe (cdr x))))))

(trace fringe)
         
(define x (list (list 1 2) (list 3 4)))
(fringe x)
  
