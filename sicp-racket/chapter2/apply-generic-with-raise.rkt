#lang racket

(require "tag.rkt")
;(require "tag-without-number-special-case.rkt")
(require "table.rkt")
(require racket/trace)

;; contains part of a solution of ex 2.84

(provide apply-generic)

(define (no-method op type-tags)
  (error "No method for these types"
         (list op type-tags)))

(define tower-of-types '(integer rational real complex))

(define (higher type1 type2)
  (define (higher-helper type1 type2 types)
    (cond ((eq? type1 (car types)) type2)
          ((eq? type2 (car types)) type1)
          (else (higher-helper type1 type2 (cdr types)))))
  (trace higher-helper)
  (higher-helper type1 type2 tower-of-types))

(define (apply-with-coercion op type-tags type1 type2 a1 a2)
  (let ((t1->t2 (get-coercion type1 type2))
        (t2->t1 (get-coercion type2 type1)))
        (cond (t1->t2 (apply-generic op (t1->t2 a1) a2))
              (t2->t1 (apply-generic op a1 (t2->t1 a2)))
              (else (no-method op type-tags)))))

(define (apply-generic op . args)
  (let ((type-tags (map type-tag args)))
    (let ((proc (get op type-tags)))
      (if proc
          (apply proc (map contents args))
          (if (= (length args) 2) 
              (let ((type1 (car type-tags))
                    (type2 (cadr type-tags))
                    (a1 (car args))
                    (a2 (cadr args)))
                (if (not (eq? type1 type2))
                    (apply-with-coercion op type-tags type1 type2 a1 a2)
                (no-method op type-tags)))
               (no-method op type-tags))))))

(trace apply-generic)
