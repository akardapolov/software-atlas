# `var`: Local Variable Type Inference

> **Project:** Amber
> **Java version:** 10 (final)
> **Status:** Released

The `var` keyword allows the compiler to infer the type of a local variable from its initializer, reducing verbosity without sacrificing Java's static type safety.

---

## History and Evolution

### The Verbosity Problem

Before `var`, Java's explicit type declarations often repeated obvious information:

```java
// The type is obvious from the right-hand side
Map<String, List<Customer>> customersByCity = new HashMap<>();
//    ^^^^^^^^^^^^^^^^^^^^^ obvious              ^^^^^^^^^ already known
```

This is particularly painful with:
- Generics with long type parameters
- Try-with-resources
- Enhanced for-loops over complex types

### The Design Constraint

Java is a **nominally typed** language: types are central to its design. The Amber team was careful not to introduce "JavaScript-style" dynamic typing:

- `var` is **not** a type — it's a signal for the compiler to infer the type
- The inferred type is **fixed** — you cannot reassign a `var` with a different type
- `var` only works where the **initializer's type is unambiguous**

### Timeline

| Milestone | Release | Feature |
|---|---|---|
| `var` for local variables | Java 10 | Basic type inference |
| `var` in lambda parameters | Java 11 | `@SuppressWarnings("var")` style annotations |
| `var` in for-loops | Java 10 | `for (var item : list)` |

`var` was the first Amber feature to ship — even before the project was officially named "Amber."

## Implementation Deep Dive

### How It Works

```java
var list = new ArrayList<String>();  // infers: ArrayList<String>
var map = new HashMap<>();           // infers: HashMap<Object, Object>
var stream = list.stream();          // infers: Stream<String>
```

The compiler replaces `var` with the inferred type at compile time. There is **no runtime difference** between `var` and explicit types.

### Where `var` Works

| Location | Supported? | Example |
|---|---|---|
| Local variables | ✅ Yes | `var x = 42;` |
| For-each loop | ✅ Yes | `for (var s : list)` |
| Try-with-resources | ✅ Yes | `try (var in = new FileInputStream(...))` |
| Lambda parameters | ✅ Yes (Java 11) | `(var x, var y) -> x + y` |
| Fields | ❌ No | `private var count = 0;` // ERROR |
| Method parameters | ❌ No | `void foo(var x)` // ERROR |
| Return types | ❌ No | `var foo() { ... }` // ERROR |
| Catch variables | ❌ No | `catch (var e)` // ERROR |

### Best Practices

**Good uses of `var`:**

```java
// The type is obvious from the initializer
var list = new ArrayList<String>();

// The type name is very long
var factory = new AbstractWidgetFactory.Builder().create();

// Loop variables
for (var entry : map.entrySet()) { ... }
```

**Questionable uses of `var`:**

```java
// Type is not clear from the initializer
var data = readData();  // What type is data? Need to check method signature.

// Primitive literals (type may surprise)
var x = 1;        // int, not long or byte
var y = 1.0;      // double, not float
var z = "hello";  // String, obviously
```

### The `var` + Diamond Operator Interaction

```java
var list = new ArrayList<>();        // infers ArrayList<Object>
var list = new ArrayList<String>();  // infers ArrayList<String>
```

When using `var` with the diamond operator `<>`, the compiler has no target type to infer from. It falls back to `Object` for generic parameters. Always provide explicit type arguments when using `var`.

## See Also

- [Lambdas and Functional Interfaces — main feature page](../../03-lambdas.md)
- [Generics — main feature page](../../01-generics.md)
- [Records](01-records.md) — another Amber feature for conciseness
