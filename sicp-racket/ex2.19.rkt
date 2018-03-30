#lang racket

(require racket/trace)

(define (first-denomination coins)
  (car coins))

(define (no-more? lst) (null? lst))

(define (cc amount coin-values)
  (cond ((= amount 0) 1)
        ((or (< amount 0) (no-more? coin-values)) 0)
        (else
         (+ (cc amount (cdr coin-values))
            (cc (- amount
                   (first-denomination coin-values))
                coin-values)))))


(define us-coins (list 50 25 10 5 1))
(define uk-coins (list 100 50 20 10 5 2 1 0.5))

;(trace length)
;(trace first-denomination)
;(trace cc)

(cc 100 us-coins)
(cc 100  (list 1 10 5 50 25))

;; The order of the coins does not affect the result, because we check
;; all possible combinations but the efficiency can vary depending on
;; the order of the input.

