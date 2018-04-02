#lang racket

(define (equal? a b)
  (cond ((and (pair? a) (pair? b) (= (car a) (car b)))
         (if (and (pair? (cdr a)) (pair? (cdr b)))
             (equal? (cdr a) (cdr b))
             #t))
        (else (eq? a b))))

(define (equal2? p1 p2)
  (cond ((and (null? p1) (null? p2)) #t)
        ((or (null? p1) (null? p2)) #f)
        ((and (pair? p1) (pair? p2))
         (and (equal? (car p1) (car p2))
              (equal? (cdr p1) (cdr p2))))
        ((or (pair? p1) (pair? p2)) #f)
        (else (eq? p1 p2))))

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (equal3? list1 list2) 
   (accumulate (lambda (x y) (and x y)) #t (map eq? list1 list2))) 

(equal? '(1 2) '(1 2))
(equal? '(1 2 3) '(1 2 3))
(equal? 3 4)
(equal? 4 4)
(equal? 4 '(1 2))

(display "---")
(newline)

(equal2? '(1 2) '(1 2))
(equal2? '(1 2 3) '(1 2 3))
(equal2? 3 4)
(equal2? 4 4)
(equal2? 4 '(1 2))
