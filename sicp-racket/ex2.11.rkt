#lang racket

(define (make-interval x y)
  (cons x y))

(define (lower-bound i)
  (car i))

(define (upper-bound i)
  (cdr i))

(define (mul-interval x y)
   (let ((xlo (lower-bound x))
         (xhi (upper-bound x))
         (ylo (lower-bound y))
         (yhi (upper-bound y)))
   (cond ((and (>= xlo 0)
               (>= xhi 0)
               (>= ylo 0)
               (>= yhi 0))
          ; [+, +] * [+, +]
          (make-interval (* xlo ylo) (* xhi yhi)))
         ((and (>= xlo 0)
               (>= xhi 0)
               (<= ylo 0)
               (>= yhi 0))
          ; [+, +] * [-, +]
          (make-interval (* xhi ylo) (* xhi yhi)))
         ((and (>= xlo 0)
               (>= xhi 0)
               (<= ylo 0)
               (<= yhi 0))
          ; [+, +] * [-, -]
          (make-interval (* xhi ylo) (* xlo yhi)))
         ((and (<= xlo 0)
               (>= xhi 0)
               (>= ylo 0)
               (>= yhi 0))
          ; [-, +] * [+, +]
          (make-interval (* xlo yhi) (* xhi yhi)))
         ((and (<= xlo 0)
               (>= xhi 0)
               (<= ylo 0)
               (>= yhi 0))
          ; [-, +] * [-, +]
          (make-interval (min (* xhi ylo) (* xlo yhi))
                         (max (* xlo ylo) (* xhi yhi))))
         ((and (<= xlo 0)
               (>= xhi 0)
               (<= ylo 0)
               (<= yhi 0))
          ; [-, +] * [-, -]
          (make-interval (* xhi ylo) (* xlo ylo)))
         ((and (<= xlo 0)
               (<= xhi 0)
               (>= ylo 0)
               (>= yhi 0))
          ; [-, -] * [+, +]
          (make-interval (* xlo yhi) (* xhi ylo)))
         ((and (<= xlo 0)
               (<= xhi 0)
               (<= ylo 0)
               (>= yhi 0))
          ; [-, -] * [-, +]
          (make-interval (* xlo yhi) (* xlo ylo)))
         ((and (<= xlo 0)
               (<= xhi 0)
               (<= ylo 0)
               (<= yhi 0))
          ; [-, -] * [-, -]
          (make-interval (* xhi yhi) (* xlo ylo))))))

(define (mul-interval2 x y) 
      (define (endpoint-sign i)  
        (cond ((and (>= (upper-bound i) 0) 
                    (>= (lower-bound i) 0)) 
               1) 
              ((and (< (upper-bound i) 0) 
                    (< (lower-bound i) 0)) 
               -1) 
              (else 0))) 
  
      (let ((es-x (endpoint-sign x)) 
            (es-y (endpoint-sign y)) 
            (x-up (upper-bound x)) 
            (x-lo (lower-bound x)) 
            (y-up (upper-bound y)) 
            (y-lo (lower-bound y))) 
  
        (if (and (= es-x 0) (= es-y 0)) 
          ; Take care of the exceptional condition where we have to test 
          (make-interval (min (* x-lo y-up) (* x-up y-lo)) 
                         (max (* x-lo y-lo) (* x-up y-up))) 
  
          ; Otherwise, select which value goes in which "slot". 
          (let ((a1 (if (and (<= es-y 0) (<= (- es-y es-x) 0)) x-up x-lo)) 
                (a2 (if (and (<= es-x 0) (<= (- es-x es-y) 0)) y-up y-lo)) 
                (b1 (if (and (<= es-y 0) (<= (+ es-y es-x) 0)) x-lo x-up)) 
                (b2 (if (and (<= es-x 0) (<= (+ es-x es-y) 0)) y-lo y-up))) 
            (make-interval (* a1 a2) (* b1 b2))))))
