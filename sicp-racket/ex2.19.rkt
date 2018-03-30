#lang racket

(require racket/trace)

(define (length items)
  (if (null? items)
      0
      (+ 1 (length (cdr items)))))
(trace length)

(define (list-ref items n)
  (if (= n 0) 
      (car items)
      (list-ref (cdr items) (- n 1))))
(trace list-ref)

(define (first-denomination coins k)
  (list-ref coins k))
(trace first-denomination)

;; maybe we can get rid of the usage of k here 
(define (cc-helper amount coins k)
  (cond ((= amount 0) 1)
        ((or (< amount 0) (= k 0)) 0)
        (+ (cc-helper (- amount (first-denomination coins k)) coins k)
           (cc-helper amount coins (- k 1)))))
(trace cc-helper)

(define (cc amount coins)
  (cc-helper amount coins (- (length coins) 1)))
(trace cc)

(define us-coins (list 50 25 10 5 1))
(define uk-coins (list 100 50 20 10 5 2 1 0.5))

(cc 100 us-coins)


