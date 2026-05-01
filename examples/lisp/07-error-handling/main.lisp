;;; Error Handling in Lisp (Common Lisp)
;;; ==============================
;;; condition-case, handler-bind, warn, ignore-errors, signal

(in-package :main)
(use :fiveam)

;;; ── condition-case error handling ─────────────────

(defun safe-divide (numerator denominator)
  (condition-case ()
    (zerop denominator)
      (error division-by-zero)
    (t (numerator / denominator)))

;;; ── handler-bind for cleanup ─────────────────────

(defun with-file-read (filename body-fn)
  (handler-bind ((file-stream error exit)
              (lambda (file-stream condition)
                (format t "Reading file ~a~%" filename)
                (let ((line (read-line file-stream nil)))
                  (format t "Line: ~a~%" line)
                  (close file-stream)
                  (funcall body-fn line))))))

;;; ── Demo functions ─────────────────────────────

(defun demo-condition-case ()
  (format t "--- condition-case ---")
  (format t "safe-divide(10, 2): ~d~%" (safe-divide 10 2))
  (format t "safe-divide(10, 0): ~a~%" (safe-divide 10 0)))

(defun demo-handler-bind ()
  (format t "~%~%handler-bind ---")
  (with-file-read "/proc/version" #'(lambda (line) (format t "Version: ~a~%" line))))

;;; ── warn and ignore-errors ───────────────────────

(defun risky-operation ()
  (declare (ignore x))
  (warn "This operation might fail~%")
  (/ 10 0))  ; Will signal error

(defun demo-ignore-errors ()
  (format t "~%~%ignore-errors ---")
  (risky-operation))

;;; ── signal handling (advanced) ───────────────────

(defun setup-signal-handler (signal)
  (setf (symbol-function 'sigint signal) 'interrupted t))

(defun demo-signal-handler ()
  (format t "~%~%signal handler ---")
  (setup-signal-handler 'sigint)
  (format t "Signal handler set. Send SIGINT to interrupt...~%")
  (sleep 2))  ; Wait for interrupt or timeout
  (format t "Continuing...~%"))

;;; ── restart-case (pattern matching) ───────────

(defun process-data (state)
  (case state
    (init "Data initialized")
    (processing "Processing...")
    (done "Done")))

(defun demo-restart-case ()
  (format t "~%~%restart-case ---")
  (process-data 'processing))

;;; ── uncaught error handling ───────────────────────

(defun safe-operation ()
  (unwind-protect
      (progn (risky-operation))
      (error "Operation failed")))

(defun demo-unwind-protect ()
  (format t "~%~%unwind-protect ---")
  (let ((result (safe-operation)))
    (format t "Result: ~a~%" (if result t "Success" "Failure"))))

(defun main ()
  (demo-condition-case)
  (demo-handler-bind)
  (demo-ignore-errors)
  ;; (demo-signal-handler)  ; Commented - interactive
  (demo-restart-case)
  (demo-unwind-protect)

  (format t "~%~%Summary ---")
  (format t "Lisp error handling:")
  (format t "  - condition-case: handle error conditions")
  (format t "  - handler-bind: cleanup with unwind-protect")
  (format t "  - warn: warning messages without aborting")
  (format t "  - ignore-errors: ignore specific errors")
  (format t "  - restart-case: pattern matching for state machines")
  (format t "  - signals: advanced error handling")
  (format t "  - Implementation-specific: each Lisp differs"))
