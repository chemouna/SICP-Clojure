#lang racket

(require racket/trace)

(define (even? n)
   (= (remainder n 2) 0))

(define (odd? n) (not (even? n)))

(define (length items)
  (if (null? items)
      0
      (+ 1 (length (cdr items)))))

(define (simple-filter pred lst res)
  (cond ((null? lst) res)
        ((pred (car lst)) (simple-filter pred (cdr lst) (append res (list (car lst)))))
        (else (simple-filter pred (cdr lst) res))))

(define (only-even lst)
  (simple-filter even? lst '()))

(define (only-odd lst)
  (simple-filter odd? lst '()))

(define (same-parity x . y)
  (if (even? x) (cons x (only-even y))
      (cons x (only-odd y))))

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

(same-parity2 1 2 3 4 5 6 7)
(same-parity2 2 3 4 5 6 7)
