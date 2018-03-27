#lang racket

(sort '(7883 9099 6729 2828 7754)
      (lambda (x y) (< (modulo x 100)(modulo y 100))))

(map (lambda (x) (* x x)) '(1 2 3))

(define sum 0)
(for-each (lambda (x) (set! sum (+ sum x))) '(1 2 3))

(define (reduce f coll)
  (if (null? (cdr coll))
      (car coll)
      (f (car coll) (reduce f (cdr coll)))))

(reduce + '(1 2 3))

(apply + 1 2 3 '(4 5))
(apply - 100 '(5 12))