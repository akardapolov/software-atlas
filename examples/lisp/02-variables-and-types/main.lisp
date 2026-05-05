;;; Variables and Types in Lisp
;;; =============================
;;; Lisp uses dynamic typing with symbols as variable names.
;;; Values are not typed at definition time — the symbol
;;; can refer to any value. Lisp is homoiconic: code
;;; and data have same representation (lists).

;; ── Basic types ─────────────────────────────────────

;; Integers — arbitrary precision (bignum)
(setq count 42)
(setq big-number (expt 10 100))  ; googol — no overflow!
(format t "count = ~A, type = ~A~%" count (type-of count))
(format t "big_number has ~D digits~%" (length (write-to-string big-number)))

;; Floats — IEEE 754 double precision
(setq pi 3.14159)
(format t "pi = ~A, type = ~A~%" pi (type-of pi))

;; Ratios — exact rational numbers (no floating-point error)
(setq one-half 1/2)
(setq two-thirds 2/3)
(format t "1/2 + 2/3 = ~A~%" (+ one-half two-thirds))  ; 7/6

;; Strings — sequences of characters
(setq name "Software Engineering Atlas")
(format t "name = ~A~%" name)
(format t "name[0] = ~A, name[-1] = ~A~%" (aref name 0) (aref name -1))

;; Booleans — t (true) and nil (false)
(setq active t)
(format t "active = ~A, type = ~A~%" active (type-of active))
(format t "t + t = ~A~%" (+ t t))  ; 2 — t is not 1!
(format t "nil = ~A, type = ~A~%" nil (type-of nil))


;; ── Dynamic typing ─────────────────────────────────

;; The same symbol can refer to different types over time
(setq x 42)
(format t "~%nx = ~A, type = ~A~%" x (type-of x))

(setq x "hello")
(format t "x = ~A, type = ~A~%" x (type-of x))

(setq x '(1 2 3))
(format t "x = ~A, type = ~A~%" x (type-of x))


;; ── Homoiconicity ────────────────────────────────────

(format t "~%n--- Homoiconicity ---")
;; Code and data have same representation (lists)
;; '(1 2 3) is a list of numbers
;; '(setq x 10) is also a list (code!)
(setq code '(setq x 10))
(setq data '(1 2 3))

(format t "code = ~A, type of car = ~A~%" code (type-of (car code)))
(format t "data = ~A, type of car = ~A~%" data (type-of (car data)))

;; This enables meta-programming (code that generates code)


;; ── Symbols ─────────────────────────────────────────

(format t "~%n--- Symbols (variables) ---")
;; Symbols are evaluated to their bound value
(setq my-var 42)
(format t "my-var = ~A~%" my-var)

;; Quote prevents evaluation: returns the symbol itself
(format t "'my-var = ~A~%" 'my-var)


;; ── Collections ─────────────────────────────────────

(format t "~%n--- Collections ---")

;; List — ordered, linked structure
(setq fruits '(apple banana cherry))
(setq fruits-with-date (append fruits '(date)))
(format t "fruits = ~A~%" fruits)
(format t "car (first) = ~A~%" (car fruits))
(format t "cdr (rest) = ~A~%" (cdr fruits))
(format t "length = ~D~%" (length fruits))

;; Cons — prepend to list (prepend, not append!)
(setq more-fruits (cons 'date fruits))
(format t "cons 'date onto fruits = ~A~%" more-fruits)

;; Alist — association list (key-value pairs)
(setq person '((name . "Ada") (age . 36)))
(format t "person = ~A~%" person)

;; Helper to get value from alist
(defun get-value (alist key)
  (cdr (assoc key alist)))

(format t "name = ~A~%" (get-value person 'name))
(format t "age = ~A~%" (get-value person 'age))

;; Property list — modern association lists
(setq person-plist '(name "Ada" age 36))
(format t "person-plist = ~A~%" person-plist)
(format t "get name = ~A~%" (getf 'name person-plist))


;; ── Quoting ─────────────────────────────────────────

(format t "~%n--- Quoting ---")
;; Quote prevents evaluation of forms

(format t "'(+ 1 2) = ~A~%" '(+ 1 2))  ; Unevaluated form
(format t "(+ 1 2) = ~A~%" (+ 1 2))      ; Evaluated result

;; Backquote ( quasi-quote) — template with evaluation
(setq x 10)
(format t "`(list x x) = ~A~%" `(list ,x ,x))  ; (list 10 10)


;; ── Dynamic evaluation ────────────────────────────────

(format t "~%n--- Dynamic evaluation ---")

;; eval — evaluate a form as data
(setq form '(+ 1 2 3))
(format t "form = ~A~%" form)
(format t "eval form = ~A~%" (eval form))

;; funcall — call a function whose name is determined at runtime
(setq op '+)
(format t "funcall op '(1 2 3) = ~A~%" (funcall op '(1 2 3)))


;; ── Comparison with other languages ─────────────────

(format t "~%n--- Summary ---")
(format t "Lisp:")
(format t "  - Dynamic typing (no type declarations)")
(format t "  - Homoiconic (code = data)")
(format t "  - Symbols as variable names")
(format t "  - Lists as primary data structure")
(format t "  - Quoting for code-as-data")
(format t "  - eval for dynamic evaluation")
