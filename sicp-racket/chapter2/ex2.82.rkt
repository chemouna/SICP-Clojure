#lang racket

(require "tag.rkt")
(require "table.rkt")

(define (coerce-to target-type other-args result)
  (if (null? other-args)
      result
      (let* ((arg (car other-args))
            (orig-type (type-tag arg)))
        (if (eq? target-type orig-type)
            (coerce-to target-type (cdr other-args) (append result (list arg)))
            (let ((orig->target (get-coercion orig-type target-type)))
              (if orig->target 
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
      (let ((coerced-args (coerce-to (car coercion-types) (cdr coercion-types) '())))
        (if coerced-args
            (let ((proc (get op (map type-tag coerced-args))))
              (if proc
                  (apply proc op (map contents coerced-args))
                  (apply-generic-helper op (cdr coercion-types) args)))
            (apply-generic-helper op (cdr coercion-types) args)))))


(define (apply-generic op . args)
  (let* ((type-tags (map type-tag args))
         (proc (get op type-tags)))
         (if proc
             (apply proc op (map contents args))
             (let ((uniq-types (make-uniq (type-tags))))
               (if (> (length uniq-types) 1)
                   (apply-generic-helper op uniq-types args)
                   (error "No method for this type"
                           (list op type-tags)))))))


            