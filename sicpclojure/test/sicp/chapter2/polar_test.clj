
(ns sicp.chapter2.polar-test
  (:use sicp.chapter2.polar)
  (:use clojure.test)
  (:require [sicp.chapter2.table :as table]))

(deftest test-make-from-mag-ang
  (is (= ((table/gett 'magnitude '(polar)) '(2 30)) 2))
  (is (= ((table/gett 'angle '(polar)) '(2 3)) 3)))

(run-tests)


