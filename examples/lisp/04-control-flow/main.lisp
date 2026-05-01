;;; Control Flow in Lisp
;;; =======================
;;; Demonstrates cond, case, loop/dolist, dotimes, catch/throw,
;;; and pattern matching (if available). Lisp uses conditionals and
;;; recursion for control flow more than loops.

;; ── Conditionals ───────────────────────────────────────

(defun classify (n)
  "Classify a number."
  (cond
   ((< n 0) "negative")
   ((= n 0) "zero")
   (t "positive")))

(format t "--- cond (if/elif/else) ---")
(dolist (n '(-3 0 7))
  (format t "~A → ~A~%" n (classify n)))


;; ── Multiple conditions ─────────────────────────────────

(format t "~%n--- Multiple conditions ---")

(defun fizzbuzz (n)
  "FizzBuzz: demonstrate multiple conditions."
  (cond
   ((and (zerop (% n 3)) (zerop (% n 5)) "FizzBuzz")
   ((zerop (% n 3)) "Fizz")
   ((zerop (% n 5)) "Buzz")
   (t (format t "~A" n))))

(dotimes (i 1 20)
  (format t "~A " (fizzbuzz i)))


;; ── Logical operators ─────────────────────────────────

(format t "~%n--- Logical operators ---")

;; and, or, not
(format t "t and t = ~A~%" (and t t))
(format t "t and nil = ~A~%" (and t nil))
(format t "t or nil = ~A~%" (or t nil))
(format t "not t = ~A~%" (not t))


;; ── Equality and comparison ─────────────────────────

(format t "~%n--- Comparison ---")

(defun check-equality (a b)
  (format t "~A = ~A? ~A~%"
          a b (= a b)))

(check-equality 5 5)
(check-equality 5 "5")  ; nil (different types: number vs string)


;; ── Recursion (primary loop mechanism) ─────────

(format t "~%n--- Recursion ---")

(defun sum-to-n (n)
  "Sum numbers from 1 to n using recursion."
  (if (<= n 0)
      0
      (+ n (sum-to-n (- n 1)))))

(format t "sum-to-n(10) = ~A~%" (sum-to-n 10))

;; Tail recursion (accumulator pattern)
(defun sum-to-n-tr (n &optional (acc 0))
  "Tail-recursive sum with accumulator."
  (if (<= n 0)
      acc
      (sum-to-n-tr (- n 1) (+ n acc))))

(format t "sum-to-n-tr(10) = ~A~%" (sum-to-n-tr 10))


;; ── Loop: dolist ─────────────────────────────

(format t "~%n--- dolist (iterate over list) ---")

(let ((total 0))
  (dolist (n '(1 2 3 4 5))
    (setq total (+ total n)))
  (format t "sum 1..5 = ~A~%" total))


;; ── Loop: dotimes ───────────────────────────────

(format t "~%n--- dotimes (fixed iteration) ---")

(let ((x 1))
  (dotimes (i 5)
    (setq x (* x 2)))
  (format t "powers of two: ~A~%" x))


;; ── Loop: do (general iterator) ─────────────

(format t "~%n--- do (general iterator) ---")

(let ((squares '()))
  (do ((i 1 (+ i 1)))
      ((> i 10) squares)
    (push (* i i) squares))
  (format t "squares (reverse of push): ~A~%" (nreverse squares)))


;; ── Pattern matching (destructuring) ───────────────

(format t "~%n--- Destructuring with car/cdr ---")

;; Lisp doesn't have modern pattern matching, but can use car/cdr
(defun my-first (list)
  (car list))

(defun my-rest (list)
  (cdr list))

(setq point '(10 20))
(format t "point = ~A~%" point)
(format t "my-first = ~A~%" (my-first point))
(format t "my-rest = ~A~%" (my-rest point))


;; ── Case statement ───────────────────────────────

(format t "~%n--- case (switch-like) ---")

(defun describe-value (value)
  "Describe a value using case."
  (case value
    (0 "zero")
    ((or (integerp value) (< value 0))
     (format t "negative ~D" value))
    ((integerp value)
     (format t "positive ~D" value))
    (stringp
     (format t "string (~D chars)" (length value)))
    (t "something else")))

(format t "~%ndescribe(0) = ~A~%" (describe-value 0))
(format t "describe(-5) = ~A~%" (describe-value -5))
(format t "describe(7) = ~A~%" (describe-value 7))
(format t "describe('hello') = ~A~%" (describe-value "hello"))


;; ── CATCH and THROW (non-local exit) ─────────────

(format t "~%n--- catch/throw (non-local exit) ---")

(defun find-element (list element)
  "Find element index using catch/throw."
  (let ((index 0))
    (catch 'not-found
      (dolist (item list)
        (when (eql item element)
          (throw 'found index))
        (setq index (+ index 1)))
      (format t "not found"))
    (format t "found at index ~D~%" index)))

(format t "~%nfind-element '(a b c d e) 'd' = ")
(find-element '(a b c d e) 'd))
(format t "find-element '(a b c) 'd' = ")
(find-element '(a b c) 'd))


;; ── Tagbody / go (labeled goto) ─────────────────

(format t "~%n--- tagbody/go (labeled control flow) ---")

;; Tagbody allows named blocks and go to jump to them
(tagbody
  start
  (format t "start~%")
  (when (> (random 10) 5)
    (go end))
  (format t "middle~%")
  (go start)
  end
  (format t "end~%"))


;; ── Unwind-protect (finally clause) ───────────────

(format t "~%n--- unwind-protect (finally clause) ---")

;; Unwind-protect ensures cleanup runs even on error
(defun safe-operation (value)
  (let ((result nil))
    (unwind-protect
      (progn
        (format t "  operation on ~A...~%" value)
        (setq result (* value 2)))
      (progn
        (format t "  cleanup~%"))
      (format t "  result = ~A~%" result))
    result))

(format t "~%nsafe-operation(5):")
(safe-operation 5)


;; ── Prog1 / ProgN (local variables) ─────────

(format t "~%n--- prog1 (local variables) ---")

;; prog1/progN introduce local variables and return last value
(prog1 (x y z)
  (setq x 10 y 20 z 30)
  (format t "x=~D y=~D z=~D sum=~D~%" x y z (+ x y z)))


;; ── Destructuring bind with let* ───────────────

(format t "~%n--- Multiple let (let*) ---")

;; let* allows sequential binding where later bindings can use earlier ones
(let* ((x 1)
       (y (+ x 1))
       (z (+ y 1)))
  (format t "x=~D y=~D z=~D~%" x y z))


;; ── Summary ───────────────────────────────────────────

(format t "~%n--- Summary ---")
(format t "Lisp control flow:")
(format t "  - cond for conditionals (if/elif/else pattern)")
(format t "  - Recursion as primary iteration")
(format t "  - dolist for list iteration")
(format t "  - dotimes for fixed iteration")
(format t "  - do for general iteration")
(format t "  - case for value matching")
(format t "  - catch/throw for non-local exits")
(format t "  - tagbody/go for labeled jumps")
(format t "  - unwind-protect for cleanup")
