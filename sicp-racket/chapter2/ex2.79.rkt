#lang racket

(require "tag.rkt")
(require "apply-generic.rkt")
(require "scheme-number.rkt")
(require "rational-number.rkt")
(require "complex-number.rkt")

(define (equ? x y)
  (apply-generic 'equal? x y))

(define sn1 (make-scheme-number 2))
(define sn2 (make-scheme-number 2))
(define sn3 (make-scheme-number 3))

(define rn1 (make-rational-number 2 3))
(define rn2 (make-rational-number 2 3))
(define rn3 (make-rational-number 2 4))

(define cn1 (make-complex-from-real-imag 2 3))
(define cn2 (make-complex-from-real-imag 2 3))
(define cn3 (make-complex-from-real-imag 2 4))

;(equ? sn1 sn2)
;(equ? sn1 sn3)

;(equ? rn1 rn2)
;(equ? rn1 rn3)

;(equ? cn1 cn2)
;(equ? cn1 cn3)

;Lessons from this ex:
; - how adding an operation is done in such a modular system and how easy it is 
; - tagging of multiple parameters needs to be done for each one (f.ex (complex complex) as a tag for equal? parameters in complex-number package)
; - rasing the question of whether its a better abstraction to put the definition of equal? in rectangular and polar packages or its better to put 
;   it in the complex number package (See here http://community.schemewiki.org/?sicp-ex-2.79 for an example) 

