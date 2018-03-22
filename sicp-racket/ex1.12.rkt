#lang racket

(define (pascal i j)
  (if (or (= j 1) (= i 1) (= j i))
      1
      (+ (pascal (sub1 i) (sub1 j))
         (pascal (sub1 i) j))))
