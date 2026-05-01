;;; Functions in Clojure
;;; ====================
;;; Functions are the primary abstraction in Clojure. They are first-class,
;;; can be composed, and are building blocks of all Clojure programs.

(ns main
  (:require [clojure.string :as str]))

;; ── Basic functions ──────────────────────────────────

(defn greet
  "A simple function with a docstring."
  [name]
  (str "Hello, " name "!"))

(println (greet "Atlas"))

;; Multi-arity (overloaded by argument count)
(defn power
  "Raise base to exponent. Defaults to squaring."
  ([base] (power base 2))
  ([base exp] (Math/pow base exp)))

(println "\n--- Multi-arity ---")
(println "(power 3) =" (power 3))
(println "(power 2 10) =" (power 2 10))

;; Variadic arguments
(defn sum [& nums]
  (reduce + 0 nums))

(println "(sum 1 2 3 4 5) =" (sum 1 2 3 4 5))

;; ── First-class functions ────────────────────────────

(println "\n--- First-class functions ---")

;; Functions are values — assignable, passable, storable
(def op +)
(println "(op 3 4) =" (op 3 4))

(def ops {:add + :sub - :mul * :div /})
(println "(:mul ops) 3 4 =" ((:mul ops) 3 4))

;; ── Anonymous functions ──────────────────────────────

(println "\n--- Anonymous functions ---")

;; fn form
(println "((fn [x] (* x x)) 5) =" ((fn [x] (* x x)) 5))

;; Reader macro #()
(println "(#(* % %) 5) =" (#(* % %) 5))
(println "(#(+ %1 %2) 3 4) =" (#(+ %1 %2) 3 4))

;; ── Higher-order functions ───────────────────────────

(println "\n--- Higher-order functions ---")

(def numbers (range 1 11))

;; map
(println "(map #(* % %) numbers) =" (vec (map #(* % %) numbers)))

;; filter
(println "(filter even? numbers) =" (vec (filter even? numbers)))

;; reduce (fold)
(println "(reduce + numbers) =" (reduce + numbers))
(println "(reduce * 1 numbers) =" (reduce * 1 numbers))

;; remove (opposite of filter)
(println "(remove even? numbers) =" (vec (remove even? numbers)))

;; some, every?
(println "(some even? numbers) =" (some even? numbers))
(println "(every? pos? numbers) =" (every? pos? numbers))

;; sort-by
(def words ["banana" "apple" "cherry" "date"])
(println "(sort-by count words) =" (sort-by count words))

;; group-by
(println "(group-by even? [1..6]) ="
         (group-by even? (range 1 7)))

;; ── Function composition ────────────────────────────

(println "\n--- Composition ---")

;; comp: right-to-left composition
(def shout (comp str/upper-case str/trim))
(println "(shout \"  hello  \") =" (shout "  hello  "))

(def count-evens (comp count (partial filter even?)))
(println "(count-evens [1..20]) =" (count-evens (range 1 21)))

;; Threading macros: left-to-right pipelines
(println
  "Thread-last: "
  (->> (range 1 11)
       (filter even?)
       (map #(* % %))
       (reduce +)))
;; 1. numbers 1-10
;; 2. keep evens: 2,4,6,8,10
;; 3. square: 4,16,36,64,100
;; 4. sum: 220

;; Thread-first: for object-like operations
(println
  "Thread-first: "
  (-> "  Hello, World!  "
      str/trim
      str/lower-case
      (str/replace "world" "atlas")))

;; ── Partial application ──────────────────────────────

(println "\n--- Partial application ---")

(def add5 (partial + 5))
(println "(add5 10) =" (add5 10))

(def info-log (partial println "[INFO]"))
(info-log "Server started")

;; ── Closures ─────────────────────────────────────────

(println "\n--- Closures ---")

(defn make-multiplier [factor]
  (fn [x] (* x factor)))

(def double' (make-multiplier 2))
(def triple' (make-multiplier 3))
(println "(double' 5) =" (double' 5))
(println "(triple' 5) =" (triple' 5))

;; Counter with atom (managed mutable state)
(defn make-counter []
  (let [count (atom 0)]
    (fn [] (swap! count inc))))

(def counter (make-counter))
(println "(counter) =" (counter))
(println "(counter) =" (counter))
(println "(counter) =" (counter))

;; ── Destructuring in function args ───────────────────

(println "\n--- Destructuring ---")

;; Vector destructuring
(defn distance [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (Math/pow (- x2 x1) 2)
                (Math/pow (- y2 y1) 2))))

(println "(distance [0 0] [3 4]) =" (distance [0 0] [3 4]))

;; Map destructuring
(defn greet-person [{:keys [name age]}]
  (str name " is " age " years old"))

(println (greet-person {:name "Ada" :age 36}))

;; ── Recursion ────────────────────────────────────────

(println "\n--- Recursion ---")

;; Direct recursion
(defn factorial [n]
  (if (<= n 1) 1
      (* n (factorial (dec n)))))

(println "(factorial 10) =" (factorial 10))

;; Tail recursion with recur (required for TCO in Clojure)
(defn factorial-tail [n]
  (loop [i n, acc 1]
    (if (<= i 1) acc
        (recur (dec i) (* acc i)))))

(println "(factorial-tail 20) =" (factorial-tail 20))

;; ── Lazy sequences ──────────────────────────────────

(println "\n--- Lazy sequences ---")

;; iterate: generate lazy sequence by repeated application
(println "(take 10 (iterate #(* 2 %) 1)) ="
         (vec (take 10 (iterate #(* 2 %) 1))))

;; Fibonacci as lazy sequence
(def fibs
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))
(println "(take 15 fibs) =" (vec (take 15 fibs)))

;; ── Memoization ──────────────────────────────────────

(println "\n--- Memoization ---")

(def fib-memo
  (memoize
    (fn [n]
      (if (< n 2) n
          (+ (fib-memo (dec n)) (fib-memo (- n 2)))))))

(println "(fib-memo 30) =" (fib-memo 30))

;; ── apply ────────────────────────────────────────────

(println "\n--- apply ---")

;; apply "spreads" a collection as arguments
(println "(apply + [1 2 3 4 5]) =" (apply + [1 2 3 4 5]))
(println "(apply max [3 1 4 1 5 9]) =" (apply max [3 1 4 1 5 9]))
(println "(apply str [\"a\" \"b\" \"c\"]) =" (apply str ["a" "b" "c"]))

;; ── juxt (apply multiple functions at once) ──────────

(println "\n--- juxt ---")

(def stats (juxt count #(reduce + %) #(apply min %) #(apply max %)))
(println "(stats [3 1 4 1 5 9]) =" (stats [3 1 4 1 5 9]))
;; [count, sum, min, max]

;; ── Summary ──────────────────────────────────────────

(println "\n--- Summary ---")
(println "Clojure functions:")
(println "  - First-class: values, passable, composable")
(println "  - defn for named, fn / #() for anonymous")
(println "  - Multi-arity and variadic")
(println "  - Closures capture enclosing scope")
(println "  - map, filter, reduce, remove, some, every?")
(println "  - comp for composition, partial for currying")
(println "  - -> and ->> threading macros for pipelines")
(println "  - Destructuring in function parameters")
(println "  - recur for tail-call optimisation")
(println "  - Lazy sequences with iterate, lazy-seq")
(println "  - memoize for automatic caching")
