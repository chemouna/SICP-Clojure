#lang racket

(define nil '())

(define (square x) (* x x))

(define (divides? a b) (= (remainder b a) 0))

(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor n 2))

(define (prime? n) (= n (smallest-divisor n)))

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

;; (unique-pairs 5)
;; '((5 1) (5 2) (5 3) (5 4) (4 1) (4 2) (4 3) (3 1) (3 2) (2 1))

;; or we can just use the code provided before
(define (unique-pairs-2 n)
   (flatmap
    (lambda (i)
      (map (lambda (j) (list i j))
           (enumerate-interval 1 (- i 1))))
    (enumerate-interval 1 n)))

(define (prime-sum? pair)
  (prime? (+ (car pair) (cadr pair))))

(define (make-pair-sum pair)
  (list (car pair) (cadr pair) (+ (car pair) (cadr pair))))

(define (prime-sum-pairs n)
  (map make-pair-sum
       (filter prime-sum?
               (unique-pairs n))))

(prime-sum-pairs 5)
