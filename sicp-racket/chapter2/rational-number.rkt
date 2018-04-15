#lang racket

(require "table.rkt")
(require "tag.rkt")
(require "real.rkt")

;(require "tag-without-number-special-case.rkt")

(require racket/trace)

(provide make-rational-number)

; install procedure
(define (install-rational-number)
  ; internal procedures
  (define (numer x) (car x))
  (define (denom x) (cdr x))
  (define (make-rat n d)
    (let ((g (gcd n d)))
      (cons (/ n g) (/ d g))))
  (define (add-rat x y)
    (make-rat (+ (* (numer x) (denom y))
                 (* (numer y) (denom x)))
              (* (denom x) (denom y))))
  (define (sub-rat x y)
    (make-rat (- (* (numer x) (denom y))
                 (* (numer y) (denom x)))
              (* (denom x) (denom y))))
  (define (mul-rat x y)
    (make-rat (* (numer x) (numer y))
              (* (denom x) (denom y))))
  (define (div-rat x y)
    (make-rat (* (numer x) (denom y))
              (* (denom x) (numer y))))
  (define (equal? x y)
    (and (= (numer x) (numer y))
         (= (denom x) (denom y))))
  (define (=zero? x)
    (and (= (numer x) 0) (not (= (denom x) 0))))
  (define (rational->real r)
    (make-real (/ (numer r) (denom r))))
  ; interface to rest of the system
  (define (tag x) (attach-tag 'rational x))
  (put 'add '(rational rational)
       (lambda (x y) (tag (add-rat x y))))
  (put 'sub '(rational rational)
       (lambda (x y) (tag (sub-rat x y))))
  (put 'mul '(rational rational)
       (lambda (x y) (tag (mul-rat x y))))
  (put 'div '(rational rational)
       (lambda (x y) (tag (div-rat x y))))
  (put 'make 'rational
       (lambda (n d) (tag (make-rat n d))))
  (put 'equal? '(rational rational) equal?)
  (put '=zero? '(rational) =zero?)
  (put-coercion 'rational 'real rational->real)
  'done)

; install
(install-rational-number)

(define (make-rational-number n d)
  ((get 'make 'rational) n d))

(trace make-rational-number)
