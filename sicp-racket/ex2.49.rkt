#lang racket

(require (planet "sicp.ss" ("soegaard" "sicp.plt" 2 1)))

;; a/
(define outline
  (segments->painter (list
                      (make-segment
                       (make-vect 0 0)
                       (make-vect 0 0.99))

                      (make-segment
                       (make-vect 0 0)
                       (make-vect 0.99 0))

                      (make-segment
                       (make-vect 0.99 0)
                       (make-vect 0.99 0.99))

                      (make-segment
                       (make-vect 0 0.99)
                       (make-vect 0.99 0.99)))))

(paint outline)

;; b/
(define X
  (segments->painter (list
                      (make-segment
                       (make-vect 0.99 0)
                       (make-vect 0 0.99))

                      (make-segment
                       (make-vect 0 0)
                       (make-vect 0.99 0.99)))))

(paint X)

;; c/
(define diamond
  (segments->painter (list
                      (make-segment
                       (make-vect 0 0.5)
                       (make-vect 0.5 0))

                      (make-segment
                       (make-vect 1 0.5)
                       (make-vect 0.5 0))

                      (make-segment
                       (make-vect 0.5 1)
                       (make-vect 0 0.5))

                      (make-segment
                       (make-vect 0.5 1)
                       (make-vect 1 0.5))
                     )))

(paint diamond)

;; d/
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
                       (make-segment
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
                        (make-vect 0.999 0.144)))))

(paint wave)