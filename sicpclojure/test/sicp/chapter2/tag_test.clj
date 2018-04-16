
(ns sicp.chapter2.tag-test
  (:use clojure.test)
  (:use sicp.chapter2.tag))

(deftest test-type-tag
  (is (= (type-tag 2) 'scheme-number))
  (is (= (type-tag '('real 2.13)) '(quote real))))

(deftest test-contents
  (is (= (contents 2) 2))
  (is (= (contents '('real 2.13)) '(2.13))))

(deftest test-attach-tag
  (is (= (attach-tag 2) 2))
  (is (= (attach-tag '('real 2.13)) '((quote real) 2.13))))

(run-tests)
