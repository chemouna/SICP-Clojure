
(ns sicp.chapter2.generic-operations-test
  (:use [clojure.test]
        [sicp.chapter2.generic-operations])
  (:require [sicp.chapter2.integer :as int]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.rational :as rat]
            [sicp.chapter2.complex :as c]
            [sicp.chapter2.polynomial :as p]
            [sicp.chapter2.generic-term :as t]))

(deftest test-add
  (is (= (add (int/make-integer 2)
              (int/make-integer 3))
         (int/make-integer 5)))

  (is (= (add (rat/make-rational 2 3)
              (rat/make-rational 11 3))
         (rat/make-rational 39 9))))

(deftest test-mul
  (is (= (mul (real/make-real 2/3)
              (real/make-real 3/4))
         (real/make-real 1/2))))

(deftest test-zero?
  (is (=zero? (int/make-integer 0)))
  (is (=zero? (c/make-complex-from-real-imag 0 0))))

(deftest test-negate
  (is (= (negate (real/make-real 2.12)) (real/make-real (- 2.12)))))

;(def p1 (p/make-polynomial 'x '((t/make-term 2 1) (t/make-term 4 1))))
;(def p2 (p/make-polynomial 'x '((t/make-term 3 1) (t/make-term 9 1))))
;(def rf (rat/make-rational p2 p1))

;(add rf rf)
