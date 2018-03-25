#lang racket

(define (square x)
  (* x x))

(define (expmod base exp m)
  (cond ((= exp 0) 1)
        ((even? exp)
         (remainder (square (expmod base (/ exp 2) m))
                    m))
        (else
         (remainder (* base (expmod base (- exp 1) m))
                    m))))

(define (are-coprimes? a b)
  (= (gcd a b) 1))

(define (coprimes-less n)
   (define (is-coprime-to-n? x)
    (and (are-coprimes? x n) (not (= x 1))))
   (filter is-coprime-to-n? (range 1 (- n 1))))

(define (fermat-test n a)
  (= (expmod a (- n 1) n) 1))

(define (fermat-primality-test n)
  (define (pass-fermat-test? x)
    (fermat-test n x))
  (let ([cps (coprimes-less n)])
        (and (not (empty? cps)) (andmap pass-fermat-test? cps))))




