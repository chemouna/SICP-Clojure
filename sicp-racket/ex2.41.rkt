#lang racket

(define nil '())

(define (enumerate-interval low high)
  (if (> low high)
      nil
      (cons low (enumerate-interval (+ low 1) high))))

(define (enumerate-interval-down high low)
  (if (= low high)
      nil
      (cons high (enumerate-interval-down (- high 1) low))))

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (flatmap proc seq)
  (accumulate append nil (map proc seq)))

(define (distinct x y z)
  (and (not (= x y)) (not (= x z)) (not (= y z))))

(define (ordered-triples n s) 
  (filter (lambda (t) (and (= (+ (car t) (cadr t) (caddr t)) s)
                         (distinct (car t) (cadr t) (caddr t))))
        (flatmap
         (lambda (k)
           (map (lambda (x) (append k (list x))) 
                (enumerate-interval 1 5)))
         (flatmap
          (lambda (i)
            (map (lambda (j) (list i j))
                 (enumerate-interval 1 n)))
          (enumerate-interval 1 n)))))


(ordered-triples 5 11)
  