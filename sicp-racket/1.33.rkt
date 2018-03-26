#lang racket

(require math/number-theory)
(require racket/trace)

(define (filtered-accumulate combiner null-value term a next b predicate)
  (define (new-next a)
    (define na (next a))
    (if (predicate na) na (next na)))
  (if (> a b) null-value
      (combiner (term a) (filtered-accumulate combiner null-value term (new-next a) next b predicate))))
      

;; sum of squares of the prime numbers in the interval a to b
(define (sum-squares-primes a b)
  (define (square x) (* x x))
  (define (inc x) (+ x 1)) ;; this next can be improved given what we know about primes kx+v ....
  (filtered-accumulate + 0 square a inc b prime?))

;; (trace filtered-accumulate)
;; (trace sum-squares-primes)

(sum-squares-primes 1 2) ;; 1 + 4 = 5

(sum-squares-primes 1 3) ;; 1 + 4 + 9 = 14
