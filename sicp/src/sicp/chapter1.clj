(ns sicp.chapter1
  (:use clojure.tools.trace)
  (:require [clj-time.core :as time]
            [clj-time.coerce :as tc]))

(clojure.tools.trace/trace-ns 'sicp.chapter1)

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

;;linear recursive

(defn cc [amount kinds-of-coins acc]
  (cond (= amount 0) (+ acc 1)
        (or (< amount 0) (empty? kinds-of-coins)) acc
        :else (cc (- amount (first kinds-of-coins))
                  kinds-of-coins
                  (cc amount (rest kinds-of-coins) acc))))

(defn count-change2 [amount]
  (cc amount '(50 25 10 5 1) 0))

;; (count-change2 100)


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

;; ex 1.11

;; using a recursive process
(defn f [n]
  (cond (< n 3) n
        :else (+ (* 3 (f (- n 3))) (* 2 (f (- n 2))) (f (- n 1)))))

;; using an iterative process
(defn f-iter [f1 f2 f3 cnt n]
  (cond (= cnt n) f3
        :else (f-iter f2 f3 (+ f3 (* 2 f2) (* 3 f1)) (+ cnt 1) n)))

(defn f2 [n]
  (cond (< n 3) n
        :else (f-iter 0 1 2 2 n)))

;; ex 1.12
;(defn pascal-rec [r c]
;  (cond (< r c) #f
;        (or (= r 0) (= c r)) 1
;        :else (+ (pascal-rec (- r 1) c) (pascal-rec (- r 1) (- c 1)))))

;; ex 1.16

;; recursive process version
(defn square [x]
  (* x x))

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

;; iterative process
(defn fast-expt-iter [b counter product]
  (cond (= counter 0) product
        (even? counter) (fast-expt-iter (square b) (/ counter 2) product)
        :else (fast-expt-iter b (- counter 1) (* b product))))

(defn fast-expt2 [b n]
  (fast-expt-iter b n 1))

;(fast-expt 2 4)
;(dotrace [fast-expt] (fast-expt 2 50))

;(fast-expt2 2 4)
;(dotrace [fast-expt2] (fast-expt2 2 50))

;; ex 1.17 and 1.18
(defn double-int [x]
  (+ x x))

(defn halve [x]
  (/ x 2))

;; recursive process version
(defn ^:dynamic mult [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))


(defn ^:dynamic fast-mult [a b]
  (cond (= b 0) 0
        (= b 1) a
        (even? b) (double-int (fast-mult a (halve b)))
        :else (+ a (fast-mult a (- b 1)))))

;; iterative process version
(defn ^:dynamic mult-iter [a b p]
  (cond (= b 0) p
        (even? b) (mult-iter (double-int a) (halve b) p)
        :else (mult-iter a (- b 1) (+ a p))))

(defn ^:dynamic mult2 [a b]
  (mult-iter a b 0))


;(dotrace [mult] (mult 4 12))
;(dotrace [mult-iter] (mult-iter 4 12 0))
;(dotrace [fast-mult] (fast-mult 4 12))

;; ex 1.19

;; getting used to fib functions
(defn fib [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (fib (- n 1))
                 (fib (- n 2)))))

;; a = fib n - 1 , b = fub n - 2
(defn fib-iter [a b count]
  (cond (= count 0) b
        :else (fib-iter (+ a b) a (- count 1))))

(defn fib2 [n]
  (fib-iter 1 0 n))

;; sol 1.19
(defn fib-iter2 [a b p q count]
  (cond (= count 0) b
        (even? count) (fib-iter2 a b (+ (* p p) (* q q)) (+ (* 2 p q) (* q q)) (/ count 2))
         :else (fib-iter2 (+ (* b q) (* a q) (* a p)) (+ (* b p) (* a q)) p q (- count 1))))

(defn fib3 [n]
  (fib-iter2 1 0 0 1 n))

;; section 1.2.5

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))

;; ex 1.22
(defn runtime []
  (System/currentTimeMillis))

(defn square [x]
  (* x x))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn divides? [div n]
   (= (mod n div) 0))

(defn find-divisor [n test]
  (cond (> (* test test) n) n
        (divides? test n) test
        :else (recur n (inc test))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn isPrime? [n]
  (= n (smallest-divisor n)))

(defn report-prime [elapsed-time]
  (print " *** ")
  (println elapsed-time))

(defn start-prime-test [n start-time]
  (cond (isPrime? n) (report-prime (- (runtime) start-time))))

(defn timed-prime-test [n]
  (println n)
  (start-prime-test n (runtime)))

(defn search-for-primes [start end]
  (cond (even? start) (search-for-primes (+ start 1) end)
        (< start end) (timed-prime-test start)
        :else (search-for-primes (+ start 2) end)))


(search-for-primes 1000 100000)

;; ex 1.23

(defn nextdiv [x]
  (if (= x 2) 3 (+ x 2)))

(defn find-divisor2 [n test]
  (cond (> (* test test) n) n
        (divides? test n) test
        :else (recur n (nextdiv test))))

(defn smallest-divisor2 [n]
  (find-divisor2 n 2))

;; ex 1.24

(defn fast-prime?
  [n counter]
  (let [a (+ 1 (rand (- n 1)))
        remainder (expmod a n n)]
    (cond (not= remainder a) false
          (and (= remainder a) (= counter 5)) true
          :else (fast-prime? n (+ counter 1)))))

(defn expmod
  [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn start-prime-test2
  [n]
  (if (fast-prime? n 0)
    (str "***")))

(defn timed-prime-test2 [n]
  (do (println n)
      (time (start-prime-test2 n))))

(timed-prime-test2 100)
(timed-prime-test2 10000)

;; ex 1.25

(defn expmod2
  [base exp m]
  (cond (= exp 0) 1
        :else (rem (fast-expt base exp) m)))

(defn fast-prime2?
  [n counter]
  (let [a (+ 1 (rand (- n 1)))
      remainder (expmod2 a n n)]
  (cond (not= remainder a) false
        (and (= remainder a) (= counter 5)) true
        :else (fast-prime2? n (+ counter 1)))))

(defn start-prime-test3
  [n]
  (if (fast-prime2? n 0)
    (str "***")))

(defn timed-prime-test3 [n]
  (do (println n)
      (time (start-prime-test3 n))))

;; (timed-prime-test3 100) ;; slower than the previoys version which took 0.44781 ms

;; ex 1.27
(defn fermat-test [n a]
  (= (expmod a n n) a))

(defn fermat-full [n]
  (defn iter [a]
    (cond (= a 1) true
          (not (fermat-test n a)) false
          :else (iter (- a 1))))
  (iter (- n 1)))

(fermat-full 561)
(fermat-full 1105)
(fermat-full 1729)
(fermat-full 2465)
(fermat-full 2821)
(fermat-full 6601)

;; ex 1.28
(defn quot-rem [m n]
  [(quot m n) (rem m n)])

;; (quot-rem 1212121 2)

(defn findds-iter [d m]
  (let [s (quot m 2) r (rem m 2)]  ;; im not sure if quot and rem here are inversed ?
    (if (= r 1) [d m]
        (findds-iter (+ 1 d) s)))) 

;; (findds-iter 2 4)

(defn findds [n]
  (findds-iter 0 n))

;; (findds 4)

(defn iter [coll n]
  (cond
    (empty? coll) false
    (= (first coll) 1) false
    (= (first coll) (- n 1)) true
    :else (iter (rest coll) n)))

;;(iter (range 1 10) 3)

(defn square-mod [n a]
  (rem (* a a) n))

;; (square-mod 3 17)

(defn miller-rabin-pt [n a]
  (let [
        [d s] (findds (- n 1))
        b0 (expmod a s n)
        b (take d (iterate #(square-mod n %) b0))]
    (cond
      ;; (or (<= a 1) (>= a (- n 1))) ;; todo: handle returning an error
      (< n 2) false
      (even? n) false
      (or (= b0 1) (= b0 (- n 1))) true
      (empty? b) false
      :else (iter (rest b) n))))


(map #(miller-rabin-pt 1212121 %) [5432 1265 87532 8765 26])

