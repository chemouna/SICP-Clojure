(ns sicp.chapter2.apply-generic-with-raise-test
  (:use clojure.test)
  (:use sicp.chapter2.apply-generic-with-raise)
  (:require [sicp.chapter2.rational :as rat]
            [sicp.chapter2.real :as real]
            [sicp.chapter2.integer :as int]
            [sicp.chapter2.complex :as c]))

(deftest test-raise-to
  (is (= (raise-to 'real 'rational (rat/make-rational-number 2 5)))))

; raise-values real (rational real integer) ((rational (2 5)) (real 2.5) (integer 5)) ())
; raise-to real rational (rational (2 5)))


(run-tests)
