#lang racket

(define nil '())
(define (square x) (* x x))

(define (square-list items)
  (define (iter things answer)
    (if (null? things)
        answer
        (iter (cdr things)
              (cons (square (car things)) answer))))
  (iter items nil))

(square-list (list 1 2 3 4))

;; the result is in the reverse order because every time we
;; take the first elements to the start of the list with cons,
;; so for example the last element is added to the start of the list after being squared

(define (square-list2 items)
  (define (iter things answer)
    (if (null? things)
        answer
        (iter (cdr things)
              (cons answer
                    (square (car things))))))
  (iter items nil))

(square-list2 (list 1 2 3 4))

;; the result of square-list2 : '((((() . 1) . 4) . 9) . 16) so the order is correct but we add
;; lists instead of just the values
;; because we cons a list answer to the list 

;; but using append to append the two list with appending the new computed square to the
;; previously computed squares
(define (square-list3 items)
  (define (iter things answer)
    (if (null? things)
        answer
        (iter (cdr things)
              (append answer
                    (list (square (car things)))))))
  (iter items nil))

(square-list3 (list 1 2 3 4))