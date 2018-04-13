#lang racket

(provide put get)

(define *op-table* (make-hash))
(define *coercion-table* (make-hash))

(define (put op type proc)
  (hash-set! *op-table* (list op type) proc))
(define (get op type)
  (hash-ref *op-table* (list op type) null))
