
(ns sicp.chapter2.rectangular
  (:use sicp.chapter2.rectangular)
  (:use clojure.test))

(deftest test-make-from-real-imag
  (is (= (real-part (make-from-real-imag 2 3)) 2))
  (is (= (imag-part (make-from-real-imag 2 3)) 3)))

(deftest test-make-from-mag-ang
  (is (= (magnitude (make-from-mag-ang 2 30)) 2.0))
  (is (= (angle (make-from-mag-ang 2 3)) 3.0)))

(run-tests)
