#lang racket

(define (reverse items)
  (if (null? items)
      items
      (append (reverse (cdr items)) (list (car items)))))

(define (deep-reverse x)
  (cond ((or (null? x) (not (pair? x))) x) 
        ((append (deep-reverse (cdr x)) (list (deep-reverse (car x))) ))))

(define x (list (list 1 2) (list 3 4)))
x
;; ((1 2) (3 4))
(reverse x)
;; ((3 4) (1 2))
(deep-reverse x)
;; ((4 3) (2 1))
