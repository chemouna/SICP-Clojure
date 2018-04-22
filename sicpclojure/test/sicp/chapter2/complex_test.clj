
(ns sicp.chapter2.complex-test
  (:use [clojure.test]
        [sicp.chapter2.complex]
        [sicp.chapter2.tag])
  (:require [sicp.chapter2.table :as table]))

;(type-tag (contents (make-complex-from-real-imag 2 3)))

(deftest test-make-complex-from-real-imag
  (let [c (make-complex-from-real-imag 2 3)]
    (is (= (type-tag c) 'complex))
    (is (= (contents c) '(rectangular (2 3))))))

(deftest test-make-complex-from-mag-ang
  (let [c (make-complex-from-mag-ang 2 3)]
    (is (= (type-tag c) 'complex))
    (is (= (contents c) '(polar (2 3))))))

(deftest test-add-complex
  (is (= (add-complex
          (make-complex-from-real-imag 2 3)
          (make-complex-from-real-imag 3 4))
         '(rectangular (5 5)))))

(deftest test-negate-complex
  (is (= (negate-complex (make-complex-from-real-imag 2 3))
         (make-complex-from-real-imag -2 3))))

