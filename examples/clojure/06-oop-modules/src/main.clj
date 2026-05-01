; OOP and Modules in Clojure
; ==========================
; Clojure uses protocols (interfaces), records (data types),
; and multimethods. Namespaces with ns keyword.
; OOP through protocols and composition, not inheritance.

(ns oop-and-modules)

;; ── Protocols (Interfaces) ───────────────────────

(defn demonstrate-protocols []
  (println "--- Protocols (Interfaces) ---")

  ;; Protocol definition (like interface)
  (defprotocol Drawable
    (draw [this] "Draw the entity")
    (resize [this] [factor] "Resize by factor")
    (get-size [this] "Get the size"))

  ;; Record implementing protocol
  (defrecord Circle [radius])
    ( Drawable
      (draw [this]
        (println (str "Drawing circle with radius " radius)))

      (resize [this] [factor]
        (let [new-radius (* radius factor)]
          (reset! Circle new-radius)))

      (get-size [this]
        (let [[_ radius]])
          (* radius Math/PI)))

  (defrecord Rectangle [width height]
    ( Drawable
      (draw [this]
        (println (str "Drawing rectangle " width "x" height)))

      (resize [this] [factor]
        (let [[new-width (* width factor)] [new-height (* height factor)]]
          (reset! Rectangle new-width new-height)))

      (get-size [this]
        (let [[width height]]
          (* width height))))


  ;; Polymorphic function using protocol
  (defn draw-all [shapes]
    (doseq [shape shapes]
      (draw shape)))

  (defn demonstrate []
    (let [circle (Circle. 5)]
    (defn rect (Rectangle. 3 4))
    (draw-all [circle rect]))


;; ── Records (Data Types) ─────────────────────

(defn demonstrate-records []
  (println "\n--- Records (Data Types) ---")

  ;; Record definition
  (defrecord Point [x y])
  (defn distance-to [this other : Point]
    (let [[dx (- (:x other) (:y other))]
          [dy (- (:y other) (:y other))]]
      (Math/sqrt (+ (* dx dx) (* dy dy))))

  ;; Positional destructuring
  (let [[x _]] (Point 10 20))
  (println "x =" x)

  ;; Update record (assoc creates new instance)
  (defn move-point [p dx dy]
    (->Point p
      (assoc p :x (+ (:x p) dx)
      (assoc p :y (+ (:y p) dy))))


  ;; Multiple fields
  (defrecord Person [name age city email])
  (defn create-person []
    (->Person name "Ada" age 36 city "London" email "ada@example.com"))

  (def ada (create-person))
  (println "Person:" ada)
  (println "  name:" (:name ada))
  (println "  age:" (:age ada))
  (println "  city:" (:city ada))
  (println "  email:" (:email ada)))


;; ── Types (Def and protocols) ─────────────

(defn demonstrate-types []
  (println "\n--- Types (Def and protocols) ---")

  ;; deftype (forward declaration)
  (deftype Point [x y])
  (defn make-point [x y]
    (Point. x y))

  (defn distance-def [p1 p2]
    (let [[dx (- (:x p1) (:x p2))]
          [dy (- (:y p1) (:y p2))]]
      (Math/sqrt (+ (* dx dx) (* dy dy))))

  ;; Records using deftype
  (defrecord PointDef [x y])
  (PointDef. 10 20)

  (println "PointDef:" (PointDef. 10 20))

  ;; Protocol with type constraints
  (defprotocol Numberable
    (get-number [this] "Get the number (returns long, int, etc)"))
    (to-string [this] "Convert to string"))

  (defrecord Score [value]
    (Numberable
      (get-number [_] 95)))

  (defrecord NameScore [value name]
    (Numberable
      (get-number [_] 42]))

  (defn demonstrate []
    (let [number (Score. 95)]
    (println "get-number:" (get-number number))
    (println "to-string:" (to-string number)))


;; ── Multimethods (Method Overloading) ─────────────

(defn demonstrate-multimethods []
  (println "\n--- Multimethods ---")

  ;; Protocol for area calculation
  (defprotocol AreaCalculable
    (area [this]))

  ;; Rectangle implementation
  (defrecord RectangleArea [width height]
    (AreaCalculable
      (area [_] (* width height)))

  ;; Circle implementation
  (defrecord CircleArea [radius]
    (AreaCalculable
      (area [_] (* Math/PI radius radius)))

  ;; Polymorphic function
  (defn total-area [shapes]
    (reduce + (map area shapes) 0))

  (let [rect (RectangleArea. 3 4)]
    (let [circle (CircleArea. 2)]
    (total-area [rect circle]))

  (println "Rect area:" (area rect))
  (println "Circle area:" (area circle))
  (println "Total area:" (total-area [rect circle])))


;; ── Namespaces ─────────────────────────────

(defn demonstrate-namespaces []
  (println "\n--- Namespaces ---")

  ;; Current namespace
  (println "Current namespace:" *ns*))

  ;; Require (import namespace)
  ;; In real usage, require from clojure.string:
  (require '[clojure.string :as s]')
  (println "Split function:" (s/split "a,b,c"))


;; ── Composition ─────────────────────────────

(defn demonstrate-composition []
  (println "\n--- Composition (with) ---")

  ;; Containment (has-a relationship)
  (defrecord Engine [horsepower make year])
  (defrecord Car [make model year engine]
    (defn can-drive? [car]
    (:year car) 2000)

  (let [engine (Engine. 150 1980)]
    (let [car (Car. "Model T" 2010 engine)]

    (println "Can drive 1970 car?" (can-drive? car))
    (println "Car engine:" (:engine car))

  (defn drive-car [car]
    (when (can-drive? car)
      (println "Driving:" (:make car))))


  ;; Association (has-a relationship)
  (defrecord Address [street city country]
  (defrecord Person [name address])

  (defn create-person-with-address []
    (->Person name "Ada" (Address. "123 Main St" "London" "UK"))

  (let [ada (create-person-with-address)]
    (println "Person:" ada)
    (println "  Address:" (:address ada)))


;; ── Dynamic Polymorphism ─────────────────────

(defn demonstrate-dynamic []
  (println "\n--- Dynamic Polymorphism ---")

  ;; Multimethod area example
  (defn shape-area [shape]
    (case shape
      (RectangleArea [_ w h] (* w h))
      (RectangleArea [_ w h] (* w h))

      (CircleArea [_ r])
      (* Math/PI r r)))

  (defn total-shape-area [shapes]
    (map shape-area shapes)
    (reduce + 0))

  (let [rect (RectangleArea. 3 4)]
    (let [circle (CircleArea. 2)]
    (total-shape-area [rect circle])

    (println "Rectangle area:" (shape-area rect))
    (println "Circle area:" (shape-area circle))
    (println "Total area:" (total-shape-area [rect circle])))


;; ── Object-Oriented ─────────────────────

(defn demonstrate-object-oriented []
  (println "\n--- Object-Oriented ---")

  ;; defrecord (like class)
  (defrecord BankAccount [owner balance])

  (defn account-operations [account]
    (let [[new-balance (+ (:balance account) 100)]]
      (reset! BankAccount (:owner account) new-balance))

    (println "New balance:" (:balance account)))

  (defn withdraw [account amount]
    (if (and (pos? amount) (>= amount (:balance account)))
      (reset! BankAccount (:owner account) (- (:balance account) amount)))
      :balance account
      false))

  (let [account (BankAccount. "Alice" 1000.0)]
    (println "Initial:" account)

  (withdraw account 200)
  (println "After -200:" account)

  (withdraw account 100)
  (println "After -100:" account)

  (withdraw account 100)
  (println "After -100 (should fail):" account))


;; ── Specs (Preconditions/Postconditions) ─────────

(defn demonstrate-specs []
  (println "\n--- Spec Pre/Postconditions ---")

  (defn positive-number? [n]
    (pos? n))

  (defn even-number? [n]
    (and (int? n) (zero? (mod n 2)))

  (defn s-conjoins-spec
    "Two strings can be s-conjoined"
    (^:string s-conjoins)
    (s-conjoins [a b] [a b])))


;; ── Summary ───────────────────────────────────

(defn -main []
  (println "OOP and Modules in Clojure")
  (println "========================")

  (demonstrate-protocols)
  (demonstrate-records)
  (demonstrate-types)
  (demonstrate-multimethods)
  (demonstrate-namespaces)
  (demonstrate-composition)
  (demonstrate-dynamic)
  (demonstrate-object-oriented)
  (demonstrate-specs)

  (println "\n--- Summary ---")
  (println "Clojure OOP:")
  (println "  - Protocols (defprotocol): interfaces with type constraints")
  (println "  - Records (defrecord): data types with field access")
  (println "  - deftype: forward declarations")
  (println "  - Multimethods: method overloading by dispatch")
  (println "  - Namespaces: ns keyword, require/import")
  (println "  - Composition: contain, association (with)")
  (println "  - No inheritance: use protocols and composition")
  (println "  - Dynamic polymorphism: multimethods, functions first")
  (println "  - Specs: pre and postconditions (s/conjoins)")
  (println "  - Persistent! data structures are immutable by default"))
