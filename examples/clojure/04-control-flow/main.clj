;; Control Flow in Clojure
;; ======================
;; if, when, cond, case, loop/recur, doseq

(ns main)

;; ── if/when/unless ─────────────────────────────

(defn check-number [n]
  (cond
    (zero? n) "zero"
    (pos? n) "positive"
    :else "negative"))

(defn demo-if []
  (println "--- if/when/unless ---")
  (println (check-number 0))
  (println (check-number 5))
  (println (check-number -3))

  (when (even? 10)
    (println "10 is even"))
  (unless (even? 11)
    (println "11 is odd")))

;; ── cond ─────────────────────────────────────────────

(defn describe-grade [score]
  (cond
    (>= score 90) "A"
    (>= score 80) "B"
    (>= score 70) "C"
    (>= score 60) "D"
    :else "F"))

(defn demo-cond []
  (println "\n--- cond ---")
  (println "Score 85:" (describe-grade 85))
  (println "Score 95:" (describe-grade 95))
  (println "Score 55:" (describe-grade 55)))

;; ── case ───────────────────────────────────────────

(defn day-name [n]
  (case n
    1 "Monday"
    2 "Tuesday"
    3 "Wednesday"
    4 "Thursday"
    5 "Friday"
    6 "Saturday"
    7 "Sunday"
    :else "Unknown"))

(defn demo-case []
  (println "\n--- case ---")
  (println (day-name 1))
  (println (day-name 5))
  (println (day-name 8)))

;; ── loop/recur ─────────────────────────────────────

(defn factorial [n]
  (loop [acc 1, current n]
    (if (zero? current)
      acc
      (recur (* acc current) (dec current)))))

(defn demo-loop-recur []
  (println "\n--- loop/recur ---")
  (println "factorial(5) =" (factorial 5))
  (println "factorial(10) =" (factorial 10)))

;; ── doseq (side effects) ─────────────────────────

(defn demo-doseq []
  (println "\n--- doseq ---")
  (doseq [n (range 1 6)]
    (println "Processing" n))
  (println "doseq done"))

;; ── for (comprehension) ───────────────────────────

(defn demo-for []
  (println "\n--- for comprehension ---")
  (let [evens (for [n (range 1 11)
                  :when (even? n)]
              n)]
    (println "Even numbers 1-10:" evens)))

;; ── map/filter/reduce (functional iteration) ───────

(defn demo-map-filter-reduce []
  (println "\n--- map/filter/reduce ---")

  ;; map
  (def squares (map #(* % %) (range 1 6)))
  (println "Squares of 1-5:" squares)

  ;; filter
  (def evens (filter even? (range 1 11)))
  (println "Even numbers 1-10:" evens)

  ;; reduce
  (def total (reduce + 0 (range 1 6)))
  (println "Sum of 1-5:" total)

  ;; take-while (early exit)
  (def until-5 (take-while #(< % 6) (range 1 100)))
  (println "Numbers 1-5:" until-5))

;; ── Summary ───────────────────────────────────────

(defn demo-summary []
  (println "\n--- Summary ---")
  (println "Clojure control flow:")
  (println "  - if: conditional with then/else")
  (println "  - when/unless: single-branch conditionals")
  (println "  - cond: multi-branch conditionals")
  (println "  - case: value-based branching")
  (println "  - loop/recur: tail-recursive loops")
  (println "  - doseq: iteration with side effects")
  (println "  - for: comprehension with :let/:when/:while")
  (println "  - map/filter/reduce: functional iteration")
  (println "  - Early exit: take-while, reduced, lazy seqs"))

(defn -main []
  (demo-if)
  (demo-cond)
  (demo-case)
  (demo-loop-recur)
  (demo-doseq)
  (demo-for)
  (demo-map-filter-reduce)
  (demo-summary))
