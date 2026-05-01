package main

import (
	"fmt"
	"sort"
	"strings"
)

// ── Basic function ───────────────────────────────────

func greet(name string) string {
	return "Hello, " + name + "!"
}

// ── Multiple return values ───────────────────────────

func divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, fmt.Errorf("division by zero")
	}
	return a / b, nil
}

// Named return values
func minMax(nums []int) (min, max int) {
	min, max = nums[0], nums[0]
	for _, n := range nums[1:] {
		if n < min {
			min = n
		}
		if n > max {
			max = n
		}
	}
	return // naked return — returns named values
}

// ── Variadic functions ───────────────────────────────

func sum(nums ...int) int {
	total := 0
	for _, n := range nums {
		total += n
	}
	return total
}

// ── Higher-order functions ───────────────────────────

func apply(f func(int) int, values []int) []int {
	result := make([]int, len(values))
	for i, v := range values {
		result[i] = f(v)
	}
	return result
}

func filter(pred func(int) bool, values []int) []int {
	var result []int
	for _, v := range values {
		if pred(v) {
			result = append(result, v)
		}
	}
	return result
}

func reduce(f func(int, int) int, values []int, initial int) int {
	acc := initial
	for _, v := range values {
		acc = f(acc, v)
	}
	return acc
}

// ── Closures ─────────────────────────────────────────

func makeMultiplier(factor int) func(int) int {
	return func(x int) int {
		return x * factor // factor captured from enclosing scope
	}
}

func makeCounter() func() int {
	count := 0
	return func() int {
		count++ // mutates captured variable
		return count
	}
}

// ── Function composition ─────────────────────────────

func compose(f, g func(string) string) func(string) string {
	return func(s string) string {
		return f(g(s))
	}
}

// ── Methods (functions with receivers) ───────────────

type Point struct {
	X, Y float64
}

// Value receiver — doesn't modify original
func (p Point) Scale(factor float64) Point {
	return Point{p.X * factor, p.Y * factor}
}

// String method — satisfies fmt.Stringer interface
func (p Point) String() string {
	return fmt.Sprintf("(%g, %g)", p.X, p.Y)
}

// ── Defer ────────────────────────────────────────────

func demonstrateDefer() {
	fmt.Println("  start")
	defer fmt.Println("  deferred 1 (runs last)")
	defer fmt.Println("  deferred 2 (runs second-to-last)")
	fmt.Println("  end")
	// Deferred calls execute in LIFO order when function returns
}

// ── Main ─────────────────────────────────────────────

func main() {
	// Basic function
	fmt.Println(greet("Atlas"))

	// Multiple return values
	fmt.Println("\n--- Multiple return values ---")
	result, err := divide(10, 3)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Printf("10 / 3 = %.4f\n", result)
	}

	_, err = divide(10, 0)
	if err != nil {
		fmt.Println("Error:", err)
	}

	min, max := minMax([]int{3, 1, 4, 1, 5, 9, 2, 6})
	fmt.Printf("min = %d, max = %d\n", min, max)

	// Variadic functions
	fmt.Println("\n--- Variadic ---")
	fmt.Println("sum(1,2,3) =", sum(1, 2, 3))
	fmt.Println("sum(1..10) =", sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
	nums := []int{10, 20, 30}
	fmt.Println("sum(slice...) =", sum(nums...)) // spread slice

	// First-class functions
	fmt.Println("\n--- First-class functions ---")
	add := func(a, b int) int { return a + b }
	fmt.Println("add(3, 4) =", add(3, 4))

	// Higher-order functions
	fmt.Println("\n--- Higher-order functions ---")
	numbers := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}

	squares := apply(func(x int) int { return x * x }, numbers)
	fmt.Println("squares =", squares)

	evens := filter(func(x int) bool { return x%2 == 0 }, numbers)
	fmt.Println("evens =", evens)

	total := reduce(func(a, b int) int { return a + b }, numbers, 0)
	fmt.Println("sum =", total)

	product := reduce(func(a, b int) int { return a * b }, numbers, 1)
	fmt.Println("product =", product)

	// Sorting with custom comparator
	words := []string{"banana", "apple", "cherry", "date"}
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
	fmt.Println("sorted by length =", words)

	// Closures
	fmt.Println("\n--- Closures ---")
	double := makeMultiplier(2)
	triple := makeMultiplier(3)
	fmt.Println("double(5) =", double(5))
	fmt.Println("triple(5) =", triple(5))

	counter := makeCounter()
	fmt.Println("counter() =", counter())
	fmt.Println("counter() =", counter())
	fmt.Println("counter() =", counter())

	// Function composition
	fmt.Println("\n--- Composition ---")
	shout := compose(strings.ToUpper, strings.TrimSpace)
	fmt.Printf("shout(\"  hello  \") = %q\n", shout("  hello  "))

	// Methods
	fmt.Println("\n--- Methods ---")
	p := Point{3, 4}
	fmt.Println("p =", p)
	fmt.Println("p.Scale(2) =", p.Scale(2))
	fmt.Println("p (unchanged) =", p) // value receiver doesn't modify

	// Defer
	fmt.Println("\n--- Defer ---")
	demonstrateDefer()

	// Immediately invoked function
	fmt.Println("\n--- IIFE ---")
	result2 := func(x int) int { return x * x }(7)
	fmt.Println("(func(x) x*x)(7) =", result2)

	// Summary
	fmt.Println("\n--- Summary ---")
	fmt.Println("Go functions:")
	fmt.Println("  - First-class: assignable, passable")
	fmt.Println("  - Multiple return values (idiomatic error handling)")
	fmt.Println("  - Variadic: func(args ...int)")
	fmt.Println("  - Closures: capture enclosing scope")
	fmt.Println("  - Methods: functions with receivers")
	fmt.Println("  - Defer: cleanup runs when function returns")
	fmt.Println("  - No generics for HOF (until 1.18+)")
}
