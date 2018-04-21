
(ns sicp.chapter2.term-test
  (:use clojure.test)
  (:use sicp.chapter2.term))

(deftest test-make-term-order
  (is (= (order (make-term 2 3)) 2)))

(deftest test-make-term-coeff
  (is (= (coeff (make-term 3 5)) 5)))

(deftest test-adjoin-term
  (is (= (adjoin-term (make-term 2 3) the-empty-termlist) '((2 3))))
  (is (= (adjoin-term (make-term 1 5) '((3 2))) '((1 5) (3 2))))
  (is (= (adjoin-term (make-term 4 6) '((5 4) (7 2))) '((4 6) (5 4) (7 2)))))

(deftest test-add-terms
  (is (= (add-terms '((2 3)) '((3 7)))))
  (is (= (add-terms '() '((5 7))) '((5 7)))))

(deftest test-mul-term-by-all-terms
  (is (= (mul-term-by-all-terms '(2 3) '((3 5) (1 3))) '((5 15) (3 9)))))

(deftest test-mul-terms
  (is (= (mul-terms '((2 3)) '((3 7))) '((5 21))))
  (is (= (mul-terms '() '())))) 

(deftest test-=zero-terms?
  (is (=zero-terms? '()))
  (is (not (=zero-terms? '((make-term (2 3)))))))
