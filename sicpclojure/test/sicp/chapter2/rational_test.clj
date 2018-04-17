
(ns sicp.chapter2.rational-test
  (:require [clojure.test]
            [sicp.chapter2.tag]
            [sicp.chapter2.rational :as rat]))

;; todo : fix this 
(deftest test-numer
  (is (= (rat/numer (contents (rat/make-rational-number 2 3))) 2)))

(test-numer)
;(test-vars [#'the-ns/the-test])

(comment "
(deftest test-denom
  )
")

(comment "
(deftest test-add-rational-number
  (is (rat/equal?
       (rat/add-rat
        (rat/make-rational-number 2 4)
        (rat/make-rational-number 1 4))
       (rat/make-rational-number 3 4))))
")

;(run-tests)
