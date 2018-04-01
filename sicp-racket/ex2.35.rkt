#lang racket

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (enumerate-tree tree)
   (cond ((null? tree) null)
         ((not (pair? tree)) (list tree))
        (else (append (enumerate-tree (car tree))
                       (enumerate-tree (cdr tree))))))

(define (count-leaves t)
  (accumulate +
              0
              (map (lambda(x)
                     (cond ((null? x) 0)
                            ((not (pair? x)) 1)
                            (count-leaves x) ) )
                   (enumerate-tree t))))

(define (count-leaves-2 t)
  (accumulate +
              0
              (map (lambda (x) 1)
                   (enumerate-tree t))))

(count-leaves (list))
(count-leaves-2 (list))

(count-leaves (list 1 2 3))
(count-leaves-2 (list 1 2 3))

(count-leaves (list 1 (list 1 2 3)))
(count-leaves-2 (list 1 (list 1 2 3)))

(count-leaves (list (list 1 2) (list 1 2 3) 1))
(count-leaves-2 (list (list 1 2) (list 1 2 3) 1))