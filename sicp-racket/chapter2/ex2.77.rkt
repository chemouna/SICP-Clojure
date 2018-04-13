#lang racket

(require "apply-generic.rkt")
(require "complex-number-package.rkt")

;; we had to add this because magnitude is not exported from complex-number-package
(define (magnitude z) (apply-generic 'magnitude z))

(magnitude (make-complex-from-real-imag 3 4))

;; First failure is because each operation was defined only for (rectangular) and (polar). So that applying these
;; operations to (complex) type failed.

;; calling magintude z will magnitude of complex which is
;; (define (magnitude z) (apply-generic 'magnitude z)) 
;; and since is a rectangular calls rectangular's definition of magnitude 
;;  (define (magnitude z)
;;    (sqrt (+ (square (real-part z))
;;             (square (imag-part z))))) 
    
;; z = 3 + 4i so mag = sqrt (9 + 16) = sqrt (25) = 5
    
;; so how many times is apply-generic called ? -> 2 times 

;; what are they trying try to teach with this example ? 

;; the lesson i think is just showing us how a call goes through these layers of abstraction

