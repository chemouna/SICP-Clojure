
(ns sicp.chapter2.table-test
  (:use clojure.test)
  (:use sicp.chapter2.table))

(deftest test-putt-gett
  (putt 'add '(real real) #(+ %1 %2))
  (putt 'multi '(real real) #(* %1 %2))
  (is (= ((gett 'add '(real real)) 1 2) 3))
  (is (= ((gett 'multi '(real real)) 4 3) 12)))

(deftest test-put-get-coercion
  (put-coercion 'integer 'real  #(+ %1 0.0)) ;; not sure about proc here
  (is (= ((get-coercion 'integer 'real) 2) 2.0)))

(run-tests)
