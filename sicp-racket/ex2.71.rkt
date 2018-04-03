#lang racket

(define (make-leaf symbol weight)
  (list 'leaf symbol weight))

(define (leaf? object)
  (eq? (car object) 'leaf))

(define (symbol-leaf x) (cadr x))

(define (weight-leaf x) (caddr x))

(define (left-branch tree) (car tree))
(define (right-branch tree) (cadr tree))
(define (symbols tree)
  (if (leaf? tree)
      (list (symbol-leaf tree))
      (caddr tree)))
(define (weight tree)
  (if (leaf? tree)
      (weight-leaf tree)
      (cadddr tree)))

(define (make-code-tree left right)
  (list left
        right
        (append (symbols left) (symbols right))
        (+ (weight left) (weight right))))

(define (adjoin-set x set)
  (cond ((null? set) (list x))
        ((< (weight x) (weight (car set))) (cons x set))
        (else (cons (car set)
                    (adjoin-set x (cdr set))))))

(define (encode-symbol symbol tree)
  (cond ((leaf? tree) (eq? symbol tree) '())
        ((memq symbol (symbols (left-branch tree)))
          (cons 0 (encode-symbol symbol (left-branch tree))))
        ((memq symbol (symbols (right-branch tree)))
         (cons 1 (encode-symbol symbol (right-branch tree))))
        (else (error "error: symbol not found in tree"))))

(define (encode message tree)
  (if (null? message)
      '()
      (append (encode-symbol (car message) tree)
              (encode (cdr message) tree))))

(define (make-leaf-set pairs)
  (if (null? pairs)
      '()
      (let ((pair (car pairs)))
        (adjoin-set (make-leaf (car pair)
                               (cadr pair)) 
                    (make-leaf-set (cdr pairs))))))

(define (successive-merge set)
  (if (null? (cddr set))
       set
       (successive-merge 
        (cons (make-code-tree (car set) (cadr set))
              (cddr set)))))

(define (generate-huffman-tree pairs)
  (successive-merge (make-leaf-set pairs)))

(define (print-huffman-tree tree)
	(define (print-indent n)
		(display " ")
		(if (= n 0)
			#t
			(print-indent (- n 1))))
	(define (print-leaf leaf depth)
		(print-indent depth)
		(display leaf)
		(newline))
	(define (print-branch branch depth)
		(cond ((leaf? branch) (print-leaf branch depth))
			  (else (print-branch (left-branch branch) (+ 1 depth))
			  		(print-branch (right-branch branch) (+ 1 depth)))))
	(print-branch tree 0))

(print-huffman-tree (generate-huffman-tree '((A 1) (B 2) (C 4) (D 8) (E 16))))
;       (leaf A 1)
;     (leaf B 2)
;    (leaf C 4)
;   (leaf D 8)
;  (leaf E 16)

;; format the output has 5 levels deep for n = 5

(display "     -----      ")
(newline)

(print-huffman-tree (generate-huffman-tree
                     '((A 1) (B 2) (C 4) (D 8) (E 16) (F 32) (G 64) (H 128) (K 256) (L 512))))

#|         (leaf A 1)
          (leaf B 2)
         (leaf C 4)
        (leaf D 8)
       (leaf E 16)
      (leaf F 32)
     (leaf G 64)
    (leaf H 128)
   (leaf K 256)
 (leaf L 512)
|#

;; the same for n = 10 we can see it has 10 levels deep 

;; in these trees In such a tree, the most frequent symbol will need 1 bit of encoding, and the least
;; frequent symbol will need n-1 bits of encoding (for n=5 E needs log(16) = 4 bits to encode it,
;; for n = 10, J needs log(512) = 9 bits to encode it). 

