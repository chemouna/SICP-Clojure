#lang racket

;; f(n) = n if n<3 and f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n >= 3.

;; using a recursive process
(define (f n)
  (if (< n 3) n
      (+ (* 3 (f (- n 3))) (* 2 (f (- n 2))) (f (- n 1)))))

;; using an iterative process
(define (f2 n)
  (if (< n 3) n
      (f-iter 0 1 2 2 n)))

(define (f-iter f1 f2 f3 count n)
  (if (= count n) f3
      (f-iter f2 f3 (+ f3 (* 2 f2) (* 3 f1)) (+ count 1) n)))