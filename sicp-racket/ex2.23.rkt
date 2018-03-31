#lang racket

(require racket/trace)

(define (for-each f items)
  (cond
    ((= (length items) 1) (f (car items)))
    ((> (length items) 1) (f (car items)) (for-each f (cdr items)))))

(define (display-element x)
  (newline) (display x))

(for-each display-element (list 57 321 88))

(define (for-each2 proc items) 
  (let ((items-cdr (cdr items))) 
    (proc (car items)) 
     (if (not (null? items-cdr)) 
         (for-each2 proc items-cdr) 
         true)))

(for-each2 display-element (list 57 321 88))

(define (for-each3 proc items) 
  (cond ((not (null? items)) 
          (proc (car items)) 
          (for-each3 proc (cdr items))))) 

(for-each3 display-element (list 57 321 88))

(define (for-each4 proc items) 
  (if (not (null? items)) 
      (begin
        (proc (car items)) 
        (for-each4 proc (cdr items)))
      #t)) 

(for-each4 display-element (list 57 321 88))

;; this one is weird but cute :)
(define (for-each5 f items) 
   (define (iter f items . cur) 
     (if (null? items) 
         #t 
         (iter f 
               (cdr items) 
               (f (car items))))) 
   (iter f items))

(for-each5 display-element (list 57 321 88))