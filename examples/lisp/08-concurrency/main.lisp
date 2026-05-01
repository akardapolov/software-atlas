;;; Concurrency in Lisp (SBCL)
;;; ==============================
;;; threads, mutex, condition variables

(defun main ()
  ;; ── Basic thread spawn ─────────────────────────

  (format t "--- Basic thread spawn~%")

  (let ((thread (sb-thread:make-thread
                  (lambda () (format t "Hello from thread!~%")))))
    (sb-thread:join-thread thread))

  ;; ── Shared state with mutex ───────────────────────

  (format t "~%~%Shared state with mutex~%")

  (let ((counter (sb-thread:make-mutex :name "counter"))
        (count 0)
        (threads '()))

    (dotimes (i 10)
      (push (sb-thread:make-thread
             (lambda ()
               (sb-thread:with-mutex (counter)
                 (incf count))))
            threads))

    (dolist (t threads)
      (sb-thread:join-thread t))

    (format t "Counter: ~d~%" count))

  ;; ── Condition variable ───────────────────────────

  (format t "~%~%Condition variable (producer-consumer)~%")

  (let ((mutex (sb-thread:make-mutex))
        (not-empty (sb-thread:make-condition-variable))
        (not-full (sb-thread:make-condition-variable))
        (buffer '())
        (produced 0)
        (max-items 5))

    ;; Producer
    (let ((producer (sb-thread:make-thread
                     (lambda ()
                       (dotimes (i 10)
                         (sb-thread:with-mutex (mutex)
                           (while (>= (length buffer) max-items)
                             (sb-thread:condition-wait not-full mutex))
                           (push produced buffer)
                           (incf produced)
                           (format t "Produced: ~d~%" produced)
                           (sb-thread:condition-notify not-empty))))))
      ;; Consumer
      (let ((consumer (sb-thread:make-thread
                       (lambda ()
                         (dotimes (i 10)
                           (sb-thread:with-mutex (mutex)
                             (while (null buffer)
                               (sb-thread:condition-wait not-empty mutex))
                             (let ((item (pop buffer)))
                               (format t "Consumed: ~d~%" item)
                               (sb-thread:condition-notify not-full)))))))
        (sb-thread:sleep 1.5)

        (format t "~%~%Summary ---~%")
        (format t "Lisp concurrency (SBCL):~%")
        (format t "  - sb-thread:make-thread: spawn threads~%")
        (format t "  - sb-thread:make-mutex: mutual exclusion~%")
        (format t "  - sb-thread:make-condition-variable: signaling~%")
        (format t "  - sb-thread:with-mutex: scoped lock~%")
        (format t "  - sb-thread:condition-wait/notify: wait/signal pattern~%")
        (format t "  - Implementation-specific: each Lisp differs~%")))
