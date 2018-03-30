#lang racket

(define (reverse lst)
  (define (rev-iter l rl)
    (if (null? l)
        rl
        (rev-iter (cdr l) (cons (car l) rl))))
  (rev-iter lst '()))

(reverse (list 1 4 9 16 25))
;; (25 16 9 4 1)

