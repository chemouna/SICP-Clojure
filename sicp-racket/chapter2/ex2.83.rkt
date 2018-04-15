#lang racket

(require "integer.rkt")
(require "real.rkt")
(require "rational-number.rkt")
(require "apply-generic-with-table-coercion.rkt")
(require "complex-number.rkt")
(require "table.rkt")
(require "raise.rkt")
;(require "tag-without-number-special-case.rkt")
(require "tag.rkt")

(require racket/trace)

#| ; Examples:
(raise (make-integer 2))
(raise (make-rational-number 3 4))
(raise (make-real 1.234))
(raise (make-real 3/4))
|#
