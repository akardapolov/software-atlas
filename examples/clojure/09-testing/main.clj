;; Testing in Clojure
;; ======================
;; clojure.test, deftest, is/are assertions

(ns main
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]))

;; ── Basic test ───────────────────────────────────

(deftest test-addition
  (testing "addition"
    (is (= 8 (+ 5 3))))

(deftest test-string-ops
  (testing "string operations"
    (is (= 5 (count "hello")))
    (is (= "olleh" (clojure.string/reverse "hello")))))

;; ── Grouping tests ───────────────────────────────

(deftest test-math-operations
  (testing "math operations"
    (is (= 10 (* 5 2)))
    (is (= 2 (/ 10 5)))
    (is (= 3 (mod 10 3)))))

(deftest test-vector-operations
  (testing "vector operations"
    (is (= 6 (reduce + [1 2 3])))
    (is (= 6 (count [1 2 3])))))

;; ── Testing with fixtures ─────────────────────────────

(defn setup-fixtures []
  {:numbers [1 2 3 4 5]})

(deftest test-with-fixture
  (testing "using fixture data"
    (let [nums (setup-fixtures)]
      (is (= 15 (reduce + nums))))))

;; ── Exception testing ──────────────────────────────

(deftest test-division-by-zero
  (testing "division by zero"
    (is (thrown? ArithmeticException (/ 10 0)))))

;; ── Test using testing macro ─────────────────────

(deftest test-sets
  (testing "set operations"
    (testing "union"
      (is (= #{1 2 3 4 5} (clojure.set/union #{1 2 3} #{4 5})))
    (testing "intersection"
      (is (= #{2 3} (clojure.set/intersection #{1 2 3 4} #{2 3 5})))))

;; ── Testing async code ──────────────────────────────

(deftest test-future
  (testing "async testing"
    (let [f (future (+ 1 2))]
      (is (= 3 @f)))))

;; ── Property-based test (test.check) ──────────────

;; Note: requires [org.clojure/test.check "0.11.0" or later

(tctest/defspec reverse-twice-is-identity
  (tc/forall [xs (gen/vector gen/int)]
    (= (clojure.string/reverse (clojure.string/reverse xs)) xs)))

;; ── Summary ───────────────────────────────────────

(deftest test-summary
  (testing "clojure.test features"
    (is true)
    (is false)))

;; ── Running tests ───────────────────────────────────
;; Run with: lein test
