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

;(provide apply-generic)

(define (no-method op type-tags)
  (error "No method for these types"
         (list op type-tags)))

(define tower-of-types '(integer rational real complex))

(define (find-highest-type l)
  (define (filter-type t f)
    (cond ((null? f) '())
          ((eq? (car f) t) (filter-type t (cdr f)))
          (else (cons (car f) (filter-type t (cdr f))))))
  (define (find-highest highest remaining-tower remaining-list)
    (cond ((null? remaining-list) highest)
          ((null? remaining-tower)
           (error "Cannot find highest type from non-tower types -- FIND-HIGHEST-TYPE"
                  remaining-list))
          (else (find-highest (car remaining-tower)
                              (cdr remaining-tower)
                              (filter-type (car remaining-tower) remaining-list)))))
  (find-highest #f tower-of-types l))

(define (raise-to type value)
  (cond ((eq? type (type-tag value)) value)
        ((memq type tower-of-types) (raise-to type (raise value)))
        (else (error "Cannot raise to non-tower type -- RAISE-TO"
                     (list type tower-of-types)))))

(define (raise-all-to type values)
  (if (null? values)
      '()
      (cons (raise-to type (car values)) (raise-all-to type (cdr values)))))

(define (apply-generic op . args)
  (let* ((type-tags (map type-tag args))
         (proc (get op type-tags)))
    (if (and proc (not (null? proc)))
        (apply proc (map contents args))
        (if (> (length args) 1)
            (let* ((highest-type (find-highest-type type-tags))
                   (mapped-args (raise-all-to highest-type args))
                   (mapped-types (map type-tag mapped-args))
                   (mapped-proc (get op mapped-types)))
              (if mapped-proc
                  (apply mapped-proc (map contents mapped-args))
                  (error
                   "No method for these types -- APPLY-GENERIC"
                   (list op type-tags))))
            (no-method op type-tags)))))

(apply-generic 'addd (make-real 3.14159) (make-rational-number 3 4) (make-complex-from-real-imag 1 7))