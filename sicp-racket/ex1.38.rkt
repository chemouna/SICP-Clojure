#lang racket

(require racket/trace)

;(define (d2 i)
;  (cond ((or (= i 0) (= i 2) (= i 3) (= i 5) (= i 6)) 1)
;        ((= i 1) 2)
;        ((= i 4) 4)
;       ((= i 7) 6)
;        (- (* 2 (d2 (- i 3))) (d2 (- i 6)))))

(define (d i)
   (if (not (= 0 (remainder (+ i 1) 3)))
       1
       (* 2 (/ (+ i 1) 3))))

(define (cont-frac n d k)
  (define (frac i)
    (if (< i k)
        (/ (n i) (+ (d i) (frac (+ i 1))))
        (/ (n i) (d i))))
  (frac 1))

(+ 2 (cont-frac (lambda (i) 1.0) d 10)) 