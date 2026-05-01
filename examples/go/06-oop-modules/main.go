/*
OOP and Modules in Go
========================
Demonstrates structs, methods, interfaces, embedding, and packages.
Go has classes limited to methods on structs. No inheritance.
*/

package main

import (
	"fmt"
	"math"
)

// ── Structs and Methods ────────────────────────────

func demonstrateStructs() {
	fmt.Println("--- Structs and Methods ---")

	// Struct definition
	type Point struct {
		X, Y int
	}

	p := Point{X: 10, Y: 20}
	fmt.Printf("Point: X=%d, Y=%d\n", p.X, p.Y)

	// Method definition (value receiver)
	func (p Point) String() string {
		return fmt.Sprintf("(%d, %d)", p.X, p.Y)
	}

	fmt.Printf("p.String() = %s\n", p.String())


	// Pointer receiver for mutation
	type Counter struct {
		count int
	}

	func (c *Counter) Increment() {
		c.count++
	}

	counter := Counter{count: 0}
	counter.Increment()
	fmt.Printf("counter.count = %d\n", counter.count)
}


// ── Interfaces (Go's polymorphism) ─────────────────────

func demonstrateInterfaces() {
	fmt.Println("\n--- Interfaces ---")

	// Interface definition
	type Shape interface {
		Area() float
		Perimeter() float
	}

	// Struct implementing interface
	type Rectangle struct {
		Width, Height float
	}

	func (r Rectangle) Area() float {
		return r.Width * r.Height
	}

	func (r Rectangle) Perimeter() float {
		return 2 * (r.Width + r.Height)
	}

	// Circle implementing interface
	type Circle struct {
		Radius float
	}

	func (c Circle) Area() float {
		return math.Pi * c.Radius * c.Radius
	}

	func (c Circle) Perimeter() float {
		return 2 * math.Pi * c.Radius
	}

	// Polymorphic function
	var shape Shape

	shape = Rectangle{Width: 5, Height: 3}
	fmt.Printf("Rectangle area = %.2f\n", shape.Area())

	shape = Circle{Radius: 2}
	fmt.Printf("Circle area = %.2f\n", shape.Area())


	// Empty interface (type erasure)
	type Shapeer interface {
		Describe() string
	}

	type Triangle struct{}

	func (t Triangle) Describe() string {
		return "Triangle"
	}

	var shapeer Shapeer = Triangle{}
	fmt.Printf("shapeer.Describe() = %s\n", shapeer.Describe())
}


// ── Embedding (composition) ─────────────────────

func demonstrateEmbedding() {
	fmt.Println("\n--- Embedding (composition) ---")

	// Embedded struct
	type Address struct {
		Street, City, Country string
	}

	type Person struct {
		Name string
		Age  int
		Address // Embedded struct
	}

	person := Person{
		Name:  "Ada",
		Age:  36,
		Address: Address{
			Street: "123 Main St",
			City:    "London",
			Country: "UK",
		},
	}

	// Access embedded fields directly
	fmt.Printf("Person: Name=%s, Age=%d\n", person.Name, person.Age)
	fmt.Printf("Address: %s, %s, %s\n", person.Street, person.City, person.Country)
}


// ── Constructors (factory functions) ─────────────

func demonstrateConstructors() {
	fmt.Println("\n--- Constructors ---")

	// Struct with constructor
	type Person2 struct {
		Name string
		Age  int
	}

	func NewPerson(name string, age int) *Person2 {
		return &Person2{
			Name: name,
			Age: age,
		}
	}

	ada := NewPerson("Ada", 36)
	fmt.Printf("Person: Name=%s, Age=%d\n", ada.Name, ada.Age)
}


// ── Methods and Pointers ─────────────────────────

func demonstratePointers() {
	fmt.Println("\n--- Methods with Pointers ---")

	type Buffer struct {
		data []byte
		size int
	}

	// Value receiver (copy on modification)
	func (b Buffer) Write(p []byte) int {
		copy(b.data, p)
		b.size += len(p)
	}

	buf := Buffer{}
	buf.Write([]byte{1, 2, 3})
	fmt.Printf("buf.size = %d, data = %v\n", buf.size, buf.data)

	// Pointer receiver (no copy, mutation)
	func (b *Buffer) Append(p []byte) int {
		b.data = append(b.data, p...)
		b.size += len(p)
	}

	buf2 := Buffer{}
	buf2.Append([]byte{4, 5, 6})
	fmt.Printf("buf2.size = %d, data = %v\n", buf2.size, buf2.data)
}


// ── Visibility (exported) ─────────────────────

type Counter struct {
	count int
}

// Exported function (public)
func ExportedIncrement(c *Counter) {
	c.count++
}

// Unexported function (private to package)
func unexportedIncrement(c *Counter) {
	c.count++
}

func demonstrateVisibility() {
	fmt.Println("\n--- Visibility (Exported vs Private) ---")

	counter := Counter{}

	// Exported: callable from any package
	ExportedIncrement(&counter)
	fmt.Printf("Exported: count = %d\n", counter.count)

	// Unexported: only in same package
	unexportedIncrement(&counter)
	fmt.Printf("Unexported: count = %d\n", counter.count)
}


// ── Goroutines (concurrency) ─────────────────────

func demonstrateGoroutines() {
	fmt.Println("\n--- Goroutines (lightweight threads) ---")

	// Channel for communication
	ch := make(chan int)

	// Launch goroutines
	go func() {
		ch <- 1
		ch <- 2
		ch <- 3
	}()

	// Receive from channel
	fmt.Println("Receiving from channel:")
	for i := 0; i < 3; i++ {
		value := <-ch
		fmt.Printf("Received: %d\n", value)
	}

	close(ch)
}


// ── Select (multiplexing channels) ─────────────

func demonstrateSelect() {
	fmt.Println("\n--- Select (multiplexing channels) ---")

	ch1 := make(chan string)
	ch2 := make(chan string)

	go func() {
		ch1 <- "from ch1"
	}()

	go func() {
		ch2 <- "from ch2"
	}()

	// Select waits for first ready channel
	select {
	case msg := <-ch1:
		fmt.Printf("Received from ch1: %s\n", msg)
	case msg := <-ch2:
		fmt.Printf("Received from ch2: %s\n", msg)
	}
}


// ── Error Handling (multiple values) ─────────────

func demonstrateErrorHandling() {
	fmt.Println("\n--- Error Handling ---")

	// Return multiple values (error pattern)
	type Result struct {
		Value  int
		Error error
	}

	func divide(a, b int) Result {
		if b == 0 {
			return Result{Error: fmt.Errorf("division by zero")}
		}
		return Result{Value: a / b}
	}

	result := divide(10, 2)
	fmt.Printf("Result: %+v\n", result)

	result = divide(10, 0)
	fmt.Printf("Error Result: %+v\n", result.Error)
}


// ── Summary ───────────────────────────────────────

func main() {
	fmt.Println("OOP and Modules in Go")
	fmt.Println("========================")

	demonstrateStructs()
	demonstrateInterfaces()
	demonstrateEmbedding()
	demonstrateConstructors()
	demonstratePointers()
	demonstrateVisibility()
	demonstrateGoroutines()
	demonstrateSelect()
	demonstrateErrorHandling()

	fmt.Println("\n--- Summary ---")
	fmt.Println("Go OOP:")
	fmt.Println("  - Structs: data types with methods (value/pointer receivers)")
	fmt.Println("  - Interfaces: duck typing with explicit contracts")
	fmt.Println("  - Embedding: composition (not inheritance)")
	fmt.Println("  - No inheritance: use embedding instead")
	fmt.Println("  - Exported: exported functions/vars")
	fmt.Println("  - Unexported: private to package")
	fmt.Println("  - Goroutines: lightweight concurrency (channels, select)")
	fmt.Println("  - Multiple values: (value, error) pattern")
	fmt.Println("  - Pointers: explicit memory control")
}
