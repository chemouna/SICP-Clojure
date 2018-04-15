#lang racket

(require "tag.rkt")
(require "table.rkt")
(require "scheme-number.rkt")
(require "complex-number.rkt")

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

(define (make-uniq list)
  (if (null? list)
      list
      (let ((head (car list))
            (tail (cdr list)))
        (if (memq head tail)
            (make-uniq tail)
            (cons head (make-uniq tail))))))

(define (apply-generic-helper op coercion-types args)
  (if (null? coercion-types)
      (error "No method for these types, and could not coerce"
               (list op (map type-tag args)))
      (let ((coerced-args (coerce-to (car coercion-types) args '())))
        (if coerced-args
            (let ((proc (get op (map type-tag coerced-args))))
              (if (and proc (not (null? proc)))
                  (apply proc op (map contents coerced-args))
                  (apply-generic-helper op (cdr coercion-types) args)))
            (apply-generic-helper op (cdr coercion-types) args)))))


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


(define (multi-add x y z) (apply-generic 'multi-add x y z))

(define op1 'multi-add)

#|
(define args1 (list (make-scheme-number 1)
                     (make-scheme-number 2)
                     (make-scheme-number 3)))

(apply-generic-helper op1 '(scheme-number scheme-number scheme-number) args1)
(apply-generic op1 args1)
|# 

#|
(define args2 (list (make-scheme-number 1)
                     (make-complex-from-real-imag 2 1)
                     (make-scheme-number 3)))
(apply-generic op1 args2)
|#

#|
(define args3 (list (make-complex-from-real-imag 1 2)
                     (make-scheme-number 3)
                     (make-complex-from-real-imag 4 5)))
(apply-generic op1 args3)
|#

            