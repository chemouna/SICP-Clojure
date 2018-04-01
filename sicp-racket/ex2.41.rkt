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
         (lambda (i)
           (map (lambda (x) (cons i x))
                (flatmap
                 (lambda (j)
                   (map (lambda (k) (list j k))
                        (enumerate-interval 1 (- j 1))))
                 (enumerate-interval 1 (- i 1)))))
         (enumerate-interval 1 n))))


;; lesson: instead of using car, caddr, caddr and doing the sum manualy,
;; just use accumulate its more readable
(define (ordered-triples-2 n)
   (flatmap (lambda (i)
      (flatmap (lambda (j)
         (map (lambda (k)
                (list i j k))
              (enumerate-interval 1 (- j 1))))
       (enumerate-interval 1 (- i 1))))
    (enumerate-interval 1 n)))

(define (make-triple-sum triple)
   (append triple (list (accumulate + 0 triple))))

(define (ordered-triples-sum-2 n s)
   (define (triple-sum? triple)
     (= s (accumulate + 0 triple)))
   (map make-triple-sum
        (filter triple-sum?
                (ordered-triples-2 n))))

(ordered-triples 5 10)
(ordered-triples-sum-2 5 10)

(ordered-triples 6 10)
(ordered-triples-sum-2 6 10)

(ordered-triples 12 12)
(ordered-triples-sum-2 12 12)
