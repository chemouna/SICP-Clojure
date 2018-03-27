#lang racket

(define tolerance 0.00001)

(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define (try guess)
    (newline)
    (display guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try next))))
  (try first-guess))

(define (average x y)
  (/ (+ x y) 2))

;; without average damping
(fixed-point (lambda (x) (/ (log 1000) (log x))) 2.0) ;; 4.555532270803653
(expt 4.555532270803653 4.555532270803653) ;; 999.9913579312362

;; with average damping
(fixed-point (lambda (x) (average x (/ (log 1000) (log x)))) 2.0) ;; 4.555537551999825 <- this one is faster 
(expt 4.555537551999825 4.555537551999825) ;; 1000.0046472054871 <= this one is closer to 1000