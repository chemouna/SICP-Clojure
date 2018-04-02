#lang racket

(require (planet "sicp.ss" ("soegaard" "sicp.plt" 2 1)))

(define (square-of-four tl tr bl br)
  (lambda (painter)
    (let ((top (beside (tl painter) (tr painter)))
          (bottom (beside (bl painter) (br painter))))
      (below bottom top))))

(define (split d1 d2)
  (lambda (painter n)
    (if (= n 0)
      painter
      (let ((smaller ((split d1 d2) painter (- n 1))))
        (d1 painter (d2 smaller smaller))))))

(define right-split (split beside below))
(define up-split (split below beside))

;; Make changes to the square limit of wave

;; a/ Add some segments to the primitive wave painter of exercise (add a smile for example)

(define wave
 (segments->painter (list
  (make-segment
   (make-vect 0.006 0.840)
   (make-vect 0.155 0.591))
  (make-segment
   (make-vect 0.006 0.635)
   (make-vect 0.155 0.392))
  (make-segment
   (make-vect 0.304 0.646)
   (make-vect 0.155 0.591))
  (make-segment
   (make-vect 0.298 0.591)
   (make-vect 0.155 0.392))
  (make-segment
   (make-vect 0.304 0.646)
   (make-vect 0.403 0.646))
  (make-segment
   (make-vect 0.298 0.591)
   (make-vect 0.354 0.492))
  (make-segment ; left face
   (make-vect 0.403 0.646)
   (make-vect 0.348 0.845))
  (make-segment
   (make-vect 0.354 0.492)
   (make-vect 0.249 0.000))
  (make-segment
   (make-vect 0.403 0.000)
   (make-vect 0.502 0.293))
  (make-segment
   (make-vect 0.502 0.293)
   (make-vect 0.602 0.000))
  (make-segment
   (make-vect 0.348 0.845)
   (make-vect 0.403 0.999))
  (make-segment
   (make-vect 0.602 0.999)
   (make-vect 0.652 0.845))
  (make-segment
   (make-vect 0.652 0.845)
   (make-vect 0.602 0.646))
  (make-segment
   (make-vect 0.602 0.646)
   (make-vect 0.751 0.646))
  (make-segment
   (make-vect 0.751 0.646)
   (make-vect 0.999 0.343))
  (make-segment
   (make-vect 0.751 0.000)
   (make-vect 0.597 0.442))
  (make-segment
   (make-vect 0.597 0.442)
   (make-vect 0.999 0.144))
  (make-segment ; eye
   (make-vect 0.395 0.916)
   (make-vect 0.410 0.916))
  (make-segment ; smile
   (make-vect 0.376 0.746)
   (make-vect 0.460 0.790)))))

; (paint wave)

;; b/ Change the pattern constructed by corner-split (for example, by using only one copy of the
;; up-split and right-split images instead of two).
(define (corner-split painter n)
  (if (= n 0)
      painter
      (let ((up (up-split painter (- n 1)))
            (right (right-split painter (- n 1)))
            (corner (corner-split painter (- n 1))))
          (beside (below painter (beside up up))
                  (below (below right right) corner)))))

; (paint (corner-split einstein 2))

;; c/ Modify the version of square-limit that uses square-of-four so as to assemble the corners
;; in a different pattern (you might make the big Mr. Rogers look outward from each corner of
;; the square)

(define (square-limit-old painter n)
  (let ((combine4 (square-of-four flip-horiz identity
                                  rotate180 flip-vert)))
    (combine4 (corner-split painter n))))

(define (square-limit painter n)
  (let ((combine4 (square-of-four identity flip-horiz
                                  flip-vert rotate180)))
    (combine4 (corner-split painter n))))

(paint (square-limit-old wave 0))
(paint (square-limit wave 0))

(paint (square-limit-old wave 1))
(paint (square-limit wave 1))
