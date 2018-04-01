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

(define (sum-odd-squares tree)
  (cond ((null? tree) 0)
        ((not (pair? tree))
         (if (odd? tree) (square tree) 0))
        (else (+ (sum-odd-squares (car tree))
                 (sum-odd-squares (cdr tree))))))

(define (even-fibs n)
  (define (next k)
(if (> k n) nil
        (let ((f (fib k)))
          (if (even? f)
              (cons f (next (+ k 1)))
              (next (+ k 1))))))
(next 0))

(define (fib n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        ((+ (fib (- n 1))
                 (fib (- n 2))))))

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (enumerate-interval low high)
  (if (> low high)
      nil
      (cons low (enumerate-interval (+ low 1) high))))

;(accumulate append
;  nil
;  (map (lambda (i)
;       (map (lambda (j) (list i j))
;                        (enumerate-interval 1 (- i 1))))
;       (enumerate-interval 1 n)))

(define (flatmap proc seq)
  (accumulate append nil (map proc seq)))

(define (prime-sum? pair)
  (prime? (+ (car pair) (cadr pair))))

(define (make-pair-sum pair)
  (list (car pair) (cadr pair) (+ (car pair) (cadr pair))))

(define (prime-sum-pairs n)
  (map make-pair-sum
       (filter prime-sum?
               (flatmap
                (lambda (i)
                  (map (lambda (j) (list i j))
                       (enumerate-interval 1 (- i 1))))
                (enumerate-interval 1 n)))))

(define (remove item s)
  (filter (lambda (x) (not (= x item)))
          s))

(define (permutations s)
  (if (null? s) ; empty set?
      (list nil) ; sequence containing empty set (flatmap (lambda (x)
      (flatmap
        (lambda(x)
          (map (lambda (p) (cons x p))
           (permutations (remove x s))))
        s)))

