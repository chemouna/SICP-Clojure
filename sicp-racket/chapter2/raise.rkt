#lang racket

(require "tag.rkt")
;(require "tag-without-number-special-case.rkt")
(require "table.rkt")

(require racket/trace)

(provide raise)

(define tower-of-types '(integer rational real complex))

(define (apply-raise v types)
  (cond ((null? types)
         (error "Type not found in the tower-of-types"
                  (list v tower-of-types)))
        ((eq? (type-tag v) (car types))
         (if (null? (cdr types))
             v
             (let ((raise-proc (get-coercion (type-tag v) (cadr types))))
               (if raise-proc
                     (raise-proc (contents v))
                     (error "No coercion procedure found for types"
                            (list (type-tag v) (cadr types)))))))
        (else (apply-raise v (cdr types)))))

(define (raise v)
  (apply-raise v tower-of-types))

(trace raise)