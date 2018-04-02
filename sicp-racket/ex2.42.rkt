#lang racket

(define nil '())

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

(define (enumerate-interval low high)
  (if (> low high)
      nil
      (cons low (enumerate-interval (+ low 1) high))))

(define (flatmap proc seq)
  (accumulate append nil (map proc seq)))

(define empty-board nil)

(define (make-position row col)
  (cons row col))

(define (position-row p)
  (car p))

(define (position-col p)
  (cdr p))

(define (adjoin-position row col positions)
  (append positions (list (make-position row col))))

(define (safe? col positions)
  (let ((kth-queen (list-ref positions (- col 1)))
        (other-queens (filter (lambda(q)
                                (not (= (position-col q) col)))
                              positions)))
  (define (attacks? q1 q2)
    (or (= (position-row q1) (position-row q2))
        (= (position-col q1) (position-col q2))
        (= (abs (- (position-row q1) (position-row q2)))
           (abs (- (position-col q1) (position-col q2))))))

  (define (iter q board)
    (or (null? board)
        (and (not (attacks? q (car board)))
             (iter q (cdr board)))))

  (iter kth-queen other-queens)))
  
(define (queens board-size)
  (define (queen-cols k)
    (if (= k 0)
        (list empty-board)
        (filter
         (lambda (positions) (safe? k positions))
         (flatmap
          (lambda (rest-of-queens)
            (map (lambda (new-row)
                   (adjoin-position new-row k rest-of-queens))
                 (enumerate-interval 1 board-size)))
          (queen-cols (- k 1))))))
  (queen-cols board-size))

;(queens 1)
;(queens 2)
;(queens 3)
;(queens 4)
;(queens 5)
;(queens 6)
;(queens 7)
;(queens 8)
(queens 10)