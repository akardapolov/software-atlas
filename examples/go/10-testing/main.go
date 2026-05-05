// Testing in Go
// ============
// testing package, table-driven tests, benchmarks

import "fmt"
import "testing"

// ── Basic test ───────────────────────────────────

func TestAddition(t *testing.T) {
    result := 5 + 3
    if result != 8 {
        t.Errorf("expected 8, got %d", result)
    }
}

func TestStringLength(t *testing.T) {
    s := "hello"
    expected := 5
    if len(s) != expected {
        t.Errorf("expected %d, got %d", expected, len(s))
    }
}

// ── Table-driven test ─────────────────────────────

func TestSquares(t *testing.T) {
    tests := []struct {
        input    int
        expected int
    }{
        {1, 1},
        {2, 4},
        {3, 9},
        {5, 25},
        {-3, 9},
    }

    for _, tt := range tests {
        t.Run(fmt.Sprintf("%d", tt.input), func(t *testing.T) {
            result := tt.input * tt.input
            if result != tt.expected {
                t.Errorf("input %d: expected %d, got %d",
                    tt.input, tt.expected, result)
            }
        })
    }
}

// ── Subtests ──────────────────────────────────────

func TestUser(t *testing.T) {
    t.Run("valid", func(t *testing.T) {
        user := struct{ Name: "Alice", Age: 30 }
        if user.Name != "Alice" {
            t.Error("name mismatch")
        }
    })

    t.Run("too young", func(t *testing.T) {
        user := struct{ Name: "Bob", Age: 15 }
        if user.Age >= 18 {
            t.Error("should fail age check")
        }
    })
}

type User struct {
    Name string
    Age  int
}

// ── Assertions helper ───────────────────────────────

func assertEqual(t *testing.T, expected, actual int) {
    t.Helper()
    if expected != actual {
        t.Errorf("expected %d, got %d", expected, actual)
    }
}

// ── Example test (documentation) ───────────────

func ExampleHello() {
    fmt.Println("Hello, World!")
    // Output: Hello, World!
}

// ── Benchmark ─────────────────────────────────────

func BenchmarkFibonacci(b *testing.B) {
    for n := 0; n < b.N; n++ {
        fib(20)
    }
}

func fib(n int) int {
    if n <= 1 {
        return n
    }
    return fib(n-1) + fib(n-2)
}

// ── Summary ─────────────────────────────────────

func TestSummary(t *testing.T) {
    t.Log("\n--- Summary ---")
    t.Log("Go testing:")
    t.Log("  - testing package: Test(t *testing.T)")
    t.Log("  - Subtests: t.Run() for organization")
    t.Log("  - Table-driven: loop over test cases")
    t.Log("  - Benchmarks: Benchmark(b *testing.B)")
    t.Log("  - Example tests: Example() for documentation")
    t.Log("  - Assertions: manual or with testify/assert")
    t.Log("  - Mocking: gomock for interface mocking")
    t.Log("  - Race detection: go test -race")
}
