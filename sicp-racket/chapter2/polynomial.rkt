#lang racket

(require "tag.rkt")
(require "table.rkt")
(require "term.rkt")
(require "generic-operations.rkt")
(require "scheme-number.rkt")
(require "rational-number.rkt")
(require "integer.rkt")
(require "complex-number.rkt")
(require "real.rkt")
(require racket/trace)

(provide make-polynomial)

(define (install-polynomial-package)
  ;; internal procedures
  ;; representation of poly

  (define (variable? e) (symbol? e))

  (define (same-variable? v1 v2)
    (and (variable? v1) (variable? v2) (eq? v1 v2)))
  
  (define (make-poly variable term-list)
    (cons variable term-list))

  (define (variable p) (car p))

  (define (term-list p) (cdr p))

  (define (=zero? p)
    (=zero-terms? (term-list p)))
  
  ;; <procedures adjoin-term ...coeff from text below> ;; continued on next page

  (define (add-poly p1 p2)
    (if (same-variable? (variable p1) (variable p2))
      (make-poly (variable p1)
                 (add-terms (term-list p1)
                            (term-list p2)))
      (error "Polys not in same var -- ADD-POLY"
             (list p1 p2))))
  
  (define (mul-poly p1 p2)
    (if (same-variable? (variable p1) (variable p2))
      (make-poly (variable p1)
                 (mul-terms (term-list p1)
                            (term-list p2)))
      (error "Polys not in same var -- MUL-POLY"
             (list p1 p2))))

  (define (sub-poly p1 p2)
    (add-poly p1 (negate p2)))

  
  ;; interface to rest of the system
  
  (define (tag p)
    (attach-tag 'polynomial p))

  (define (make-polynomial var terms)
    (tag (make-poly var terms)))
  
  (put 'add '(polynomial polynomial)
      (lambda (p1 p2) (tag (add-poly p1 p2))))

  (put 'mul '(polynomial polynomial)
      (lambda (p1 p2) (tag (mul-poly p1 p2))))

  (put 'make 'polynomial make-polynomial)

  (put '=zero? '(polynomial) =zero?)
  'done)

(install-polynomial-package)

(define (make-polynomial var terms)
  ((get 'make 'polynomial) var terms))

#|
 ; tests 
 (=zero? (make-polynomial 'x '()))
 (=zero? (make-polynomial 'x (list (list 4 (make-integer 3))
                                    (list 2 (make-integer 1))
                                    (list 0 (make-real 2.3)))))
 (=zero? (make-polynomial 'x (list (list 3 (make-real 0))
                                    (list 2 (make-rational-number 0 4))
                                    (list 1 (make-integer 0)))))
|#