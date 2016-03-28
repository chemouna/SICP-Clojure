(ns sicp)

; exercice 1.29

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn simp-integral
  [f a b n]
  (let [h (/ (- b a) n)]
    (letfn [(term [k] (* (f (+ a (* k h))) (if (even? k) 2 4)))]
      (/ (* h (+ a (sum term 1 inc n)) ) 3) )))

; how is this a linear recursion ? 

