;; Concurrency in Clojure
;; ======================
;; atoms, refs, agents, futures

(ns main
  (:require [clojure.core.async :as async]))

(defn -main []
  ;; ── Atom (uncoordinated, synchronous) ─────────────

  (println "--- Atom ---")

  (def counter (atom 0))

  (dotimes [_ 10]
    (swap! counter + 100))

  (println "Counter:" @counter)

  ;; ── Ref (coordinated with dosync) ────────────────

  (println "\n--- Ref with dosync ---")

  (def account1 (ref 100))
  (def account2 (ref 100))

  (defn transfer [amount]
    (dosync
      (alter account1 - amount)
      (alter account2 + amount)))

  (future (transfer 50))
  (future (transfer 30))

  (Thread/sleep 100)
  (println "Account 1:" @account1)
  (println "Account 2:" @account2)

  ;; ── Agent (asynchronous, independent) ────────────

  (println "\n--- Agent ---")

  (def state (agent 0))

  (send state + 10)
  (send state * 2)

  (await-for #(= @state 20) 1000)
  (println "Agent value:" @state)

  ;; ── Future ─────────────────────────────────────────

  (println "\n--- Future ---")

  (def result (future
                (Thread/sleep 200)
                (+ 10 20)))

  (println "Computing...")
  (println "Result:" @result)

  ;; ── Promise (deliver to multiple) ───────────────────

  (println "\n--- Promise ---")

  (def p (promise))

  (future (Thread/sleep 200) (deliver p 42))

  (println "Waiting for promise...")
  (println "Promise delivered:" @p)

  ;; ── pmap (parallel map) ─────────────────────────

  (println "\n--- pmap (parallel map) ---")

  (defn slow-square [x]
    (Thread/sleep 50)
    (* x x))

  (def nums [1 2 3 4 5])
  (def results (pmap slow-square nums))

  (println "Squares (parallel):" results)

  ;; ── core.async channels ─────────────────────────────

  (println "\n--- core.async channels ---")

  (def ch (async/chan 5))

  (go (>! ch 1))
  (go (>! ch 2))
  (go (>! ch 3))

  (println "Receiving from channel:"
    (async/<!! ch)
    (async/<!! ch)
    (async/<!! ch))

  (async/close! ch)

  ;; ── Summary ───────────────────────────────────────

  (println "\n--- Summary ---")
  (println "Clojure concurrency:")
  (println "  - atom: uncoordinated, synchronous updates")
  (println "  - ref: coordinated updates with dosync")
  (println "  - agent: asynchronous, independent updates")
  (println "  - future: run computation in thread pool")
  (println "  - promise: deliver once to multiple")
  (println "  - pmap: parallel map")
  (println "  - core.async: CSP-style channels")
  (println "  - Immutable data by default: no accidental mutation")
)
