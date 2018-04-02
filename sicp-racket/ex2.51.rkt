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

(define (beside painter1 painter2)
  (let ((split-point (make-vect 0.5 0.0)))
    (let ((paint-left
           (transform-painter painter1
                              (make-vect 0.0 0.0)
                              split-point
                              (make-vect 0.0 1.0)))
          (paint-right
           (transform-painter painter2
                              split-point
                              (make-vect 1.0 0.0)
                              (make-vect 0.5 1.0))))
      (lambda (frame)
        (paint-left frame)
        (paint-right frame)))))


(define (below painter1 painter2)
  (let ((split-point (make-vect 0.0 0.5)))
    (let ((paint-up
           (transform-painter painter1
                              split-point
                              (make-vect 1 0.5)
                              (make-vect 0 1)))
          (paint-down
           (transform-painter painter2
                              (make-vect 0 0)
                              (make-vect 1 0)
                              split-point)))
      (lambda (frame)
        (paint-up frame)
        (paint-down frame)))))

(define (below-2 painter1 painter2)
  (flip-horiz
   (flip-vert
    (rotate90 (beside
               (rotate90 painter1)
               (rotate90 painter2))))))

(define (below-3 painter1 painter2)
   (rotate90 (beside (rotate270 painter1) (rotate270 painter2))))

(define (below-4 painter1 painter2) 
   (rotate270 (beside (rotate90 painter2) (rotate90 painter1))))

(paint (below einstein einstein))
(paint (below-2 einstein einstein))
(paint (below-3 einstein einstein))
(paint (below-4 einstein einstein))