#lang racket

;; recursive process
(define (cont-frac n d k)
  (define (frac i)
    (if (< i k)
        (/ (n i) (+ (d i) (frac (+ i 1))))
        (/ (n i) (d i))))
  (frac 1))

;; iterative process
(define (cont-frac2 n d k)
  (define (frac-iter i result)
    (if (= i 0) result
        (frac-iter (- i 1) (/ (n i) (+ (d i) result)))))
  (frac-iter (- k 1) (/ (n k) (d k))))

;; for phi
(cont-frac (lambda (i) 1.0) (lambda (i) 1.0) 8)
(cont-frac2 (lambda (i) 1.0) (lambda (i) 1.0) 8)