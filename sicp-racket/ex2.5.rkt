(define (list-ref items n)
  (if (= n 0)
(car items)
(list-ref (cdr items) (- n 1)))) (define squares (list 1 4 9 16 25)) (list-ref squares 3)#lang racket

(require racket/trace)

(define (divides? a b) (= (remainder b a) 0))

(define (get-exp x n m)
         (if (divides? m x) (get-exp (/ x m) n m)
             (exact-floor (log x n))))

;; get-exp can be implemented differently in this way
(define (get-exp2 n d)
  (define (iter x result)
    (if (= 0 (remainder x d))
        (iter (/ x d) (+ 1 result))
        result))
  (iter n 0))
  
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