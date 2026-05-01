// FP Features in Go
// ===================
// First-class functions, closures, slices

package main

import "fmt"


// ── First-class functions ───────────────────────────

func add(a, b int) int {
    return a + b
}

func multiply(a, b int) int {
    return a * b
}

// Function as value
func calculate(x int, op func(int, int) int) {
    return op(x, x)
}


// ── Higher-order functions ───────────────────────────

func applyTwice(f func(int) int, x int) int {
    return f(f(x))
}

func applyOp(f func(int, int) int, op func(int, int) int, x int) int {
    return op(f(x), x)
}


// ── Closures ─────────────────────────────────────

func makeMultiplier(factor int) func(int) int {
    return func(x int) int {
        return x * factor
    }
}


// ── Anonymous functions (function literals) ───────

func main() {

    // ── First-class functions demo ─────────────────

    fmt.Println("--- First-class functions ---")
    fmt.Printf("add(5, 3) = %d\n", add(5, 3))
    fmt.Printf("multiply(4, 7) = %d\n", multiply(4, 7))

    // Higher-order
    result1 := applyTwice(func(x int) int { return x + 1 }, 5)
    result2 := applyOp(add, multiply, 4)
    fmt.Printf("applyTwice(+1, 5) = %d\n", result1)
    fmt.Printf("applyOp(add, mul, 4) = %d\n", result2)


    // ── Closures demo ─────────────────────────────

    fmt.Println("\n--- Closures ---")

    timesThree := makeMultiplier(3)
    timesFive := makeMultiplier(5)

    fmt.Printf("timesThree(7) = %d\n", timesThree(7))
    fmt.Printf("timesFive(8) = %d\n", timesFive(8))


    // ── Anonymous functions demo ────────────────────

    fmt.Println("\n--- Anonymous functions ---")

    // Map over slice
    numbers := []int{1, 2, 3, 4, 5}

    squares := make([]int, len(numbers))
    for i, n := range numbers {
        squares[i] = func(x int) int { return x * x }(n)
    }

    fmt.Printf("Squares: %v\n", squares)


    // ── Filter with anonymous function ─────────────────

    fmt.Println("\n--- Filter with anonymous function ---")

    evens := []int{}
    for _, n := range numbers {
        if func(x int) bool { return x%2 == 0 }(n) {
            evens = append(evens, n)
        }
    }

    fmt.Printf("Evens: %v\n", evens)


    // ── Reduce (fold) ───────────────────────────

    fmt.Println("\n--- Reduce (fold) ---")

    sum := 0
    for _, n := range numbers {
        sum = func(acc, x int) int { return acc + x }(sum, n)
    }

    fmt.Printf("Sum: %d\n", sum)


    // ── Function composition simulation ─────────────────

    fmt.Println("\n--- Function composition ---")

    // Go doesn't have compose operator
    // We simulate it manually

    compose := func(f, g func(int) int) func(int) int {
        return func(x int) int {
            return f(g(x))
        }
    }

    addOne := func(x int) int { return x + 1 }
    triple := func(x int) int { return x * 3 }

    addOneThenTriple := compose(triple, addOne)

    fmt.Printf("compose(triple, addOne)(5) = %d\n", addOneThenTriple(5))


    // ── Summary ─────────────────────────────────────

    fmt.Println("\n--- Summary ---")
    fmt.Println("Go FP features:")
    fmt.Println("  - First-class functions: assignable, passable, returnable")
    fmt.Println("  - Closures: capture enclosing scope")
    fmt.Println("  - Function types: func(T) T signature")
    fmt.Println("  - Higher-order: accept/return functions")
    fmt.Println("  - Anonymous functions: func(x) { ... }")
    fmt.Println("  - Multi-return values: (T, error) for errors")
    fmt.Println("  - Slices: dynamic arrays with len(), range()")
    fmt.Println("  - Not pure: side effects allowed")
    fmt.Println("  - No map/filter: write loops or external packages")
    fmt.Println("  - Generics (1.18+): type parameters [T](x) T")
}
