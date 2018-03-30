#lang racket

(define (length items)
  (if (null? items)
      0
      (+ 1 (length (cdr items)))))

(define (last-pair lst)
  (if (or (null? lst) (= (length lst) 1))
      lst
      (last-pair (cdr lst))))

(last-pair (list 23 72 149 34))
(last-pair (list ))
(last-pair (list 1))
(last-pair (list 1 2))
;; (34)
