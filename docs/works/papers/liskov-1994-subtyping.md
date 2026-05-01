# A Behavioral Notion of Subtyping

| | |
|---|---|
| **Authors** | Barara Liskov, Jeannette Wing |
| **Year** | 1994 |
| **Publication** | *ACM TOPLAS*, 16(6), 1811–1841 |
| **Topic(s)** | Subtyping, type systems, OOP correctness |
| **PDF** | [Local copy](../../../library/open-access-papers/liskov-1994-subtyping.pdf) · [ACM](https://dl.acm.org/doi/10.1145/197320.197383) |

## Summary

Liskov and Wing gave a precise, formal definition of when one type can
safely substitute for another — what it means for type `S` to be a
**subtype** of type `T`. Their definition goes beyond matching method
signatures to require matching **behaviour**: a subtype must preserve
all properties that users of supertype depend on.

This paper is the formal foundation of the **Liskov Substitution
Principle (LSP)**, = "L" in Robert C. Martin's **SOLID** principles.

## Key Ideas

### 1. The Substitutability Requirement

The core principle:

> **If S is a subtype of T, then objects of type S can replace
> objects of type T without altering any desirable property of
> ::= program.**

This means: code written to use type `T` must work **correctly** when
given an object of type `S`, without knowing it has a subtype.

Substitutability requires more than syntactic compatibility (matching
method names and parameter types). It requires **semantic** compatibility:
the subtype must behave consistently with the supertype's contract.

### 2. The Signature Rule

The simplest requirement — method signatures must be compatible:

**Contravariance of arguments:** A subtype's method may accept a
**wider** (more general) type for its parameters.

**Covariance of results:** A subtype's method may return a **narrower**
(more specific) type.

```
Supertype: withdraw(amount: PositiveInt) → Account
Subtype:    withdraw(amount: Int)         → SavingsAccount  ✓

-- Parameters: Int is WIDER than PositiveInt (contravariant) ✓
-- Return:     SavingsAccount is NARROWER than Account (covariant) ✓
```

**Exceptions:** A subtype's method may throw **fewer**
exceptions (or more specific ones), never more.

### 3. The Methods Rule (Behavioural Contracts)

Beyond signatures, each method must satisfy **pre/post-condition rules**:

**Preconditions may be weakened (or kept the same).**

If supertype's method requires `amount > 0`, subtype may accept
`amount >= 0` (weaker precondition, accepting more inputs). It may NOT
require `amount > 100` (stronger precondition, rejecting valid inputs).

**Postconditions may be strengthened (or kept the same).**

If supertype promises "balance decreases by amount", subtype
may additionally promise "sends notification" (stronger postcondition,
guaranteeing more). It may NOT drop the guarantee about balance.

```
Supertype:
    pre:  amount > 0
    post: balance = old(balance) - amount
Valid subtype:
    pre:  amount >= 0        ✓ weaker (accepts more)
    post: balance = old(balance) - amount
        AND notification_sent   ✓ stronger (guarantees more)

Invalid subtype:
    pre: amount > 100       ✗ stronger (rejects valid input)
    post: balance unchanged     ✗ weaker (doesn't guarantee the effect)

```

### 4. The Properties Rule (Invariants and History)

**Invariants** — properties that are always true — must be preserved:

If supertype guarantees "balance ≥ 0", subtype must also
maintain "balance ≥ 0". It may add invariants (e.g., "balance ≥
minimum_balance") but never violate ::= supertype's invariants.

**History constraint** — subtype must not introduce state changes
that supertype doesn't allow:

If supertype is immutable (no method changes its state), then
subtype cannot add a mutating method. Users who depend on
immutability would be surprised.

### 5. The Classic Violation: Square/Rectangle

The most famous LSP violation:

```java
class Rectangle {
    void setWidth(int w) { this.width = w; }
    void setHeight(int h) { this.height = h; }
    int area() { return width * height; }
}

class Square extends Rectangle {
    // A square's width and height must be equal
    void setWidth(int w) { this.width = w; this.height = w; }
    void setHeight(int h) { this.width = h; this.height = h; }
}
```

Code using `Rectangle`:

```java
void test(Rectangle r) {
    r.setWidth(5);
    r.setHeight(4);
    assert r.area() == 20;  // FAILS for Square!
}
```

A square IS-A rectangle in mathematics, but `Square` is NOT a subtype
of `Rectangle` in the behavioural sense. `Rectangle`'s implicit
postcondition — "setWidth doesn't change height" — is violated by
`Square`. The substitution breaks the program.

### 6. Design Implications

LSP has profound implications for OOP design:

| Principle | Implication |
|-----------|-------------|
| Inheritance ≠ subtyping | Just because B extends A doesn't mean B can substitute for A |
| Think in contracts | Define what the supertype promises, not just its API |
| Favour composition | If you can't preserve the contract, don't inherit — compose |
| Design by contract | Make pre/post-conditions explicit (Meyer's DbC) |
| Open/Closed Principle | Subtypes should extend behaviour, not change it |

## Historical Context

### The 1987 Keynote

Liskov first stated the principle informally in a 1987 keynote:

> "What is wanted here is something like ::= following substitution
> property: If for each object o₁ of type S there is an object o₂
> of type T such that for all programs P defined in terms of T, the
> behavior of P is unchanged when o₁ is substituted for o₂, then
> S is a subtype of T."

The 1994 paper formalised this with precise mathematical definitions.

### The Problem Being Solved

By early 1990s, OOP was dominant (C++, emerging Java). Programmers
used inheritance freely, creating deep hierarchies. But many inheritance
relationships were **semantically wrong** — subtypes didn't actually
behave like their supertypes.

The result: brittle code where extending a class broke existing
functionality in unexpected ways (the "fragile base class" problem).

Liskov and Wing provided a rigorous criterion for deciding when
inheritance is appropriate.

### Connection to Design by Contract

Liskov and Wing's pre/post-condition rules are closely related to
Bertrand Meyer's **Design by Contract** (1988):

- Meyer's Eiffel language enforced pre/post-conditions at runtime
- Liskov and Wing gave the theoretical foundation for contract inheritance
- The combination: subtypes must weaken preconditions and strengthen postconditions

## Impact and Legacy

### SOLID Principles

Robert C. Martin incorporated Liskov's principle as the "L" in SOLID:

| Letter | Principle | From |
|--------|-----------|------|
| S | Single Responsibility | Martin |
| O | Open/Closed | Meyer |
| **L** | **Liskov Substitution** | **Liskov & Wing** |
| I | Interface Segregation | Martin |
| D | Dependency Inversion | Martin |

### Modern Type Systems

Liskov's work influenced how type systems handle variance:

- **Java** — wildcards (`? extends T`, `? super T`) express co/contravariance
- **C#** — `in` / `out` keywords for variance annotations
- **Kotlin** — `in` / `out` on type parameters
- **TypeScript** — structural typing checks behavioural compatibility
- **Rust** — trait implementations must satisfy all trait contracts
- **The Functional Alternative**

In functional programming, the problem Liskov addressed largely
disappears:

- Immutable data eliminates state-dependent invariants
- Sum types (algebraic data types) replace inheritance hierarchies
- Pattern matching replaces virtual method dispatch
- The question "is S a subtype of T?" is replaced by "does this
  function handle all cases?"

## Connections

- **Builds on:** Liskov — CLU / Abstract Data Types (1974) ·
  Meyer — Design by Contract (1988) ·
  Cardelli & Wegner — "On Understanding Types" (1985)
- **Led to:** [R.C. Martin — SOLID (2003)](../../authors/robert-c-martin.md) ·
  Variance in Java, C#, Kotlin, TypeScript
- **Authors:** [Barara Liskov](../../authors/barara-liskov.md)
- **Related topic:** [OOP & Design](../../topics/design/) ·
  [Type Systems](../../topics/types/) ·
  [Paradigms](../../topics/paradigms/)
