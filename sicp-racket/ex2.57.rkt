#lang racket

(define (variable? x) (symbol? x))

(define (same-variable? v1 v2)
     (and (variable? v1) (variable? v2) (eq? v1 v2)))

(define (=number? exp num)
  (and (number? exp) (= exp num)))

;; todo find a way to simplify this expression
(define (make-sum a1 a2 . r)
  (cond ((=number? a1 0)
         (cond
           ((pair? r) (make-sum a2 (car r) (cdr r)))
           ((null? r) a2)
           (else (make-sum a2 (car r)))))
        ((=number? a2 0)
          (cond
           ((pair? r) (make-sum a1 (car r) (cdr r)))
           ((null? r) a1)
           (else (make-sum a1 (car r)))))
        ((and (number? a1) (number? a2))
          (cond ((pair? r) (make-sum (+ a1 a2) (car r) (cdr r)))
                ((null? r) (+ a1 a2))
                (else (+ a1 a2 r))))
        (else
         (cond ((pair? r) (list '+ a1 a2 (car r) (cdr r)))
                ((null? r) (list '+ a1 a2))
                (else (list '+ a1 a2 r))))))

(define (make-product m1 m2 . r)
  (cond
        ((null? m2) m1)
        ((or (=number? m1 0) (=number? m2 0)
             (and (not (null? r)) (=number? (car r) 0))) 0)
        ((=number? m1 1) (make-product m2 r))
        ((=number? m2 1) (make-product m1 r))
        ((and (number? m1) (number? m2))
         (cond ((pair? r) (make-product (* m1 m2) (car r) (cdr r)))
               ((null? r) (* m1 m2))
               (else (make-product (* m1 m2) (car r))) )) ;; im not the last cond is necessary
        (else
         (cond ((pair? r) (list '* m1 m2 (car r) (cdr r)))
               ((null? r) (list '* m1 m2))
               (else (list '* m1 m2 car r))))))
       

(define (sum? x)
     (and (pair? x) (eq? (car x) '+)))

(define (addend s) (cadr s))

(define (augend s) (cddr s))

(define (product? x)
     (and (pair? x) (eq? (car x) '*)))

(define (multiplier p) (cadr p))

(define (multiplicand p) (cddr p))

(define (make-exponentiation b e)
  (cond ((=number? b 0) 0)
        ((or (=number? e 0) (=number? b 1)) 1)
        ((=number? e 1) b)
        ((and (number? b) (number? e)) (expt b e))
        (else (list '** b e))))

(define (exponentiation? x)
     (and (pair? x) (eq? (car x) '**)))

(define (base v) (cadr v))
(define (exponent v) (caddr v))

(define (deriv exp var)
  (cond ((number? exp) 0)
        ((variable? exp)
         (if (same-variable? exp var) 1 0))
        ((sum? exp)
         (make-sum (deriv (addend exp) var)
                   (deriv (augend exp) var)))
        ((product? exp)
         (make-sum
           (make-product (multiplier exp)
                         (deriv (multiplicand exp) var))
           (make-product (deriv (multiplier exp) var)
                         (multiplicand exp))))
        ((exponentiation? exp)
         (make-product (make-product (exponent exp)
                                      (make-exponentiation (base exp)
                                                           (make-sum  (exponent exp) -1)))
                       (deriv (base exp) var)))
         (error "unknown expression type -- DERIV" exp)))

;(deriv '(** x 6) 'x)

(deriv '(+ (* x x) y 3) 'x)
(deriv '(* x y (+ x 3)) 'x)

