;;; Variables and Types in Clojure
;;; ==============================
;;; Clojure uses dynamic, strong typing.
;;; All core data structures are immutable and persistent.
;;; Clojure is hosted on the JVM and interoperates with Java types.

(ns main)

;; ── Basic types ──────────────────────────────────────

(println "--- Basic types ---")

;; Integers — uses Java Long (64-bit), auto-promotes to BigInteger
(def count' 42)
(def big-num 9000000000000000000N)  ; BigInteger literal with N
(println "count =" count' "type =" (type count'))
(println "big-num =" big-num "type =" (type big-num))

;; Floats — Java Double
(def pi' 3.14159)
(println "pi =" pi' "type =" (type pi'))

;; Rationals — exact arithmetic!
(def third (/ 1 3))
(println "1/3 =" third "type =" (type third))
(println "1/3 + 1/3 + 1/3 =" (+ third third third))  ; exactly 1

;; Booleans
(def active true)
(println "active =" active "type =" (type active))

;; Strings — Java String
(def name' "Software Atlas")
(println "name =" name' "type =" (type name'))

;; Characters
(def letter \A)
(println "letter =" letter "type =" (type letter))

;; nil — absence of a value
(def nothing nil)
(println "nothing =" nothing "nil? =" (nil? nothing))

;; Keywords — lightweight identifiers (like atoms in Erlang)
(def status :active)
(println "status =" status "type =" (type status))

;; ── Immutability ─────────────────────────────────────

(println "\n--- Immutability ---")

;; def creates a global binding — not a mutable variable
(def x 42)
(println "x =" x)
;; (def x 43) would rebind the var, not mutate — and is discouraged

;; let creates local, immutable bindings
(let [a 10
      b (+ a 5)]
  (println "a =" a "b =" b))
;; a and b don't exist outside this let block

;; ── Strong typing ────────────────────────────────────

(println "\n--- Strong typing ---")

;; Clojure does NOT implicitly convert between types
;; (+ 1 "2") → ClassCastException!
(try
  (+ 1 "2")
  (catch Exception e
    (println "(+ 1 \"2\") →" (.getSimpleName (class e)))))

;; Explicit conversions
(println "(Integer/parseInt \"42\") =" (Integer/parseInt "42"))
(println "(str 42) =" (str 42))
(println "(double 42) =" (double 42))

;; ── Vectors ──────────────────────────────────────────

(println "\n--- Vectors ---")

;; Vectors — indexed, immutable sequences
(def nums [1 2 3 4 5])
(println "nums =" nums)
(println "count =" (count nums))
(println "(first nums) =" (first nums))
(println "(last nums) =" (last nums))
(println "(nth nums 2) =" (nth nums 2))

;; "Adding" returns a NEW vector (structural sharing)
(def extended (conj nums 6))
(println "(conj nums 6) =" extended)
(println "nums still =" nums "← unchanged!")

;; ── Lists ────────────────────────────────────────────

(println "\n--- Lists ---")

;; Lists — linked lists, efficient prepend
(def fruits '("apple" "banana" "cherry"))
(println "fruits =" fruits)
(println "(first fruits) =" (first fruits))
(println "(rest fruits) =" (rest fruits))

;; conj on lists prepends (not appends like vectors)
(println "(conj fruits \"date\") =" (conj fruits "date"))

;; ── Maps ─────────────────────────────────────────────

(println "\n--- Maps ---")

;; Maps — key-value pairs, keywords as keys
(def person {:name "Ada" :age 36 :active true})
(println "person =" person)

;; Access — keywords are functions!
(println "(:name person) =" (:name person))
(println "(:age person) =" (:age person))

;; Missing key returns nil (or a default)
(println "(:email person) =" (:email person))          ; nil
(println "(:email person \"?\") =" (:email person "?")) ; default

;; "Updating" returns a NEW map
(def updated (assoc person :age 37))
(println "(assoc person :age 37) =" updated)
(println "person still =" person "← unchanged!")

;; Nested update
(def nested {:user {:name "Ada" :prefs {:theme "dark"}}})
(def changed (assoc-in nested [:user :prefs :theme] "light"))
(println "assoc-in =" changed)

;; ── Sets ─────────────────────────────────────────────

(println "\n--- Sets ---")

(def tags #{:clojure :fp :jvm})
(println "tags =" tags)
(println "(contains? tags :fp) =" (contains? tags :fp))
(println "(conj tags :lisp) =" (conj tags :lisp))

;; ── Destructuring ────────────────────────────────────

(println "\n--- Destructuring ---")

;; Vector destructuring
(let [[a b c] [10 20 30]]
  (println "a =" a "b =" b "c =" c))

;; Map destructuring
(let [{:keys [name age]} {:name "Ada" :age 36}]
  (println "name =" name "age =" age))

;; With defaults
(let [{:keys [name email] :or {email "n/a"}} {:name "Ada"}]
  (println "name =" name "email =" email))

;; ── Sequences (the universal abstraction) ────────────

(println "\n--- Sequences ---")

;; Almost everything is a sequence in Clojure
;; map, filter, reduce work on vectors, lists, maps, sets, strings...

(println "(map inc [1 2 3]) =" (map inc [1 2 3]))
(println "(filter even? (range 10)) =" (filter even? (range 10)))
(println "(reduce + [1 2 3 4 5]) =" (reduce + [1 2 3 4 5]))

;; Lazy sequences
(println "(take 5 (range)) =" (take 5 (range)))  ; infinite sequence!

;; ── Java interop ─────────────────────────────────────

(println "\n--- Java interop ---")

;; Clojure values are Java objects
(println "(type 42) =" (type 42))           ; java.lang.Long
(println "(type \"hi\") =" (type "hi"))     ; java.lang.String
(println "(type true) =" (type true))       ; java.lang.Boolean
(println "(type :key) =" (type :key))       ; clojure.lang.Keyword
(println "(type [1 2]) =" (type [1 2]))     ; clojure.lang.PersistentVector

;; Call Java methods
(println "(.toUpperCase \"hello\") =" (.toUpperCase "hello"))
(println "(Math/sqrt 2) =" (Math/sqrt 2))

;; ── Summary ──────────────────────────────────────────

(println "\n--- Summary ---")
(println "Clojure: dynamic + strong typing")
(println "  - Types checked at runtime (dynamic)")
(println "  - No implicit conversions (strong)")
(println "  - All data structures immutable by default")
(println "  - Persistent data structures (structural sharing)")
(println "  - Keywords as lightweight identifiers")
(println "  - Maps as primary data structure")
(println "  - Sequences as universal abstraction")
(println "  - Hosted on JVM — Java interop")
(println "  - Exact arithmetic (rationals, BigInteger)")
