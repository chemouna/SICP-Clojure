#lang racket

(require racket/trace)

(define (square x) (* x x))
(define (cube x) (* x x x))

(define (average x y)
  (/ (+ x y) 2))

(define (average-damp f)
  (lambda (x) (average x (f x))))

(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define (try guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try next))))
  (try first-guess))

(define (sqrt x)
  (fixed-point (average-damp (lambda (y) (/ x y))) 1.0))

(define (cube-root x)
  (fixed-point (average-damp (lambda (y) (/ x (square y)))) 1.0))

(define (repeated f n)
  (if (= n 1)
      f
      (compose f (repeated f (- n 1)))))

  
(define (log2 x) (/ (log x) (log 2)))

(define (nth-root n x)
  (fixed-point (repeated
                (average-damp (lambda (y) (/ x (expt y (- n 1)))))
                (floor (log2 n) ))
               1.0))

;(trace fixed-point)
;(trace average-damp)
;(trace nth-root)