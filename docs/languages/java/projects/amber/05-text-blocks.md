# Text Blocks

> **Project:** Amber
> **Java version:** 15 (final)
> **Status:** Released

Text blocks are multi-line string literals that eliminate the need for most escape sequences and provide a clean way to embed JSON, HTML, SQL, and other formatted text in Java source code.

---

## History and Evolution

### The Multi-Line String Problem

Before text blocks, embedding multi-line text in Java was painful:

```java
// Pre-Java 15: unreadable, error-prone
String json = "{\n" +
              "  \"name\": \"Alice\",\n" +
              "  \"age\": 30\n" +
              "}";
```

Problems:
- `"` and `\n` clutter the actual content
- `"` must be escaped as `\"`
- Concatenation operators at every line break
- Easy to miss a quote or comma

### The Text Block Solution

```java
// Java 15+: clean, readable
String json = """
    {
      "name": "Alice",
      "age": 30
    }
    """;
```

The `"""` delimiter starts and ends the text block. Content between the delimiters is preserved as-is, including newlines.

### Preview Timeline

| Milestone | Release | Status |
|---|---|---|
| First preview | Java 13 | Basic text blocks |
| Second preview | Java 14 | `\s` and `\<line-terminator>` escapes |
| Finalized | Java 15 | Permanent language feature |

## Implementation Deep Dive

### Incidental vs Essential Whitespace

Text blocks distinguish two kinds of whitespace:

**Essential whitespace**: Part of the string content.
**Incidental whitespace**: Leading indentation used to align with Java code.

```java
String html = """
    <html>           ← 4 spaces: INCIDENTAL (removed)
        <body>       ← 8 spaces: 4 incidental + 4 essential
            <h1>Hello</h1>
        </body>
    </html>
    """;
```

The compiler removes the common leading whitespace (4 spaces in this case), leaving the relative indentation intact.

### Escape Sequences

Text blocks support standard escape sequences plus two new ones:

| Escape | Meaning |
|---|---|
| `\n` | Line feed |
| `\t` | Tab |
| `\"` | Double quote |
| `\\` | Backslash |
| `\s` | Single space (prevents trailing space stripping) |
| `\<LF>` | Line continuation (joins lines without newline) |

The line continuation escape is useful for very long lines:

```java
String query = """
    SELECT id, name, email \
    FROM users \
    WHERE active = true \
    ORDER BY name
    """;
// Result: single line with spaces where \ was
```

### Formatting Methods

Text blocks work well with `String.formatted()` (Java 15+):

```java
String template = """
    Hello, %s!
    You have %d unread messages.
    """;
String message = template.formatted("Alice", 5);
```

Or with `String.format()`:

```java
String message = String.format(template, "Alice", 5);
```

## Comparison with String Templates (Preview)

Text blocks (finalized, Java 15) are static multi-line strings. String Templates (preview, Java 25) add embedded expressions:

```java
// Text block: static, no interpolation
String msg = """
    Hello, {name}!
    """; // literally contains "{name}"

// String template (preview): embedded expressions
String msg = STR."""
    Hello, \{name}!
    You have \{count} messages.
    """;
```

See [String Templates](07-string-templates.md) for the preview feature.

## See Also

- [String API and Text Processing — main feature page](../../20-string-api.md)
- [String Templates](07-string-templates.md) — the next evolution (preview)
- [var](06-var.md) — another Amber feature for cleaner code
