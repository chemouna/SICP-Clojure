#lang racket

(require math/number-theory)
(require racket/trace)

(define (filtered-accumulate combiner null-value term a next b predicate)
  (define (new-next a)
    (define na (next a))
    (if (predicate na) na (next na)))
  (if (> a b) null-value
      (combiner (term a) (filtered-accumulate combiner null-value term (new-next a) next b predicate))))
      
(define (inc x) (+ x 1))

;; sum of squares of the prime numbers in the interval a to b
(define (sum-squares-primes a b)
  (define (square x) (* x x))
  (filtered-accumulate + 0 square a inc b prime?))

;(sum-squares-primes 1 2) ;; 1 + 4 = 5
;(sum-squares-primes 1 3) ;; 1 + 4 + 9 = 14

;; product of all the positive integers less than n that are relatively prime to n
;; (i.e., all positive integers i < n such that GCD(i,n) = 1).
(define (product-pos-coprime n)
  (define (identity x) x)
  (define (coprime-n? x) (coprime? n x))
  (filtered-accumulate * 1 identity 2 inc (- n 1) coprime-n?))

; (product-pos-coprime 7)
