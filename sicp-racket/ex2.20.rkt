#lang racket

(define (even? n)
   (= (remainder n 2) 0))

(define (length items)
  (if (null? items)
      0
      (+ 1 (length (cdr items)))))

(define (append list1 list2)
  (if (null? list1)
      list2
      (cons (car list1) (append (cdr list1) list2))))

(define (simple-filter pred lst res)
  (cond ((null? lst) res)
        ((= (length lst) 1) (append (car lst) res))
        ((pred (car lst)) (simple-filter pred (cdr lst) (append (car lst) res)))
        (simple-filter pred (cdr lst) res)))

(define (only-even lst)
  (simple-filter even? lst '()))

(define (only-odd lst)
  (simple-filter even? lst '()))

(define (same-parity x . y)
  (if (even? x) (only-even y)
      (only-odd y)))

(same-parity 1 2 3 4 5 6 7)
; (1 3 5 7)
(same-parity 2 3 4 5 6 7)
; (2 4 6)
