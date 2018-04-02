#lang racket

(define (equals? a b)
  (cond ((and (pair? a) (pair? b) (= (car a) (car b)))
         (if (and (pair? (cdr a)) (pair? (cdr b)))
             (equals? (cdr a) (cdr b))
             #t))
        ((or (and (pair? a) (not (pair? b))) (and (pair? b) (not (pair? a)))) #f)
        ((= a b) #t)
        (else #f)))

(equals? '(1 2) '(1 2))
(equals? '(1 2 3) '(1 2 3))
(equals? 3 4)
(equals? 4 4)
(equals? 4 '(1 2))
