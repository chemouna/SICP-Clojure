#lang racket

; this one solves ex 2.84 more generally by supporting multiple args

(require "tag.rkt")
(require "table.rkt")
(require "scheme-number.rkt")
(require "complex-number.rkt")
(require "real.rkt")
(require "rational-number.rkt")

(require racket/trace)
(require racket/block)

(define (all-but-last l)
  (cond ((empty? l) (error 'all-but-last "empty list"))
        ((empty? (rest l)) empty)
        (else (cons (first l) (all-but-last (rest l))))))

(define tower-of-types '(integer rational real complex))

(define (coerce-to target-type other-args result)
  (if (null? other-args)
      result
      (let* ((arg (car other-args))
             (orig-type (type-tag arg)))
        (if (eq? target-type orig-type)
            (coerce-to target-type (cdr other-args) (append result (list arg)))
            (let ((orig->target (get-coercion (type-tag arg) target-type)))
              (if (and orig->target (not (null? orig->target)))
                  (coerce-to target-type (cdr other-args) (append result (list (orig->target arg))))
                  #f))))))

(define (find-highest-type type-tags)
  (define original-type-tags type-tags)
  (define (find-highest-helper type-tags tower)
     (if (null? type-tags)
         (find-highest-helper original-type-tags (all-but-last tower))
         (if (eq? (car type-tags) (last tower))
             (car type-tags)
            (find-highest-helper (cdr type-tags) tower))))
  ;(trace find-highest-helper)
  (find-highest-helper type-tags tower-of-types))

(define (make-uniq list)
  (if (null? list)
      list
      (let ((head (car list))
            (tail (cdr list)))
        (if (memq head tail)
            (make-uniq tail)
            (cons head (make-uniq tail))))))

(define (apply-generic-helper op types args)
  (if (null? types)
      (error "No method for these types, and could not raise"
               (list op (map type-tag args)))
      (let ((coerced-args (coerce-to (find-highest-type types) args '())))
        (if coerced-args
            (block
             (displayln "---------") 
             (displayln coerced-args) 
             (displayln "---------") 
             (let ((proc (get op (map type-tag coerced-args))))
              (if (and proc (not (null? proc)))
                  (apply proc op (map contents coerced-args))
                  (error "No method for these types, and could not raise to the highest type"
                         (list op (map type-tag args))))))
            (apply-generic-helper op (cdr types) args)))))

(define (apply-generic op . args)
  (let* ((type-tags (map type-tag args))
         (proc (get op type-tags)))
         (if (and proc (not (null? proc)))
             (apply proc op (map contents args))
             (let ((uniq-types (make-uniq type-tags)))
               (if (> (length uniq-types) 1)
                   (apply-generic-helper op uniq-types args)
                   (error "No method for this type"
                           (list op type-tags)))))))

;(find-highest-type '(integer real rational real))

(define (multi-add x y z) (apply-generic 'multi-add x y z))

(multi-add (make-real 3.14159) (make-real 3.47) (make-complex-from-real-imag 1 7))


