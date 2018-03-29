#lang racket

(define (make-interval x y)
  (cons x y))

(define (lower-bound i)
  (car i))

(define (upper-bound i)
  (cdr i))

(define (mul-interval x y)
  (let ((p1 (* (lower-bound x) (lower-bound y)))
        (p2 (* (lower-bound x) (upper-bound y)))
        (p3 (* (upper-bound x) (lower-bound y)))
        (p4 (* (upper-bound x) (upper-bound y))))
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(define (div-interval x y)
  (cond ((or (= (upper-bound y) 0) (= (lower-bound y) 0)) error "interval has 0")
        ((= (- (upper-bound y) (lower-bound y)) 0) "error: interval spans 0")
        ((mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))))



(define a (make-interval 1 0))
(define b (make-interval 0 10))
(div-interval a b)
(div-interval b a)

(define c (make-interval 50 50))
(define d (make-interval 50 100))
(div-interval d c)