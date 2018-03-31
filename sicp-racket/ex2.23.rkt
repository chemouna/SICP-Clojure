#lang racket

(require racket/trace)

(define (for-each f items)
  (cond
    ((= (length items) 1) (f (car items)))
    ((> (length items) 1) (f (car items)) (for-each f (cdr items)))))

(define (display-element x)
  (newline) (display x))

(for-each display-element '(1 2))

(for-each display-element (list 57 321 88))