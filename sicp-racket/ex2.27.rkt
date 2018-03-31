#lang racket

(define nil '())

(define (reverse items)
  (if (null? items)
      items
      (append (reverse (cdr items)) (list (car items)))))

(define (deep-reverse x)
  (if (not (pair? x)) x 
      (append (deep-reverse (cdr x)) (list (deep-reverse (car x))) )))

(define x (list (list 1 2) (list 3 4)))
x
;; ((1 2) (3 4))
(reverse x)
;; ((3 4) (1 2))
(deep-reverse x)
;; ((4 3) (2 1))

(define (deep-reverse-2 items)
  (cond ((null? items) null)
        ((pair? (car items))
         (append (deep-reverse-2 (cdr items))
                 (list (deep-reverse-2 (car items)))))
        (else
         (append (deep-reverse-2 (cdr items))
                 (list (car items))))))

(deep-reverse-2 x)

(define (deep-reverse-3 items) 
   (define (deep-rev-if-required item) 
     (if (not (pair? item)) 
         item 
         (deep-reverse-3 item))) 
   (define (deep-rev-imp items result) 
     (if (null? items) 
         result 
         (deep-rev-imp (cdr items) 
                       (cons (deep-rev-if-required (car items)) 
                             result)))) 
   (deep-rev-imp items nil))

(deep-reverse-3 x)
 
(define (deep-reverse-4 t) 
   (if (pair? t) 
       (reverse (map deep-reverse-4 t)) 
       t))

(deep-reverse-4 x)