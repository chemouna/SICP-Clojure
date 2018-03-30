#lang racket

(define (reverse lst)
  (define (rev-iter l rl)
    (if (null? l)
        rl
        (rev-iter (cdr l) (cons (car l) rl))))
  (rev-iter lst '()))

(reverse (list 1 4 9 16 25))
;; (25 16 9 4 1)

;; solution 2
(define (append list1 list2)
  (if (null? list1)
      list2
      (cons (car list1) (append (cdr list1) list2))))

(define (reverse2 items)
  (if (null? items)
      items
      (append (reverse2 (cdr items)) (list (car items)))))

(reverse2 (list 1 4 9 16 25))