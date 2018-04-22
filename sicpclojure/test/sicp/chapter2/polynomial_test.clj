(ns sicp.chapter2.polynomial-test
  (:use [sicp.chapter2.polynomial]
        [clojure.test])
  (:require [sicp.chapter2.term :as t]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]))

(deftest test-make-poly
  (is (= (make-poly 'x (list (t/make-term 2 3))) '(x (2 3)))))

(deftest test-term-list
  (is (= (term-list (make-poly 'x (list (t/make-term 2 3)))) '((2 3)))))

(deftest test-zero?
  (is (=zero? (make-poly 'x '())))
  (is (=zero? (make-poly 'x (list (t/make-term 2 0)))))
  (is (not (=zero? (make-poly 'x (list (t/make-term 2 3))))))
  (is (not (=zero? (make-poly 'x (list (list 4 (int/make-integer 3))
                                       (list 2 (int/make-integer 1))
                                       (list 0 (real/make-real 2.3)))))))
  (is (=zero? (make-poly 'x (list (list 3 (real/make-real 0))
                                  (list 2 (rat/make-rational-number 0 4))
                                  (list 1 (int/make-integer 0)))))))

(deftest test-add-poly
  (is (= (add-poly (make-poly 'x (list (t/make-term 2 3)))
                   (make-poly 'y (list (t/make-term 5 7))))
         nil))

  ;; todo: once we have eq? for polynomials use it here
  (is (not (nil? (add-poly (make-poly 'x (list (t/make-term 2 3)))
                   (make-poly 'x (list (t/make-term 2 7))))))))

(deftest test-eq-poly?
  (is (eq-poly? (make-poly 'x '()) (make-poly 'x '())))
  (is (not (eq-poly? (make-poly 'x '()) (make-poly 'y '()))))
  (is (eq-poly? (make-poly 'x (list (t/make-term 2 3))) (make-poly 'x (list (t/make-term 2 3)))))

  (is (not (eq-poly? (make-poly 'x (list (t/make-term 2 3))) (make-poly 'x (list (t/make-term 5 3))))))

  (is (eq-poly? (make-poly 'x (list (t/make-term 2 3) (t/make-term 5 7)))
                (make-poly 'x (list (t/make-term 2 3) (t/make-term 5 7)))))

  (is (eq-poly? (make-poly 'x (list (t/make-term 2 -3)))
                (make-poly 'x (list (t/make-term 2 -3))))))

(deftest test-negate-poly
  (is (eq-poly? (negate-poly
                 (make-poly 'x (list (t/make-term 2 3) (t/make-term 5 7))))
                (make-poly 'x (list (t/make-term 2 -3) (t/make-term 5 -7)))))

  (is (eq-poly? (negate-poly
                 (make-poly 'x (list (t/make-term (int/make-integer 3) (rat/make-rational-number 2 5)))))
                (make-poly 'x (list (t/make-term (int/make-integer 3) (rat/make-rational-number (- 2) 5))))))


  (is (eq-poly? (negate-poly
                 (make-poly 'x (list
                                (t/make-term (real/make-real 2.13)
                                             (c/make-complex-from-real-imag 3 2))
                                (t/make-term (c/make-complex-from-real-imag 5 7)
                                             (rat/make-rational-number 2 5)))))
                (make-poly 'x (list
                               (t/make-term (real/make-real 2.13)
                                            (c/make-complex-from-real-imag -3 2))
                               (t/make-term (c/make-complex-from-real-imag 5 7)
                                            (rat/make-rational-number -2 5)))))))


;(is (eq-poly? (negate-poly
;               (make-poly 'x (list (t/make-term (real/make-real 2.12) (rat/make-rational-number 2 5)))))
;              (make-poly 'x (list (t/make-term (real/make-real (- 2.12)) (rat/make-rational-number (- 2) 5)))))))


(deftest test-sub-poly
  (is (= (sub-poly
          (make-poly 'x (list (t/make-term 3 5) (t/make-term 7 6)))
          (make-poly 'x (list (t/make-term 3 2) (t/make-term 7 4)))))))


