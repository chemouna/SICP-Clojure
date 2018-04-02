#lang racket

(require (planet "sicp.ss" ("soegaard" "sicp.plt" 2 1)))

(define (transform-painter painter origin corner1 corner2)
  (lambda (frame)
    (let ((m (frame-coord-map frame)))
      (let ((new-origin (m origin)))
        (painter
         (make-frame new-origin
                     (vector-sub (m corner1) new-origin)
                     (vector-sub (m corner2) new-origin)))))))

(define (flip-vert painter)
  (transform-painter painter
                     (make-vect 0.0 1.0) ; new origin
                     (make-vect 1.0 1.0) ; new end of edge1
                     (make-vect 0.0 0.0))) ; new end of edge2

(define (flip-horiz painter)
  (transform-painter painter
                     (make-vect 1.0 0.0) ; new origin
                     (make-vect 0.0 0.0) ; new end of edge1
                     (make-vect 1.0 1.0))) ; new end of edge2

(paint einstein)
;(paint (flip-vert einstein))
(paint (flip-horiz einstein))
