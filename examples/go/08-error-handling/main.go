// Error Handling in Go
// ===================
// Multi-return values, error type, panic, defer

package main

import (
	"errors"
	"fmt"
	"os"
)

// ── Custom error type ─────────────────────────────

type FileSystemError struct {
	Path   string
	Message string
}

func (e *FileSystemError) Error() string {
	return e.Message
}

// ── Functions returning errors ─────────────────────────

func readFile(path string) (string, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return "", err
	}
	return string(data), nil
}

func safeDivide(a, b int) (int, error) {
	if b == 0 {
		return 0, errors.New("division by zero")
	}
	return a / b, nil
}

// ── Error comparison with errors.Is ───────────────

func main() {
	fmt.Println("--- Error return values ---")

	result, err := safeDivide(10, 2)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("Result:", result)
	}

	// ── Custom error
	var errNotFound = &FileSystemError{
		Path: "config.txt",
		Message: "file not found",
	}

	if errors.Is(errNotFound, os.ErrNotExist) {
		fmt.Println("Is file not found error")
	}

	// ── Error wrapping with fmt.Errorf ───────

	_, err = readFile("test.txt")
	if err != nil {
		wrappedErr := fmt.Errorf("read failed: %w", err)
		fmt.Println("Wrapped error:", wrappedErr)
	}

	// ── Error unwrapping with errors.As ───────

	_, err = readFile("test.txt")
	if err != nil {
		var pathError *os.PathError
		if errors.As(err, &pathError) {
			fmt.Println("Unwrapped as PathError")
		}
	}

	// ── Defer (always executes) ─────────────────

	fmt.Println("\n--- Defer demo ---")

	file, err := os.Create("defer.txt")
	if err != nil {
		fmt.Println("Error creating file:", err)
		return
	}

	defer func() {
		fmt.Println("Defer: closing file")
		file.Close()
	}

	fmt.Println("Writing to file")
	file.WriteString("test content")

	// ── Panic (unrecoverable) ─────────────────

	fmt.Println("\n--- Panic ---")

	doSomethingThatPanic()

	fmt.Println("This won't execute")

	// ── Recover from panic ─────────────────────

	fmt.Println("\n--- Recover ---")

	func() {
		defer func() {
			if r := recover(); r != nil {
				fmt.Println("Recovered from panic:", r)
			}
		}()

		panic("Something went wrong!")
	}()

	// ── Nil checks ─────────────────────────────────────

	fmt.Println("\n--- Nil checks ---")

	var nilSlice []int

	if nilSlice == nil {
		fmt.Println("Slice is nil")
	}

	// ── Multiple return values (idiomatic) ───────

	fmt.Println("\n--- Multiple return values ---")

	data, err := readFile("config.txt")

	// Idiomatic Go error handling
	if err != nil {
		// Handle error
		fmt.Println("Config error:", err)
	}

	if len(data) > 0 {
		fmt.Println("Config loaded:", string(data))
	}

	// ── Summary ─────────────────────────────────────

	fmt.Println("\n--- Summary ---")
	fmt.Println("Go error handling:")
	fmt.Println("  - Multi-return: result, err := function()")
	fmt.Println("  - error type: errors.New() or custom types")
	fmt.Println("  - errors.Is(): compare wrapped errors")
	fmt.Println("  - errors.As(): unwrap wrapped errors")
	fmt.Println("  - nil: explicit checking for missing values")
	fmt.Println("  - defer: always executes, cleanup code")
	fmt.Println("  - panic: unrecoverable with panic()")
	fmt.Println("  - recover(): catch panic in deferred")
	fmt.Println("  - No exceptions: explicit error values instead")
}
