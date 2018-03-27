#lang racket

(define (cont-frac n d k)
  (define (frac i)
    (if (< i k)
        (/ (n i) (+ (d i) (frac (+ i 1))))
        (/ (n i) (d i))))
  (frac 1))

(define (tan-cf x k)
  (define (n i)
    (if (= i 1) x (* (- x) x)))
  (define (d i)
    (- (* 2 i) 1))
  (cont-frac n d k))
  
(tan (/ pi 6))
(tan-cf (/ pi 6) 10)

;(tan (/ pi 4))
;(tan-cf (/ pi 4) 10)

;(tan (/ pi 3))
;(tan-cf (/ pi 3) 10)