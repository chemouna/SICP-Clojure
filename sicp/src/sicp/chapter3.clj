(ns sicp.chapter3)


(defn error [message & args]
  (throw (RuntimeException. (clojure.string/join " " (cons message args)))))


;; Ex 3.1
(defn make-accumulator [x]
  (let [acc (atom x)
        acc-fn (fn[y] (do (swap! acc #(+ % y) @acc)))]))

(def A (make-accumulator 5))
(A 2)

;; Ex 3.1 : a slightly different method 
(defn make-accumulator2 [x]
  (let [acc (atom x)]
    (fn [y]
      (swap! acc #(+ % y)))))

(def A2 (make-accumulator 5))


;;
(defn make-queue [] (atom []))

(defn delete-queue! [q] (swap! q pop))

(defn insert-queue! [q e] (swap! q #(conj % e)))

(defn empty-queue? [q] (empty? @q))

(defn front-queue [q] (first @q))

(defn make-time-segment [time queue] [time queue])

(defn segment-time [s] (first s))

(defn segment-queue [s] (second s))

;; Exercice 3.28

(defn get-signal [wire]
  wire 'get-signal)

(defn make-agenda [] (atom {:time 0 :segments []}))

(defn current-time [agenda] (:time @agenda))

(defn set-current-time! [agenda time] (swap! agenda #(assoc % :time time)))

(defn segments [agenda] (:segments @agenda))

(defn set-segments! [agenda segments] (swap! agenda #(assoc % :segments segments)))

(defn first-segment [agenda] (first (segments agenda)))

(defn rest-segments [agenda] (rest (segments agenda)))

(defn empty-agenda? [agenda] (empty? (segments agenda)))

(def the-agenda (make-agenda))

(defn add-to-agenda! [time action agenda]
  (defn belongs-before? [segments]
    (or (empty? segments)
        (< (time (segment-time (first segments))))))

  (defn make-new-time-segment [time action]
    (let [q (make-queue)]
      (insert-queue! q action)
      (make-time-segment time q)))

  (defn add-to-segments! [segments]
    (if (= (segment-time (first segments)) time)
        (do
          (insert-queue!
           (segment-queue (first segments)) action)
          segments)
        (let [others (rest segments)
              others+segment (if (belongs-before? others)
                               (cons (make-new-time-segment time action) others)
                               (add-to-segments! others))]
          (if (empty? others)
            others+segment
            (cons (first segments) others+segment)))))

      (let [new-segments (add-to-segments! (segments agenda))]
          (set-segments! agenda new-segments)))

(defn remove-first-agenda-item! [agenda]
  (let [q (segment-queue (first-segment agenda))]
    (delete-queue! q)
    (if (empty-queue? q)
        (set-segments! agenda (rest-segments agenda)))))

(defn first-agenda-item [agenda]
  (if (empty-agenda? agenda)
    (error "Agenda is empty -- FIRST-AGENDA-ITEM")
    (let [first-seg (first-segment agenda)]
      (set-current-time! agenda (segment-time first-seg))
      (front-queue (segment-queue first-seg)))))


(defn after-delay [delay action]
  (add-to-agenda! (+ delay (current-time the-agenda))
                  action
                  the-agenda))

(def or-gate-delay 5)

(defn set-signal! [wire new-value]
  ((wire 'set-signal!) new-value))

(defn add-action! [wire action-procedure]
  ((wire 'add-action!) action-procedure))

(defn or-gate [a1 a2 output]
  (defn logical-or [s1 s2]
    (condp = [s1 s2]
      [1 1] 1
      [1 0] 1
      [0 1] 1
      [0 0] 0
      (error "Invalid signals " s1 " " s2)))

  (defn or-action-procedure []
    (let [new-value (logical-or (get-signal a1) (get-signal a2))]
      (after-delay or-gate-delay
                   (fn []
                     (set-signal! output new-value)))))
  (add-action! a1 or-action-procedure)
  (add-action! a2 or-action-procedure)
  'ok)

(logical-or 0 0)
