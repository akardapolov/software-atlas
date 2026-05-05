package main

import (
	"fmt"
	"math"
	"reflect"
)

// ── Types for structural typing demo ─────────────────────

// Quacker is an interface — any type with Quack() string satisfies it.
// No "implements" keyword needed.
type Quacker interface {
	Quack() string
}

type Duck struct{}

func (d Duck) Quack() string { return "Quack!" }

type Person struct{ Name string }

func (p Person) Quack() string { return p.Name + " quacks!" }

// ── Point struct ─────────────────────────────────────────

type Point struct {
	X, Y float64
}

func (p Point) DistanceFromOrigin() float64 {
	return math.Sqrt(p.X*p.X + p.Y*p.Y)
}

// ── Main ─────────────────────────────────────────────────

func main() {
	// ── Basic types ──────────────────────────────────────

	// Explicit type declaration
	var count int = 42
	fmt.Printf("count = %d, type = %T\n", count, count)

	// Type inference with :=
	pi := 3.14159 // inferred as float64
	fmt.Printf("pi = %f, type = %T\n", pi, pi)

	active := true // inferred as bool
	fmt.Printf("active = %t, type = %T\n", active, active)

	name := "Software Engineering Atlas" // inferred as string
	fmt.Printf("name = %s, type = %T\n", name, name)

	// ── Zero values ──────────────────────────────────────
	// Every type has a well-defined zero value. No uninitialised variables.

	fmt.Println("\n--- Zero values ---")
	var zeroInt int
	var zeroFloat float64
	var zeroBool bool
	var zeroString string
	var zeroPointer *int

	fmt.Printf("int:     %d\n", zeroInt)    // 0
	fmt.Printf("float64: %f\n", zeroFloat)  // 0.0
	fmt.Printf("bool:    %t\n", zeroBool)     // false
	fmt.Printf("string:  %q\n", zeroString)   // "" (empty)
	fmt.Printf("pointer: %v\n", zeroPointer) // <nil>

	// ── Integer types ────────────────────────────────────
	// Go has explicit sized integers: int8, int16, int32, int64
	// and unsigned variants: uint8, uint16, uint32, uint64
	// `int` is platform-dependent (32 or 64 bit)

	fmt.Println("\n--- Integer types ---")
	var small int8 = 127 // max value for int8
	var medium int32 = 2_000_000_000
	var large int64 = 9_000_000_000_000_000_000
	var unsigned uint8 = 255 // byte alias

	fmt.Printf("int8:   %d (max %d)\n", small, math.MaxInt8)
	fmt.Printf("int32:  %d\n", medium)
	fmt.Printf("int64:  %d\n", large)
	fmt.Printf("uint8:  %d (max %d)\n", unsigned, math.MaxUint8)

	// ── No implicit conversions ──────────────────────────
	// Go does NOT implicitly convert between types, even numeric ones

	fmt.Println("\n--- No implicit conversions ---")
	var a int32 = 10
	var b int64 = 20

	// c := a + b  // COMPILE ERROR: mismatched types int32 and int64

	c := int64(a) + b // explicit conversion required
	fmt.Printf("int64(%d) + %d = %d\n", a, b, c)

	// ── Strings ──────────────────────────────────────────

	fmt.Println("\n--- Strings ---")
	greeting := "Hello, 世界" // UTF-8 by default
	fmt.Printf("greeting = %s\n", greeting)
	fmt.Printf("len (bytes) = %d\n", len(greeting))

	// Iterate over runes (Unicode code points), not bytes
	for i, r := range greeting {
		fmt.Printf("  [%d] %c (U+%04X)\n", i, r, r)
	}

	// Strings are immutable
	// greeting[0] = 'h'  // COMPILE ERROR

	// ── Arrays and slices ────────────────────────────────

	fmt.Println("\n--- Arrays and slices ---")

	// Array — fixed size, part of the type
	arr := [3]int{10, 20, 30}
	fmt.Printf("array = %v, type = %T\n", arr, arr)

	// Slice — dynamic, reference to underlying array
	fruits := []string{"apple", "banana", "cherry"}
	fruits = append(fruits, "date")
	fmt.Printf("slice = %v, len=%d, cap=%d\n", fruits, len(fruits), cap(fruits))

	// ── Maps ─────────────────────────────────────────────

	fmt.Println("\n--- Maps ---")
	ages := map[string]int{
		"Ada":   36,
		"Grace": 85,
	}
	fmt.Printf("ages = %v\n", ages)

	// Accessing a missing key returns zero value
	score, ok := ages["Alan"]
	fmt.Printf("ages[\"Alan\"] = %d, ok = %t\n", score, ok)

	// ── Structs ──────────────────────────────────────────

	fmt.Println("\n--- Structs ---")
	p := Point{X: 3.0, Y: 4.0}
	fmt.Printf("point = %+v\n", p)
	fmt.Printf("distance from origin = %.2f\n", p.DistanceFromOrigin())

	// ── Pointers ─────────────────────────────────────────

	fmt.Println("\n--- Pointers ---")
	x := 42
	ptr := &x // pointer to x
	fmt.Printf("x = %d, ptr = %p, *ptr = %d\n", x, ptr, *ptr)

	*ptr = 100 // modify x through pointer
	fmt.Printf("after *ptr = 100: x = %d\n", x)
	// No pointer arithmetic! (unlike C)

	// ── Structural typing (interfaces) ───────────────────

	fmt.Println("\n--- Structural typing ---")

	// Both Duck and Person satisfy Quacker interface
	// because they have a Quack() string method.
	// No "implements" keyword — it's automatic.

	var q Quacker

	q = Duck{}
	fmt.Printf("Duck: %s\n", q.Quack())

	q = Person{Name: "Bob"}
	fmt.Printf("Person: %s\n", q.Quack())

	// ── Type reflection ──────────────────────────────────

	fmt.Println("\n--- Reflection ---")
	values := []any{42, 3.14, "hello", true}
	for _, v := range values {
		fmt.Printf("  value = %-8v type = %v\n", v, reflect.TypeOf(v))
	}

	// ── Summary ──────────────────────────────────────────

	fmt.Println("\n--- Summary ---")
	fmt.Println("Go: static + strong typing")
	fmt.Println("  - Types checked at compile time (static)")
	fmt.Println("  - No implicit conversions (strong)")
	fmt.Println("  - Type inference with :=")
	fmt.Println("  - Zero values — no uninitialised variables")
	fmt.Println("  - Structural typing for interfaces")
	fmt.Println("  - Sized integers (int8..int64)")
}
