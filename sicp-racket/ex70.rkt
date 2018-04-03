#lang racket

(require racket/trace)

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

(define pairs '((A 2) (NA 16) (BOOM 1) (SHA 3) (GET 2) (YIP 9) (JOB 2) (WAH 1)))

;(encode '(GET A JOB) (generate-huffman-tree pairs))
;(encode '(SHA NA NA NA NA NA NA NA NA) (generate-huffman-tree pairs))

;(encode '(WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP) (generate-huffman-tree pairs))
;(encode '(SHA BOOM) (generate-huffman-tree pairs))

(define fifties-song-tree
    (generate-huffman-tree pairs))
     
(define fifties-song-encoding
  (encode '(GET A JOB
            SHA NA NA NA NA NA NA NA NA
            GET A JOB
            SHA NA NA NA NA NA NA NA NA
            WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP 
            SHA BOOM)
          fifties-song-tree))
(length  fifties-song-encoding)

;; How many bits are required for the encoding?
;; 87 bits are needed for the encoding

;; What is the smallest number of bits that would be needed to encode this song if we used
;; a fixed-length code for the eight-symbol alphabet?
;; we can encode the 8 symbols using 3 bits and in this message we have 36 symbols so 36 x 3 = 108
