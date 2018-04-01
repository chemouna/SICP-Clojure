#lang racket

(define nil '())

; given an integer n, generates the sequence of pairs (i,j) with 1< j< i< n
(define (enumerate-interval low high)
  (if (> low high)
      nil
      (cons low (enumerate-interval (+ low 1) high))))

(define (enumerate-interval-down high low)
  (if (= low high)
      nil
      (cons high (enumerate-interval-down (- high 1) low))))
 
(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (flatmap proc seq)
  (accumulate append nil (map proc seq)))

(define (unique-pairs n)
    (filter (lambda(x) (> (car x) (cadr x)))
      (flatmap (lambda(x) (map (lambda(y) (list x y))  (enumerate-interval 1 n)))
       (enumerate-interval-down n 1))))

(unique-pairs 5)
;; '((5 1) (5 2) (5 3) (5 4) (4 1) (4 2) (4 3) (3 1) (3 2) (2 1))
