
(ns sicp.chapter2.polar-test
  (:use sicp.chapter2.polar)
  (:use clojure.test))

(deftest test-make-from-real-imag
  (is (= (real-part (make-from-real-imag 2 3)) 2.0))
  (is (= (imag-part (make-from-real-imag 2 30)) 30.0)))

(deftest test-make-from-mag-ang
  (is (= (magnitude (make-from-mag-ang 2 30)) 2))
  (is (= (angle (make-from-mag-ang 2 3)) 3)))

(run-tests)


