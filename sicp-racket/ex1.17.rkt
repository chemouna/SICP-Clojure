#lang racket

(define (double x)
   (+ x x))

(define (halve x)
   (/ x 2))

(define (even? n)
   (= (remainder n 2) 0))

;; recursive process
(define (* a b)
   (if (= b 0)
       0
       (+ a (* a (- b 1)))))

;; iterative process
(define (fast-mult a b)
   (cond ((= b 0) 0)
         ((= b 1) a)
         ((even? b) (double (fast-mult a (halve b))))
         (else (+ a (fast-mult a (- b 1))))))

