;;; FP Features in Lisp (Common Lisp)
;;; ==============================
;;; First-class functions, closures, higher-order functions

(defun main ()
  ;; ── Basic functions ─────────────────────────────

  (format t "--- Basic functions ---")
  (format t "add(5, 3) = ~d~%" (+ 5 3))
  (format t "square(4) = ~d~%" (* 4 4))


  ;; ── Higher-order functions ───────────────────────────

  (format t "~%~%Higher-order functions ---")

  (defun apply-twice (f x)
    (funcall f (funcall f x)))

  (defun inc (x) (+ x 1))
  (format t "apply-twice(inc, 5) = ~d~%" (apply-twice #'inc 5))


  ;; ── Mapcar/Remove-if/Reduce ─────────────────────

  (format t "~%~%List functions ---")

  (setf numbers '(1 2 3 4 5 6 7 8 9 10))

  ;; mapcar
  (let ((squares (mapcar (lambda (x) (* x x)) numbers)))
    (format t "Squares: ~a~%" squares))

  ;; remove-if-not (filter)
  (let ((evens (remove-if-not (lambda (x) (zerop (% x 2))) numbers)))
    (format t "Evens: ~a~%" evens))

  ;; reduce (fold)
  (let ((sum (reduce #'+ 0 numbers)))
    (format t "Sum: ~d~%" sum))

  ;; find-if
  (let ((first-even (find-if (lambda (x) (zerop (% x 2))) numbers)))
    (format t "First even: ~d~%" first-even))


  ;; ── Closures ─────────────────────────────────────

  (format t "~%~%Closures ---")

  (defun make-multiplier (factor)
    (lambda (x) (* x factor)))

  (setq times-three (make-multiplier 3))
  (setq times-five (make-multiplier 5))

  (format t "times-three(7) = ~d~%" (funcall times-three 7))
  (format t "times-five(8) = ~d~%" (funcall times-five 8))


  ;; ── Function composition ─────────────────────────────

  (format t "~%~%Function composition ---")

  (defun compose (f g)
    (lambda (x) (funcall f (funcall g x))))

  (defun shout (s)
    (string-upcase (string-trim s)))

  (defun greet (name)
    (concatenate "Hello, " name))

  (setq shout-greet (compose #'shout #'greet))
  (format t "shout-greet(\"alice\") = ~a~%" (funcall shout-greet "alice"))


  ;; ── Destructuring ─────────────────────────────────────

  (format t "~%~%Destructuring ---")

  (defun sum-point (point)
    (destructuring-bind (x y) point
      (+ x y)))

  (setq my-point '(3 . 4))
  (format t "Sum of (3 . 4) = ~d~%" (sum-point my-point))


  ;; ── Lambda with destructuring ─────────────────────

  (format t "~%~%Lambda with destructuring ---")

  (mapcar (lambda (pair)
               (destructuring-bind (a b) pair
                 (format t "a=~a, b=~b"~%)))
            '((1 . 2) (3 . 4)))


  ;; ── Recursion ─────────────────────────────────────

  (format t "~%~%Recursion ---")

  (defun factorial (n)
    (if (<= n 1)
        1
        (* n (factorial (- n 1)))))

  (format t "factorial(5) = ~d~%" (factorial 5))
  (format t "factorial(10) = ~d~%" (factorial 10))


  ;; ── Tail recursion ─────────────────────────────────

  (format t "~%~%Tail recursion ---")

  (defun factorial-tr (n)
    (factorial-tr-acc n 1))

  (defun factorial-tr-acc (n acc)
    (if (<= n 1)
        acc
        (factorial-tr-acc (- n 1) (* n acc))))

  (format t "factorial-tr(5) = ~d~%" (factorial-tr 5))
  (format t "factorial-tr(10) = ~d~%" (factorial-tr 10))


  ;; ── Filter with custom predicate ─────────────────────

  (format t "~%~%Filter with custom predicate ---")

  (defun is-multiple-of-3 (x)
    (zerop (% x 3)))

  (let ((multiples (remove-if-not #'is-multiple-of-3 numbers)))
    (format t "Multiples of 3: ~a~%" multiples))


  ;; ── Summary ─────────────────────────────────────

  (format t "~%~%Summary ---")
  (format t "Lisp FP features:")
  (format t "  - First-class functions: assignable, passable, returnable~%")
  (format t "  - Closures: lambda captures lexical scope~%")
  (format t "  - Higher-order: mapcar, remove-if, reduce~%")
  (format t "  - Cons cells: O(1) linked lists~%")
  (format t "  - Destructuring: destructuring-bind, lambda params~%")
  (format t "  - Recursion: only iteration mechanism~%")
  (format t "  - Tail recursion: implementation-dependent~%")
  (format t "  - Function composition: compose functions~%")
  (format t "  - Lambda: anonymous functions~%")
  (format t "  - Property lists: assoc for key-value pairs~%")
  (format t "  - Optional: standard functions vs external libs~%"))
