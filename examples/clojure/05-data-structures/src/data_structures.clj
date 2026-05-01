; Data Structures in Clojure
; ==========================
; Clojure provides persistent data structures (lists, vectors, maps, sets).
; Data is immutable by default, making them safe for concurrent use.

(ns data-structures)

;; ── Vectors (indexed collections) ───────────────────────

(defn demonstrate-vectors []
  (println "--- Vectors (indexed, persistent) ---")

  ;; Creation with vector literal
  (def fruits ["apple" "banana" "cherry"])
  (println "fruits =" fruits)

  ;; Indexing (O(log n))
  (println "fruits[0] =" (get fruits 0))   ; apple
  (println "fruits[2] =" (get fruits 2))   ; cherry

  ;; nth is safer than get for out-of-range
  (println "nth 5 =" (nth fruits 5 "default"))  ; Returns "default"

  ;; conj adds to end of vector
  (def fruits-2 (conj fruits "date"))
  (println "After conj 'date':" fruits-2)

  ;; assoc updates index
  (def fruits-3 (assoc fruits 1 "coconut"))
  (println "After assoc index 1:" fruits-3)

  ;; subvec (slice)
  (println "fruits[1:3] =" (subvec fruits 1 3))

  ;; Range to create vector
  (def squares (vec (map #(* % %) (range 1 11)))
  (println "squares (1-10):" squares)

  ;; count (length)
  (println "count =" (count fruits)))

;; ── Lists (linked lists) ─────────────────────────────

(defn demonstrate-lists []
  (println "\n--- Lists (linked, persistent) ---")

  ;; Creation with list literal
  (def items '(1 2 3 4 5))
  (println "items =" items)

  ;; cons adds to beginning (prepend)
  (def items-2 (conj items 0))
  (println "After conj 0:" items-2)

  ;; rest and first (head and tail)
  (println "first =" (first items))   ; 1
  (println "rest =" (rest items))     ; (2 3 4 5)

  ;; lazy sequences
  (def naturals (iterate inc 1))
  (println "First 5 naturals:" (take 5 naturals))

  ;; Persistent! collections return new instances
  (def items-3 items)
  (println "Original list:" items)
  (println "Conj returns new list:" items-2)
  (println "Original unchanged:" items))

;; ── Maps (hash maps) ─────────────────────────────

(defn demonstrate-maps []
  (println "\n--- Maps (key-value) ---")

  ;; Creation with hash-map literal
  (def person {:name "Ada" :age 36 :city "London"})
  (println "person =" person)

  ;; Access with keywords (preferred)
  (println "name =" (:name person))
  (println "age =" (:age person))

  ;; Access with keys (also works)
  (println "name via key =" (get person :name))
  (println "age via key =" (get person :age))

  ;; assoc adds/updates key
  (def person-2 (assoc person :email "ada@example.com"))
  (println "After assoc email:" person-2)

  ;; dissoc removes key
  (def person-3 (dissoc person :city))
  (println "After dissoc city:" person-3)

  ;; contains?
  (println "contains :email?" (contains? person :email))
  (println "contains :phone?" (contains? person :phone))

  ;; keys and vals
  (println "keys:" (keys person))
  (println "vals:" (vals person))

  ;; sorted map (tree-based, maintains order)
  (def ordered-scores (sorted-map-by :score [{:name "Bob" :score 95} {:name "Alice" :score 87}]))
  (println "Sorted map (by score):" ordered-scores)

  ;; update (merge)
  (def person-4 (merge person {:country "UK"}))
  (println "After merge:" person-4))

;; ── Sets (unique collections) ─────────────────────

(defn demonstrate-sets []
  (println "\n--- Sets (unique elements) ---")

  ;; Creation with hash-set literal (duplicates removed)
  (def tags #{"python" "typing" "python" "data"})
  (println "tags =" tags)
  (println "count =" (count tags))  ; 3

  ;; conj adds element
  (def tags-2 (conj tags "algorithms"))
  (println "After conj 'algorithms':" tags-2)

  ;; disj removes element
  (def tags-3 (disj tags "data"))
  (println "After disj 'data':" tags-3)

  ;; contains?
  (println "contains 'typing'?" (contains? tags "typing"))

  ;; Set operations
  (def set-a #{1 2 3 4 5})
  (def set-b #{4 5 6 7 8})

  (println "union:" (clojure.set/union set-a set-b))
  (println "intersection:" (clojure.set/intersection set-a set-b))
  (println "difference a-b:" (clojure.set/difference set-a set-b))

  ;; sorted set (tree-based, maintains order)
  (def numbers (sorted-set #{5 2 8 1 9 3 7 6 4}))
  (println "Sorted set:" numbers)  ; Already sorted!

  ;; Subset/superset
  (println "subset? (2 3 4) of (1 2 3 4 5 6)?" (clojure.set/subset? #{2 3 4} #{1 2 3 4 5 6}))
  (println "superset? (1 2 3 4 5 6) of (2 3 4)?" (clojure.set/superset? #{1 2 3 4 5 6} #{2 3 4}))

;; ── Persistent! Immutability ─────────────────────────

(defn demonstrate-persistence []
  (println "\n--- Persistent! Immutability ---")

  (def original [1 2 3])
  (def modified (conj original 4))

  (println "Original:" original)
  (println "Modified (new vector):" modified)
  (println "Original unchanged:" original))

  ;; Transient for batch operations
  (println "Transient for batch operations:")
  (def transient-original [1 2 3])
  (def transient-result
    (persistent!  (reduce conj! transient-original [4 5])))
  (println "Result:" transient-result))

;; ── StructMaps (typed records) ─────────────────────

(defn demonstrate-structmaps []
  (println "\n--- StructMaps (typed records) ---")

  ;; Define struct type
  (defrecord Point [x y])

  ;; Creation
  (def p (->Point 10 20))
  (println "Point:" p)

  ;; Access fields
  (println "x =" (:x p))
  (println "y =" (:y p))

  ;; Assoc creates new instance
  (def p-2 (assoc p :x 30))
  (println "After assoc x:" p-2)
  (println "Original unchanged:" p)

  ;; Update multiple fields
  (def p-3 (assoc p :x 30 :y 40))
  (println "After update x and y:" p-3)

;; ── Zippers (structural navigation) ─────────────────

(defn demonstrate-zippers []
  (println "\n--- Zippers (structural navigation) ---")

  (def data [1 2 3 4 5])

  ;; Create zipper (focus point)
  (def zipped (vector-zipper data))

  (println "Current:" (zipped zipped))
  (println "Right:" (next zipped))
  (println "Left:" (prev zipped))

  ;; Move through structure
  (def zipped-2 (-> zipped next))
  (println "After next:" (zipped-2 zipped))

  ;; Replace and rebuild
  (def rebuilt (zipper/replace zipped-2 0 99))
  (def result (zipper/root rebuilt))
  (println "Rebuilt vector:" result)

;; ── Lazy sequences ─────────────────────────────────

(defn demonstrate-lazy []
  (println "\n--- Lazy sequences ---")

  ;; range creates lazy sequence
  (def lazy-naturals (range))
  (println "First 5 of lazy:" (take 5 lazy-naturals))

  ;; lazy cat
  (def combined (lazy-cat (repeat [1 2 3]) (repeat [4 5])))
  (println "First 10 of combined:" (take 10 combined))

  ;; lazy map/filter
  (def lazy-squares (map #(* % %) (range 1 100)))
  (println "First 5 lazy squares:" (take 5 lazy-squares))

  (println "Lazy sequences are only computed when needed!")

;; ── Summary ───────────────────────────────────────────

(defn -main []
  (println "Data Structures in Clojure")
  (println "==========================")

  demonstrate-vectors)
  demonstrate-lists)
  demonstrate-maps)
  demonstrate-sets)
  demonstrate-persistence)
  demonstrate-structmaps)
  demonstrate-zippers)
  demonstrate-lazy)

  (println "\n--- Summary ---")
  (println "Clojure data structures:")
  (println "  - Vector: indexed, persistent, O(log n) access")
  (println "  - List: linked, persistent, head/tail operations")
  (println "  - Map: hash map (unordered), sorted map (ordered)")
  (println "  - Set: hash set, sorted set")
  (println "  - Persistent!: immutable by default, structural sharing")
  (println "  - Transient: mutable for batch operations")
  (println "  - StructMaps: typed records with field access")
  (println "  - Zippers: structural navigation and modification")
  (println "  - Lazy sequences: range, lazy-cat, computed on demand")
  (println "  - Concurrency safe: immutable data prevents data races"))
