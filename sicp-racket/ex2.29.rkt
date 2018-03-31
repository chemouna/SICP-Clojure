#lang racket

(require rackunit)
(require racket/trace)

(define (make-mobile left right)
  (list left right))

(define (left-branch m)
  (car m))

(define (right-branch m)
  (last m))

(define (make-branch length structure)
  (list length structure))

(define (branch-length b)
  (car b))

(define (branch-structure b)
  (last b))

(define example-left-branch
  (make-branch 3 4))

(define example-right-branch (make-branch 4
                                          (make-mobile
                                           (make-branch 1 2)
                                           (make-branch 1 1))))

(define (example-mobile)
  (make-mobile example-left-branch example-right-branch))

(check-equal? (left-branch (example-mobile)) example-left-branch)
(check-equal? (right-branch (example-mobile)) example-right-branch)
(check-equal? (branch-length (left-branch (example-mobile))) 3)
(check-equal? (branch-length (right-branch (example-mobile))) 4)
(check-equal? (branch-structure (left-branch (example-mobile))) 4)
(check-equal?
 (branch-structure (right-branch (example-mobile)))
 (make-mobile
  (make-branch 1 2)
  (make-branch 1 1)))

;; b/
(define (branch-weight b)
   (let ((structure (branch-structure b)))
     (if (pair? structure)
         (total-weight structure)
         structure)))

(define (total-weight tree)
  (cond ((null? tree) 0)
        ((not (pair? tree)) tree)
        (else (+ (branch-weight (left-branch tree))
                 (branch-weight (right-branch tree))))))

(define m (make-mobile (make-branch 1 1) (make-branch 1 2)))

;(trace total-weight)
(check-equal? (total-weight (example-mobile)) 7)

;; c/
(define (torque b)
  (* (branch-weight b) (branch-length b)))

(define (branch-balanced? b)
  (if (pair? (branch-structure b))
      (balanced? (branch-structure b))
      true))

(define (balanced? tree)
  (and (= (torque (left-branch tree)) (torque (right-branch tree)))
       (branch-balanced? (left-branch tree))
       (branch-balanced? (right-branch tree))))

(balanced? (example-mobile))

(define a (make-mobile (make-branch 2 3) (make-branch 2 3)))
(balanced? a)
;(length example-left-bra
nch)
;(branch-length (left-branch (example-mobile)))

