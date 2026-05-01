;;; Functions in Lisp
;;; ===============
;;; Lisp functions are first-class: assignable to variables, storable
;;; in data structures, and passable to other functions. Closures
;;; capture lexical scope. Lisp predates "functional programming" term
;;; but embodies its core principles.

;; ── Basic function definition ─────────────────────────

(defun greet (name)
  "A simple function that greets someone."
  (format t "Hello, ~A!~%" name))

(format t "~A~%" (greet "Atlas"))
(format t "greet is a ~A~%" (type-of 'greet))


;; ── Default arguments ─────────────────────────────

(defun power (base &optional (exponent 2))
  "Default argument: exponent defaults to 2 (square)."
  (expt base exponent))

(format t "~%npower(3) = ~A~%" (power 3))           ; 9
(format t "power(2 10) = ~A~%" (power 2 10))    ; 1024
(format t "power(5) = ~A~%" (power 5))               ; 25 (uses default exponent)


;; ── Variable arguments (rest) ─────────────────────────

(defun sum-all (&rest numbers)
  "Accept any number of arguments and sum them."
  (apply '+ numbers))

(format t "~%nsum-all(1 2 3 4 5) = ~A~%" (sum-all 1 2 3 4 5))
(format t "sum-all() = ~A~%" (sum-all))  ; 0 (no arguments)


;; ── Functions are first-class objects ─────────────────

(format t "~%n--- First-class functions ---")

(defun add (a b)
  (+ a b))

(defun multiply (a b)
  (* a b))

;; Functions can be assigned to variables
(setq operation 'add)
(format t "operation(3 4) = ~A~%" (funcall operation 3 4))  ; 7

;; Functions can be stored in data structures
(setq ops (cons (cons 'add (list 'mul))))
(format t "Apply first op to (3 4): ~A~%" (funcall (car ops) 3 4))


;; ── Higher-order functions ────────────────────────────

(format t "~%n--- Higher-order functions ---")

;; map — apply a function to each element
(setq numbers '(1 2 3 4 5 6 7 8 9 10))
(format t "~%nnumbers = ~A~%" numbers)

(setq squares (mapcar (lambda (x) (* x x)) numbers))
(format t "squares = ~A~%" squares)

;; remove-if — filter (keep elements satisfying predicate)
(setq evens (remove-if-not (lambda (x) (zerop (% x 2))) numbers))
(format t "evens = ~A~%" evens)

;; reduce — combine elements into one value (fold)
(defun my-fold (func initial list)
  "Fold (reduce) a list with an initial value."
  (if (null list)
      initial
      (my-fold func (func initial (car list)) (cdr list))))

(setq total (my-fold (lambda (acc x) (+ acc x)) 0 numbers))
(format t "sum = ~A~%" total)

(setq product (my-fold (lambda (acc x) (* acc x)) 1 numbers))
(format t "product = ~A~%" product)

;; sort with key function
(setq words '(banana apple cherry date))
(setq by-length (sort words '< :key 'length))
(format t "sorted by length = ~A~%" by-length)


;; ── Writing your own higher-order function ───────────

(format t "~%n--- Custom higher-order function ---")

(defun apply-twice (f x)
  "Apply function f to x, then apply f to result."
  (funcall f (funcall f x)))

(format t "apply-twice(add-one 5) = ~A~%" (apply-twice (lambda (x) (+ x 1)) 5))
(format t "apply-twice(double 3) = ~A~%" (apply-twice (lambda (x) (* x 2)) 3))


;; ── Lambda (anonymous functions) ──────────────────

(format t "~%n--- Lambda ---")

;; Lambda: anonymous function, no name
(setq square (lambda (x) (* x x)))
(format t "square(5) = ~A~%" (funcall square 5))

;; Useful inline with higher-order functions
(setq pairs '((3 "three") (1 "one") (2 "two")))
(setq sorted-pairs (sort pairs '< :key 'car))
(format t "sorted pairs = ~A~%" sorted-pairs)


;; ── Closures ─────────────────────────────────────────

(format t "~%n--- Closures ---")

(defun make-multiplier (factor)
  "Return a function that multiplies its argument by factor."
  ;; In Lisp lexical scope, closures capture by default
  (lambda (x) (* x factor)))

(setq double (make-multiplier 2))
(setq triple (make-multiplier 3))

(format t "double(5) = ~A~%" (funcall double 5))    ; 10
(format t "triple(5) = ~A~%" (funcall triple 5))    ; 15

;; Counter with closure (using setq in closure)
(defun make-counter (&optional (start 0))
  "Closure with mutable state."
  (let ((count start))
    (lambda ()
      (setq count (+ count 1))
      count)))

(setq counter (make-counter))
(format t "counter() = ~A~%" (funcall counter))  ; 1
(format t "counter() = ~A~%" (funcall counter))  ; 2
(format t "counter() = ~A~%" (funcall counter))  ; 3


;; ── Function composition ─────────────────────────────

(format t "~%n--- Function composition ---")

(defun compose (&rest fns)
  "Compose functions right-to-left: compose(f g)(x) = f(g(x))"
  (lambda (x)
    (let ((result x))
      (dolist (f (reverse fns))
        (setq result (funcall f result)))
      result)))

;; Compose str functions
(defun str-upper (s) (upcase s))
(defun str-strip (s) (string-trim s))
(defun add-bang (s) (concatenate s "!"))

(setq shout (compose 'str-upper 'str-strip 'add-bang))
(format t 'shout("  hello  ") = "~A~%"' (funcall shout "  hello  "))


;; ── Currying ──────────────────────────────────────

(format t "~%n--- Currying (partial application) ---")

;; Lisp doesn't have built-in currying, but we can implement
(defun curry (fn)
  "Convert a function of multiple args to nested single-arg functions."
  (lambda (&rest args)
    (if (null args)
        fn
        (funcall (curry fn) (apply fn (list (car args)))
              (apply fn (cdr args)))))

;; Not typically used in Common Lisp — just for demonstration


;; ── Recursion ───────────────────────────────────────

(format t "~%n--- Recursion (natural in Lisp) ---")

(defun factorial (n)
  "Recursive factorial."
  (if (< n 2)
      1
      (* n (factorial (- n 1)))))

(format t "factorial(5) = ~A~%" (factorial 5))  ; 120

;; Tail-recursive (optimizable)
(defun factorial-tr (n &optional (acc 1))
  "Tail-recursive factorial with accumulator."
  (if (< n 2)
      acc
      (factorial-tr (- n 1) (* n acc))))

(format t "factorial-tr(5) = ~A~%" (factorial-tr 5))


;; ── Map-into (side-effecting map) ───────────────

(format t "~%n--- Map-into (for side effects) ---")

;; mapc applies function to each element but returns nil (for side effects)
(defun print-all (list)
  (mapc (lambda (x) (format t "~A " x)) list))

(format t "~%nprint-all '(1 2 3):")
(print-all '(1 2 3))


;; ── Destructuring with let ─────────────────────────

(format t "~%n--- Destructuring with let ---")

(defun my-first (list)
  "Get first element using let."
  (let ((first (car list))
        rest (cdr list))
    first))

(format t "my-first '(a b c) = ~A~%" (my-first '(a b c)))


;; ── Labels (local functions) ───────────────────────

(format t "~%n--- Labels (local named functions) ---")

(labels ((square (x) (* x x))
         (cube (x) (* x x x)))
  (format t "square(3) = ~A, cube(3) = ~A~%" (square 3) (cube 3)))


;; ── Summary ───────────────────────────────────────────

(format t "~%n--- Summary ---")
(format t "Lisp functions:")
(format t "  - First-class: assignable, passable, storable")
(format t "  - Higher-order: mapcar, remove-if, fold")
(format t "  - Lambda: anonymous functions")
(format t "  - Closures: capture lexical scope")
(format t "  - Recursion: natural, tail recursion supported")
(format t "  - Function composition: compose functions")
(format t "  - Dynamic function calls: funcall, apply")
