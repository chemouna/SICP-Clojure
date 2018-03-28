#lang racket

(require racket/trace)

(define (iterative-improve is-good-enough? improve)
  (define (try guess)
     (if (is-good-enough? guess)
         guess
         (try (improve guess))))
  (lambda (x) (try x)))

(define (divides? a b) (= (remainder b a) 0))

(define (cons a b)
  (* (expt 2 a) (expt 3 b)))

(define (find-exp x n m)
  ((iterative-improve
   (lambda (guess) (and (integer? (log guess n)) (divides? n (log guess n))))
   (lambda (guess) (/ guess m))) x))

;; keep loging untill you get something that divides
(define (car z)
  (find-exp z 2 3))

(define (cdr z)
  (find-exp z 3 2))

(trace car)
(trace cons)
(trace find-exp)
(trace divides?)
(trace iterative-improve)