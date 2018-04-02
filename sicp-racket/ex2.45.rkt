#lang racket

(require (planet "sicp.ss" ("soegaard" "sicp.plt" 2 1)))

(define (square-of-four tl tr bl br)
  (lambda (painter)
    (let ((top (beside (tl painter) (tr painter)))
          (bottom (beside (bl painter) (br painter))))
      (below bottom top))))

(define (split d1 d2)
  (lambda (painter n)
    (if (= n 0)
      painter
      (let ((smaller ((split d1 d2) painter (- n 1))))
        (d1 painter (d2 smaller smaller))))))

(define right-split (split beside below))
(define up-split (split below beside))
