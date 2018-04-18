
(ns sicp.chapter2.complex-test
  (:use clojure.test)
  (:use sicp.chapter2.complex)
  (:use sicp.chapter2.tag))

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
          (make-from-real-imag 2 3)
          (make-from-real-imag 3 4))
         '(rectangular (5 5)))))

(deftest test-addd
  (is (= (addd (make-from-real-imag 2 3) (make-from-real-imag 1 4) (make-from-real-imag 1 2)))))

(run-tests)
