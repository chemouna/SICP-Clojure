#lang racket

(require racket/trace)

(define (even? n)
   (= (remainder n 2) 0))

(define (odd? n) (not (even? n)))

(define (length items)
  (if (null? items)
      0
      (+ 1 (length (cdr items)))))

(define (append list1 list2)
  (if (null? list1)
      list2
      (cons (car list1) (append (cdr list1) list2))))

(define (simple-filter pred lst res)
  (cond ((null? lst) res)
        ((pred (car lst)) (simple-filter pred (cdr lst) (append (list (car lst)) res)))
        (else (simple-filter pred (cdr lst) res))))

(define (only-even lst)
  (simple-filter even? lst '()))

(define (only-odd lst)
  (simple-filter odd? lst '()))

(define (same-parity x . y)
  (if (even? x) (reverse (append (only-even y) (cons x '())))
      (reverse (append (only-odd y) (cons x '())))))

;(trace even?)
;(trace odd?)
;(trace length)
;(trace simple-filter)
;(trace only-even)

(same-parity 1 2 3 4 5 6 7)
(same-parity 2 3 4 5 6 7)

;; solution 2
(define (same-parity2 a . z)
   (define (iter items answer)
     (if (null? items)
         answer
         (iter (cdr items)
               (if (= (remainder (car items) 2)
                      (remainder a 2))
                   (append answer (list (car items)))
                   answer))))
   (iter z (list a)))

;(same-parity 1 2 3 4 5 6 7)
; (1 3 5 7)
;(same-parity 2 3 4 5 6 7)
; (2 4 6)
