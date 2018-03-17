(ns sicp.chapter1)

(defn abs [n] (max n (- n)))

(defn good-enough? [guess x]
  (< (abs (- (* guess guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

;; Exercise 1.7
(defn new-good-enough? [old-guess new-guess]
  (< (/ (abs (- old-guess new-guess)) old-guess) 0.001))

;; Exercice 1.8
(defn cb-improve [guess x]
  (/ (+ (/ x (* guess guess)) (* 2 guess)) 3))

(defn cb-good-enough? [old-guess guess]
  (< (abs (- guess old-guess)) (* guess 0.001)))

(defn cb-iter [guess old-guess x]
  (if (cb-good-enough? old-guess guess)
    guess
    (cb-iter (cb-improve guess x) guess x)) )

(defn cb [x]
  (cb-iter 1.0 0.0 x))

;; count change

;; recursive process version
(defn first-denomination [kinds-of-coins]
	(cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
	(cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc (- amount
                        (first-denomination kinds-of-coins))
                     kinds-of-coins)
                 (cc amount
                     (- kinds-of-coins 1)))))

(defn count-change [amount]
	(cc amount 5))

;; (count-change 100)

;; an iterative process version

(defn cc-nothing [amount acc]
	acc)

(defn cc-pennies [amount acc]
	(cond (= amount 0) (+ 1 acc)
        (< amount 0) acc
        :else (cc-pennies (- amount 1)
                          (cc-nothing amount acc))))
(defn cc-nickels [amount acc]
	(cond (= amount 0) (+ 1 acc)
        (< amount 0) acc
        :else (cc-nickels (- amount 5)
                          (cc-pennies amount acc))))
(defn cc-dimes [amount acc]
	(cond (= amount 0) (+ 1 acc)
        (< amount 0) acc
        :else (cc-dimes (- amount 10)
                        (cc-nickels amount acc))))

(defn cc-quarters [amount acc]
	(cond (= amount 0) (+ 1 acc)
        (< amount 0) acc
        :else (cc-quarters (- amount 25)
                           (cc-dimes amount acc))))

(defn cc-fifties [amount acc]
	(cond (= amount 0) (+ 1 acc)
        (< amount 0) acc
        :else (cc-fifties (- amount 50)
                          (cc-quarters amount acc))))

(defn count-change-iter [amount]
	(cc-fifties amount 0))

;; (count-change-iter 100)

