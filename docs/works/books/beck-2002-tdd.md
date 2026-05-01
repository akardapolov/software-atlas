# Test-Driven Development: By Example

| | |
|---|---|
| **Author** | Kent Beck |
| **Year** | 2002 |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | TDD, testing, incremental design |
| **ISBN** | 978-0-321-14653-3 |

## Summary

Beck demonstrated TDD through two extended examples, showing that
writing tests **before** code is not primarily about testing — it's a
**design technique** that produces clean, modular, well-structured code
through tiny incremental steps.

The book's subtitle — "By Example" — is the point: Beck doesn't
theorise about TDD, he *shows* it, step by step, in real code.

## Key Ideas

### 1. The TDD Cycle

The core discipline:

```
1. RED    — Write a failing test
2. GREEN  — Write the simplest code to make it pass
3. REFACTOR — Improve the design without changing behaviour
   ↺ Repeat
```

Each cycle takes **minutes**, not hours. The test suite grows
continuously, providing a safety net for refactoring.

### 2. The Two Rules

Beck's TDD has just two rules:

1. **Write new code only when an automated test has failed**
2. **Eliminate duplication**

The first rule drives to the Red-Green cycle. The second rule drives
refactoring. Together, they produce code that is both correct
(all tests pass) and clean (no duplication).

### 3. Fake It Till You Make It

Beck showed that it's often productive to start with an obviously
wrong implementation — a **fake**:

```python
# Test
def test_sum():
    assert sum([1, 2, 3]) == 6

# Fake it (RED → GREEN as fast as possible)
def sum(numbers):
    return 6  # obviously wrong, but test passes!

# Now triangulate — add another test that forces real implementation
def test_sum_different():
    assert sum([2, 3]) == 5

# Real implementation
def sum(numbers):
    result = 0
    for n in numbers:
        result += n
    return result
```

This technique — write the simplest thing that passes, then add tests
that force generalisation — seems absurd but has a serious purpose:
it keeps steps small and keeps developer focused on behaviour,
not premature abstractions.

### 4. The Money Example

The first extended example implements a multi-currency money system.
Beck builds it entirely through TDD, starting from:

```
$5 + 10 CHF = $10 if rate is 2:1
```

Through dozens of tiny Red-Green-Refactor cycles, the system evolves
from a simple Dollar class to a full multi-currency algebra with
expressions, reductions, and exchange rates.

The example demonstrates how TDD **drives design emergence**:

- The Money class becomes a Value Object naturally
- The Expression pattern emerges from testing needs
- Duplication elimination reveals the right abstractions
- No upfront design was needed — the design emerged from the tests

### 5. xUnit Framework

The second example builds a **testing framework** using TDD — a
beautifully self-referential exercise. Beck implements a miniature
xUnit framework, testing the testing framework with itself.

This demonstrates that TDD applies even to infrastructure code and
that testing frameworks are simple enough to build from scratch.

### 6. TDD Patterns

Beck catalogued practical patterns for TDD:

| Pattern | Description |
|---------|-------------|
| **Test List** | Start each session by writing a list of tests to implement |
| **Assert First** | Write the assertion first, then work backwards |
| **Triangulation** | Add tests that force generalisation of a fake |
| **One to Many** | Start with a single item, then generalise to collections |
| **Starter Test** | Begin with the simplest possible test case |
| **Explanation Test** | Use tests to explain and document behaviour |
| **Learning Test** | Write tests to understand third-party code |

## Historical Context

### Origins

TDD's roots trace back to:

- **1960s mainframe programming** — developers would write expected
  outputs before running programs
- **Cleanroom software engineering** (1980s) — formal verification
  before execution
- **Kent Beck's Smalltalk practice** — Beck and Ward Cunningham used
  test-first programming in Smalltalk in late 1980s
- **SUnit** (1998) — Beck's Smalltalk testing framework, which
  inspired JUnit (Beck & Gamma, 1997)

### The XP Connection

TDD is one of the core practices of **Extreme Programming** (XP),
Beck's agile methodology (1999). XP combined TDD with pair programming,
continuous integration, and simple design. TDD was the practice that
caught on most widely beyond XP.

## Impact and Legacy

### Mainstream Adoption

TDD became one of the most widely adopted agile practices:

- **JUnit** (Beck & Gamma) became the standard testing framework
  for Java and inspired xUnit frameworks in every language
- **BDD** (Behaviour-Driven Development, Dan North, 2006) extended
  TDD with domain-language specifications
- **Property-Based Testing** (QuickCheck, Hughes & Claessen, 2000)
  complemented TDD with randomised testing

### The Design Benefit

The book's most important claim is that TDD is a **design** technique,
not just a testing technique:

- Tests written first express the desired API before implementation
- The need to test drives decoupling (dependencies must be injectable)
- Small steps prevent over-engineering
- Refactoring keeps the design clean

### Criticism

**Overhead:** Writing tests first can feel slow, especially for
exploratory or throwaway code.

**Test-induced design damage:** Some argue that designing for
testability can distort architecture (e.g., adding interfaces solely
for mocking). David Heinemeier Hansson's "TDD is dead" (2014)
sparked debate; Beck responded that TDD should produce better design,
not worse.

**Not sufficient:** TDD alone doesn't guarantee good architecture.
It works best combined with domain knowledge and design principles.

## Connections

- **Builds on:** Beck — *Extreme Programming Explained* (1999) ·
  SUnit / JUnit · [Deming — Systems Thinking](../../authors/kent-beck.md)
- **Led to:** [Feathers — *Working with Legacy Code* (2004)](feathers-2004-legacy.md) ·
  BDD (North, 2006) ·
  [Continuous Delivery (Humble & Farley, 2010)](humble-2010-cd.md) ·
  [Forsgren — *Accelerate* (2018)](../../authors/nicole-forsgren.md)
- **Author:** [Kent Beck](../../authors/kent-beck.md)
- **Related topic:** [Process & Testing](../../topics/process/) ·
  [OOP & Design](../../topics/design/)
