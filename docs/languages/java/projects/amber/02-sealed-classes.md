# Sealed Classes

> **Project:** Amber
> **Java version:** 17 (final)
> **Status:** Released

Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them, giving developers precise control over the type hierarchy within a module or package.

For the complete API reference and usage patterns, see the main feature page: [Sealed Classes](../../06-sealed-classes.md).

---

## History and Evolution

### The Open Hierarchy Problem

Traditional Java inheritance is open: any class can extend any non-final class. This creates several issues:

1. **Unknown subclasses** — A library author cannot know all subclasses of their class
2. **Breaking changes** — Adding a method to a base class may break unknown subclasses
3. **Incomplete switch** — `switch` statements need `default` because new subclasses can appear
4. **Security** — Sensitive base classes can be extended maliciously

### Sealed Classes in Other Languages

Sealed/final classes exist in many languages, but Amber's design draws from:

- **Scala**: `sealed trait` — pattern matching with exhaustiveness checking
- **Kotlin**: `sealed class` — closed hierarchies with when-expression
- **C#**: `sealed` — prevents inheritance, but no explicit permits list

Java's design adds the `permits` clause for explicit control.

### Preview Timeline

| Milestone | Release | Status |
|---|---|---|
| First preview | Java 15 | Basic sealed classes |
| Second preview | Java 16 | Refinements, interaction with records |
| Finalized | Java 17 | Permanent language feature |

## Implementation Deep Dive

### The `sealed` and `permits` Model

```java
public sealed interface Expr
    permits AddExpr, MulExpr, ConstExpr, NegExpr {}

public record ConstExpr(int value) implements Expr {}
public record AddExpr(Expr left, Expr right) implements Expr {}
public record MulExpr(Expr left, Expr right) implements Expr {}
public record NegExpr(Expr operand) implements Expr {}
```

The `permits` clause is the key innovation: it explicitly lists every permitted subtype. The compiler enforces that:

1. Only listed classes can implement/extend
2. Every permitted class must be in the same module (or package, if no module)
3. Every permitted class must declare itself `final`, `sealed`, or `non-sealed`

### Permitted Subclass Modifiers

| Modifier | Meaning | Use case |
|---|---|---|
| `final` | No further inheritance | Leaf nodes in hierarchy |
| `sealed` | Further restricted inheritance | Intermediate nodes |
| `non-sealed` | Open for further extension | Legacy compatibility |

```java
public sealed interface Shape permits Circle, Rectangle, Polygon {}

public final class Circle implements Shape {}           // leaf
public sealed class Rectangle implements Shape           // intermediate
    permits Square {}
public non-sealed class Polygon implements Shape {}      // open for extension
```

### Exhaustive Pattern Matching

The primary benefit of sealed classes is compiler-verified exhaustiveness:

```java
public sealed interface Expr permits Const, Add, Mul {}
public record Const(int value) implements Expr {}
public record Add(Expr left, Expr right) implements Expr {}
public record Mul(Expr left, Expr right) implements Expr {}

// Compiler verifies ALL cases are covered — no default needed!
int eval(Expr e) {
    return switch (e) {
        case Const(int v) -> v;
        case Add(var l, var r) -> eval(l) + eval(r);
        case Mul(var l, var r) -> eval(l) * eval(r);
    };
}
```

If a new `Expr` subtype is added later, the compiler flags every incomplete `switch` as an error. This makes sealed classes + records + pattern matching a powerful trio for domain modeling.

### Reflection Support

```java
Class<?> clazz = Expr.class;

if (clazz.isSealed()) {
    Class<?>[] permitted = clazz.getPermittedSubclasses();
    // [Const.class, Add.class, Mul.class]
}
```

Sealed class metadata is available at runtime via reflection, enabling frameworks to reason about closed hierarchies.

## Use Cases

### Domain Modeling

```java
public sealed interface PaymentResult
    permits PaymentSuccess, PaymentFailure, PaymentPending {}

public record PaymentSuccess(String transactionId) implements PaymentResult {}
public record PaymentFailure(String errorCode, String message) implements PaymentResult {}
public record PaymentPending(String trackingId) implements PaymentResult {}
```

### AST Representations

Compiler and interpreter internals naturally map to sealed hierarchies:

```java
public sealed interface ASTNode
    permits BinaryOp, UnaryOp, Literal, VariableRef {}
```

### State Machines

```java
public sealed interface ConnectionState
    permits Disconnected, Connecting, Connected, Failed {}
```

## See Also

- [Sealed Classes — main feature page](../../06-sealed-classes.md)
- [Records](01-records.md) — combine for algebraic data types
- [Pattern Matching for instanceof](03-pattern-matching-instanceof.md)
- [Pattern Matching for switch](04-pattern-matching-switch.md)
