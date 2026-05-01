;;; Testing in Lisp (Common Lisp)
;;; ==============================
;;; FiveAM, assert macro, test lists

(in-package :main)
(use :fiveam)

;; ── Basic test ─────────────────────────────────────

(def-suite basic-tests)

(test basic-addition
  "Addition should work"
  (is (= 8 (+ 5 3))))

(test basic-string-length
  "String length"
  (is (= 5 (length "hello"))))

(test basic-list-operations
  "List operations"
  (is (= 6 (reduce #'+ '(1 2 3)))))

;; ── Exception testing ──────────────────────────────

(defun safe-divide (a b)
  (if (zerop b)
      (error "division-by-zero")
      (/ a b)))

(test test-division-by-zero
  "Division by zero should error"
  (signals error (safe-divide 10 0)))

;; ── Multiple test cases ─────────────────────────────

(def-suite math-tests)

(test math-square-1
  "Square of 1"
  (is (= 1 (* 1 1))))

(test math-square-2
  "Square of 2"
  (is (= 4 (* 2 2))))

(test math-square-3
  "Square of 3"
  (is (= 9 (* 3 3))))

;; ── Test with setup/teardown ─────────────────────

(defvar *test-count* 0)

(defun setup-counter ()
  (setf *test-count* 0))

(defun teardown-counter ()
  (setf *test-count* -1))

(test test-with-fixtures
  "Test with fixtures"
  :setup setup-counter
  :teardown teardown-counter
  (is (= 1 *test-count*)))

;; ── Property-style test (simplified) ─────────────

(test list-reverse-twice
  "Reverse twice returns original"
  (is (= '(1 2 3)
             (reverse (reverse '(1 2 3))))))

;; ── Summary test ─────────────────────────────────────

(test summary
  "Testing framework works"
  (is (equal t t)))

;; ── Running tests ───────────────────────────────────
;; Run with:
;; sbcl --load main.lisp --eval '(fiveam:run! (list :basic-tests :math-tests))'
