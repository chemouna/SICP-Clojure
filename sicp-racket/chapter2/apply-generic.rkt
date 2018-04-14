#lang racket

(require "table.rkt")
(require "tag.rkt")
(require "scheme-number.rkt")

;(require racket/trace)

(provide apply-generic)

(define (apply-generic op . args)
  (let ((type-tags (map type-tag args)))
    (let ((proc (get op type-tags)))
      (if proc
          (apply proc (map contents args))
          (error
           "No method for these types: APPLY-GENERIC"
           (list op type-tags))))))

;(trace apply-generic)