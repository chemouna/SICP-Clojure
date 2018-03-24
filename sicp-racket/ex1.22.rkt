#lang racket

(define (square x) (* x x))

(define (divides? a b) (= (remainder b a) 0))

(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor n 2))

(define (prime? n) (= n (smallest-divisor n)))

(define (start-prime-test n start-time)
  (if (prime? n)
      (report-prime n (- (current-inexact-milliseconds) start-time)) null ))

(define (report-prime n elapsed-time)
  (newline)
  (display n)
  (display " *** ")
  (display elapsed-time))

(define (timed-prime-test n)
  (start-prime-test n (current-inexact-milliseconds)))

(define (search-primes-larger n k)
  (cond ((= k 0) (newline))
        ((prime? n)
         (timed-prime-test n) 
         (search-primes-larger (+ n 2) (- k 1))) 
        (else (search-primes-larger (+ n 1) k))))

(define (search-for-primes n k)
  (cond ((divides? 2 (+ n 1)) (search-primes-larger (+ n 2) k))
        (else (search-primes-larger (+ n 1) k))))

