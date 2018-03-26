#lang racket

;; loop through all coprime numbers or get all coprime numbers
(define (are-coprimes? a b)
  (= (gcd a b) 1))

(define (coprimes-less n)
   (define (is-coprime-to-n? x)
    (are-coprimes? x n))
  (filter is-coprime-to-n? (range 1 (- n 1))))

;; fermat test


;; check that a predicate is valid for all elements in a list

;; racket lambda inside lambda (also for scheme)

;; anything related to charmichael numbers

;; checking that a number is composite ?
