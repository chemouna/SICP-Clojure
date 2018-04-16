#lang racket

(require "tag.rkt")
;(require "tag-without-number-special-case.rkt")
(require "table.rkt")
(require "real.rkt")
(require "complex-number.rkt")
(require "rational-number.rkt")
(require "integer.rkt")

(require racket/trace)
(require racket/block)

;; contains part of a solution of ex 2.84

(provide apply-generic)

(define (no-method op type-tags)
  (error "No method for these types"
         (list op type-tags)))

(define tower-of-types '(integer rational real complex))

(define (higher type-tags args)
  (define (higher-helper type-tags args types)
    (cond ((eq? (car type-tags) (car types)) (list (cadr type-tags) (cadr args)))
          ((eq? (cadr type-tags) (car types)) (list (car type-tags) (car args)))
          (else (higher-helper type-tags args (cdr types)))))
  ;(trace higher-helper)
  (higher-helper type-tags args tower-of-types))

(define (lower higher type-tags args)
  (if (eq? (car higher) (car type-tags))
      (list (cadr type-tags) (cadr args))
      (list (car type-tags) (car args))))

(define (apply-with-successive-raising op type-tags args)
  (let* ((h (higher type-tags args))
         (l (lower h type-tags args))
         (lower->higher (get-coercion (car l) (car h))))  
         (if (not (null? lower->higher))
             (block
              (display "h: ") (display h) (newline)
              (display "l: ") (display l) (newline) 
              (display "cadr h: ") (display (cadr h)) (newline)
              (display "cadr l: ") (display (cadr l)) (newline)
              (apply-generic op (cadr h) (lower->higher (contents (cadr l)))))
             (no-method op type-tags))))

#|
(define (apply-with-coercion op type-tags type1 type2 a1 a2)
  (let ((t1->t2 (get-coercion type1 type2))
        (t2->t1 (get-coercion type2 type1)))
        (cond (t1->t2 (apply-generic op (t1->t2 a1) a2))
              (t2->t1 (apply-generic op a1 (t2->t1 a2)))
              (else (no-method op type-tags)))))
|#

(define (apply-generic op . args)
  (let ((type-tags (map type-tag args)))
    (let ((proc (get op type-tags)))
      (if (not (null? proc))
          (apply proc (map contents args))
          (if (= (length args) 2) 
              (apply-with-successive-raising op type-tags args)
              (no-method op type-tags))))))

(trace apply-generic)
(trace apply-with-successive-raising)
(trace lower)
(trace higher)


(define (add x y) (apply-generic 'add x y))

;(add (make-real 3.14159) (make-complex-from-real-imag 1 7))

;(add (make-rational-number 1 2) (make-rational-number 1 4))

;(add (make-integer 42) (make-rational-number 1 4))