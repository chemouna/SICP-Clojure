#lang racket

(require racket/trace)

(define (divides? a b) (= (remainder b a) 0))

(define (get-exp x n m)
         (if (divides? m x) (get-exp (/ x m) n m)
             (exact-floor (log x n))))

(define (cons a b)
  (* (expt 2 a) (expt 3 b)))

(define (car z)
  (get-exp z 2 3))

(define (cdr z)
  (get-exp z 3 2))

;; (trace cdr)
;; (trace car)
;; (trace cons)
;; (trace get-exp)