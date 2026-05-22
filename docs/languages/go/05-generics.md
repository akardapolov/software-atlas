# Generics

| Section | Content |
| :--- | :--- |
| **Description** | Generics (type parameters) were added in Go 1.18, allowing functions and types to work with any type while maintaining compile-time type safety. |
| **API Purpose** | Writing reusable data structures and algorithms without sacrificing type safety or resorting to `interface{}`. |
| **Terminology** | Type parameter (`[T any]`), type constraint, type set, `any`, `comparable`, interface constraints, type inference. |
| **Notes** | Go's generics use type sets defined by interfaces. The `any` constraint accepts any type. `comparable` requires `==` and `!=` operators. Type inference often eliminates the need to specify type arguments explicitly. |

```mermaid
flowchart LR
    GEN["func Min[T any](a, b T) T"] -->|"T=int"| INT["Min[int](3, 5)"]
    GEN -->|"T=string"| STR["Min[string](\"a\", \"b\")"]
    GEN -->|"T=float64"| FLT["Min(1.5, 2.5) // inferred"]

    style GEN fill:#e1f5fe,stroke:#0288d1
    style INT fill:#e8f5e9,stroke:#388e3c
    style STR fill:#e8f5e9,stroke:#388e3c
    style FLT fill:#e8f5e9,stroke:#388e3c
```

## Generic Functions

```go
// Generic function with type parameter
func Min[T comparable](a, b T) T {
    if a < b {  // Note: < requires ordered constraint, not comparable
        return a
    }
    return b
}

// Using constraints package
func Min[T constraints.Ordered](a, b T) T {
    if a < b {
        return a
    }
    return b
}

func main() {
    fmt.Println(Min(3, 5))              // T inferred as int
    fmt.Println(Min("apple", "banana")) // T inferred as string
    fmt.Println(Min[float64](1.5, 2.5)) // explicit type argument
}
```

## Generic Types

```go
// Generic stack
type Stack[T any] struct {
    items []T
}

func (s *Stack[T]) Push(item T) {
    s.items = append(s.items, item)
}

func (s *Stack[T]) Pop() (T, error) {
    var zero T
    if len(s.items) == 0 {
        return zero, errors.New("stack is empty")
    }
    item := s.items[len(s.items)-1]
    s.items = s.items[:len(s.items)-1]
    return item, nil
}

// Usage
var intStack Stack[int]
intStack.Push(10)
intStack.Push(20)
```

## Type Constraints

| Constraint | Allows |
|------------|--------|
| `any` | Any type |
| `comparable` | Types supporting `==` and `!=` |
| `constraints.Ordered` | Types supporting `<`, `<=`, `>`, `>=` |
| `interface { ~int \| ~string }` | Underlying type is `int` or `string` |

```go
// Custom constraint
type Number interface {
    ~int | ~int64 | ~float64  // ~ allows underlying types
}

func Sum[T Number](values []T) T {
    var sum T
    for _, v := range values {
        sum += v
    }
    return sum
}
```

---

Examples: [FP Features](../../../examples/go/07-fp-features/README.md)
