#lang racket

(require racket/trace)

(define (di i)
  (cond ((or (= i 0) (= i 2) (= i 3) (= i 5) (= i 6)) 1)
        ((= i 1) 2)
        ((= i 4) 4)
        ((= i 7) 6)
        (- (* 2 (di (- i 3))) (di (- i 6)))))

(define (cont-frac n d k)
  (define (frac i)
    (if (< i k)
        (/ (n i) (+ (d i) (frac (+ i 1))))
        (/ (n i) (d i))))
  (frac 1))

(trace di)
(trace cont-frac)

(cont-frac (lambda (i) 1.0) di 10) 