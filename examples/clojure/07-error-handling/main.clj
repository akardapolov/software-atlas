;; Error Handling in Clojure
;; ======================
;; try-catch-finally, ex-info, nil-punning, keyword results

(ns main)

;; ── Custom exception with ex-info ───────────────────

(defn business-error
  "Domain-specific error")

(defn throw-custom
  (throw (ex-info business-error "Business logic failed"))

;; ── try-catch-finally ───────────────────────────

(defn try-finally-demo
  (println "--- try-catch-finally ---")
  (let [file-created (atom false)]
    (try
      (spit "creating file")
      (.reset! file-created true)
      (throw (ex-info "file-error" "Simulated error"))
    (catch Exception e
      (.print "Caught exception:" e))
    (finally
      (println "Finally executed, created:" @file-created))))

;; ── Catch specific exception types ───────────────────

(defn catch-specific
  (println "\n--- Catch specific exceptions ---")
  (try
    (throw (ex-info "io-error" "IO failed"))
    (catch clojure.lang.ExceptionInfo e
      (when (= :type (ex-cause e))
        (println "Caught IO exception:" (ex-message e))))
    (catch ArithmeticException e
      (println "Caught arithmetic exception:" (.getMessage e)))
    (catch Exception e
      (println "Caught general exception:" e))))

;; ── Exception hierarchy ───────────────────────────

(defn check-exception-hierarchy
  (println "\n--- Exception hierarchy ---")
  (try
    (throw (NullPointerException.))
    (catch NullPointerException e
      (println "Caught NPE: true"))
    (catch Exception e
      (println "Caught generic Exception:" false))))

;; ── nil-punning (defensive programming) ─────────────

(defn safe-divide
  "Returns nil on division by zero for nil-punning"
  [num denom]
  (when (zero? denom)
    nil
    (/ num denom)))

(defn safe-get
  "Returns nil for nil input, throws for invalid"
  [value]
  (when (nil? value)
    nil
    value))

(defn nil-punning-demo
  (println "\n--- nil-punning ---")
  (println "safe-divide(10, 0):" (safe-divide 10 0))
  (println "safe-divide(10, 2):" (safe-divide 10 2))
  (println "safe-get(nil):" (safe-get nil))
  (println "safe-get(42):" (safe-get 42))

;; ── Keyword results ─────────────────────────────────

(defn parse-user-id
  "Returns ::success or ::error keyword"
  [input]
  (try
    (let [id (Integer/parseInt input)]
      (if (pos? id)
        ::success id
        ::error ::invalid-id))
    (catch NumberFormatException e
      ::error ::invalid-number)))

(defn keyword-result-demo
  (println "\n--- Keyword results ---")
  (println "parse-user-id('abc'):" (parse-user-id "abc"))
  (println "parse-user-id('-1'):" (parse-user-id "-1"))
  (println "parse-user-id('999999'):" (parse-user-id "999999"))

;; ── assert (precondition checking) ─────────────

(defn check-positive
  "Throws AssertionError if value <= 0"
  [value]
  (when (<= value 0)
    (assert (str "Value must be positive: " value))))

(defn assert-demo
  (println "\n--- assert demo ---")
  (check-positive 10)
  (check-positive -5)
  (try
    (check-positive 0)
    (catch AssertionError e
      (println "Caught assertion error"))))

;; ── with-open (resource cleanup) ───────────────

(defn with-open-file
  "Ensures file is closed after execution"
  []
  (with-open [rdr (clojure.java.io/reader "test.txt")]
    (if-let [line (.readLine rdr)]
      (println "Read:" line))))

;; ── Summary ───────────────────────────────────────

(defn -main
  (try-finally-demo)
  (catch-specific)
  (check-exception-hierarchy)
  (nil-punning-demo)
  (keyword-result-demo)
  (assert-demo)

  (println "\n--- Summary ---")
  (println "Clojure error handling:")
  (println "  - ex-info: structured exception with data")
  (println "  - try-catch-finally: structured error handling")
  (println "  - catch: catches specific exception types")
  (println "  - nil-punning: return nil for failures (defensive)")
  (println "  - Keyword results: ::success vs ::error")
  (println "  - assert: precondition checking with AssertionError")
  (println "  - with-open: resource cleanup pattern")
  (println "  - Inherit from Java: Exception hierarchy"))
