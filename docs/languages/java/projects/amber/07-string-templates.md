# String Templates

> **Project:** Amber
> **Java version:** 25 (preview)
> **Status:** Preview

String templates are a preview feature that embed expressions directly within string literals, providing a type-safe alternative to string concatenation and `String.format()`.

---

## History and Evolution

### The String Formatting Landscape

Java has several ways to build formatted strings:

```java
// Concatenation: verbose, hard to read
String msg = "Hello, " + name + "! You have " + count + " messages.";

// String.format: separates format from data
String msg = String.format("Hello, %s! You have %d messages.", name, count);

// MessageFormat: for localization, but verbose
String msg = MessageFormat.format("Hello, {0}! You have {1} messages.", name, count);

// StringBuilder: explicit and efficient, but boilerplate-heavy
StringBuilder sb = new StringBuilder();
sb.append("Hello, ").append(name).append("! You have ")
  .append(count).append(" messages.");
```

Each approach has trade-offs between readability, type safety, and performance.

### The String Template Approach

String templates embed expressions directly in the string:

```java
String msg = STR."Hello, \{name}! You have \{count} messages.";
```

The `\{...}` syntax is an **embedded expression**. At compile time, the compiler verifies that the expression is valid. At runtime, the value is converted to a string and inserted.

### Preview Timeline

| Milestone | Release | Status |
|---|---|---|
| First preview | Java 21 | Basic template processors |
| Second preview | Java 22 | API refinements |
| Third preview | Java 23 | Further refinements |
| Fourth preview | Java 24 | Processor API changes |
| Fifth preview | Java 25 | Refined processor model |

The feature has gone through multiple preview rounds because the template processor API proved challenging to design correctly.

## Implementation Deep Dive

### Template Processors

String templates are not hard-coded string interpolation. They are processed by a **template processor** — a function that receives the string fragments and embedded values:

```java
// STR: the standard string processor
String msg = STR."Hello, \{name}!";

// FMT: formatted string (like String.format)
String msg = FMT."Value: %10.2f\{value}";

// RAW: returns a StringTemplate without processing
StringTemplate st = RAW."Hello, \{name}!";
```

### Custom Processors

Developers can define custom processors for validation, SQL injection prevention, HTML escaping, etc.:

```java
// A processor that validates SQL parameters
StringTemplate.Processor<String, SQLException> SQL = (stringTemplate) -> {
    // Validate: embedded expressions must not contain SQL injection
    // Then compile and return a safe SQL string
    return safeCompile(stringTemplate);
};

String query = SQL."SELECT * FROM users WHERE name = \{userName}";
```

This is the key safety feature: the processor sees the template structure (separating text from values) and can apply domain-specific validation.

### Multi-Line Templates

String templates work with text blocks:

```java
String json = STR."""
    {
      "name": "\{user.name()}",
      "age": \{user.age()},
      "email": "\{user.email()}"
    }
    """;
```

### Comparison with Other Languages

| Language | Syntax | Notes |
|---|---|---|
| Java (preview) | `STR."Hello \{name}"` | Processor-based, type-safe |
| Kotlin | `"Hello $name"` | Simple interpolation |
| Python | `f"Hello {name}"` | f-strings, runtime evaluation |
| JavaScript | `` `Hello ${name}` `` | Template literals |
| C# | `$"Hello {name}"` | Interpolated strings |

Java's approach is more verbose but provides stronger guarantees through the processor model.

## When to Use

| Scenario | Recommendation |
|---|---|
| Simple concatenation | `var` or `+` — templates may be overkill |
| Complex formatting | `FMT` processor or `String.format()` |
| SQL queries | Custom processor for injection prevention |
| HTML generation | Custom processor for XSS prevention |
| JSON/XML | Text block + `STR` processor |
| Logging | Prefer structured logging over string formatting |

## Future Direction

String Templates remain in preview as the Amber team refines:
- The `StringTemplate` API
- Processor composition patterns
- Performance optimizations
- Interaction with future language features

The feature is expected to finalize after the processor API stabilizes.

## See Also

- [String API and Text Processing — main feature page](../../20-string-api.md)
- [Text Blocks](05-text-blocks.md) — multi-line string literals (finalized)
- [var](06-var.md) — another conciseness feature
