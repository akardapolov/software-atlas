;; FP Features in Clojure
;; ======================
;; Pure functions, immutable data, higher-order functions

(ns main)

;; ── Pure functions ─────────────────────────────

(println "--- Pure functions ---")

(def add (fn [a b] (+ a b)))
(def multiply (fn [a b] (* a b)))

;; Function as value
(println "add(5, 3) =" (add 5 3))

;; Higher-order function
(defn apply-twice [f x] (f (f x)))
(println "apply-twice(x+1, 5) =" (apply-twice inc 5))


;; ── Higher-order functions (map, filter, reduce) ───────

(println "\n--- Higher-order functions ---")

(def numbers [1 2 3 4 5 6 7 8 9 10])

;; map
(def squares (map #(* % %) numbers))
(println "Squares:" squares)

;; filter
(def evens (filter even? numbers))
(println "Evens:" evens)

;; reduce
(def total (reduce + 0 numbers))
(println "Sum:" total)

;; find
(def first-even (first (filter even? numbers)))
(println "First even:" first-even)

;; any/some
(def has-odd (some odd? numbers))
(println "Has odd? " has-odd)

(def all-positive? (every pos? numbers))
(println "All positive? " all-positive?)


;; ── Closures (fn, let, loop) ─────────────────────

(println "\n--- Closures ---")

(def make-multiplier (fn [factor]
                (fn [x] (* x factor))))

(def times-three (make-multiplier 3))
(def times-five (make-multiplier 5))

(println "times-three(7) =" (times-three 7))
(println "times-five(8) =" (times-five 8))


;; ── Partial application (partial) ─────────────────────

(println "\n--- Partial application (partial)")

(def add-one (partial + 1))
(def times-two (partial * 2))

(println "add-one(5) =" (add-one 5))
(println "times-two(5) =" (times-two 5))


;; ── Function composition (comp, juxt) ───────────────

(println "\n--- Function composition (comp, juxt)")

(def shout (comp str/upper str/trim))
(def greet (partial str "Hello, "))

(println "shout(\"  alice  \") =" (shout "  alice  "))
(println "greet(\"Bob\") =" (greet "Bob"))

;; juxt (apply multiple functions)
(def describe-number (juxt name count))
(println "describe-number(42) =" (describe-number 42))


;; ── Destructuring ─────────────────────────────────────

(println "\n--- Destructuring")

(defn process-point [[x y]]
  (+ x y))

(def point [3 4])
(println "Point sum:" (process-point point))

;; Destructuring in function params
(defn destructure-map [{:keys [name age]}]
  (str name " is " age " years))

(def person-map {:name "Alice", :age 30})
(println (destructure-map person-map))


;; ── Lazy sequences ───────────────────────────────

(println "\n--- Lazy sequences")

(def naturals (range))
(def take-ten (take 10 naturals))

(println "First 10 naturals:" (take-ten))


;; ── Transducer ───────────────────────────────────

(println "\n--- Transducer (composition for reduce)")

(def xf-sum (map #(* % 2) (filter even?)))
(defn transduce-xf-sum [coll] (transduce xf-sum + coll))

(println "Transduce with xf-sum:" (transduce-xf-sum [1 2 3 4 5]))


;; ── Memoization ─────────────────────────────────────

(println "\n--- Memoization (with-cache)")

(def memoize
  (let [cache (atom {})]
    (fn [f]
      (fn [& args]
        (if-let [result (get @cache args)]
          result
          (let [new-result (apply f args)]
            (swap! cache assoc args new-result)
            new-result)))))))


(defn slow-fib [n]
  (Thread/sleep 10))  ; Simulate slowness
  (if (< n 2)
    n
    (+ (slow-fib (dec n)) (slow-fib (dec n))))

(def fib (memoize slow-fib))

(println "fib(30) =" (fib 30))


;; ── Thread-last macro (->> pipeline) ─────────────────

(println "\n--- Thread-last macro (pipeline)")

(defn process [n]
  (* 2 n))

(def pipeline (comp process process process))

(println "pipeline(5) =" (pipeline 5))


;; ── Summary ───────────────────────────────────────

(println "\n--- Summary ---")
(println "Clojure FP features:")
(println "  - Pure functions: no side effects")
(println "  - Immutable data: vectors, maps, sets")
(println "  - Higher-order: map, filter, reduce")
(println "  - Closures: let/loop bindings")
(println "  - Partial: partial application")
(println "  - Composition: comp, juxt, partial")
(println "  - Destructuring: bind data structures")
(println "  - Lazy sequences: range, take, drop")
(println "  - Transducers: composition for reduce")
(println "  - Thread-last: ->> pipeline macro")
(println "  - Memoization: with-cache optimization")
(println "  - No loops: use map/filter/reduce instead")
