#lang racket

(require "tag.rkt")
(require "table.rkt")

(define tower-of-types '(integer real rational complex))

(define (apply-raise v tower-types)
  (if (null? tower-types)
      (error "No types tower provided!")
      (if (eq? (type-tag v) (car tower-types))
          (if (null? (cdr tower-types))
              v
              (let ((raise-proc (get-coercion (type-tag v) (cadr tower-types))))
                (if raise-proc
                    (apply raise-proc (contents v))
                    (error "No coercion procedure found for types "
                            (list (type-tag v) (cadr tower-types)))))) ;; ?? comeback to this
          (apply-raise v (cdr tower-types)))))
               
(define (raise v)
  (apply-raise v tower-of-types))