# String API and Text Processing

Java provides a rich set of APIs for creating, manipulating, and processing text.
Understanding the differences between `String`, `StringBuilder`, and `StringBuffer`,
along with formatting and parsing utilities, is essential for writing efficient Java code.

---

## String — Immutable Character Sequence

`String` is **immutable**: every "modifying" operation creates a new `String` object.
This enables safe sharing, caching, and natural thread safety.

### String literals and the String Pool

```java
// Literal — interned in the String Pool
String a = "hello";
String b = "hello";
System.out.println(a == b);     // true — same pooled reference

// new String() — explicit heap allocation, bypasses the pool
String c = new String("hello");
System.out.println(a == c);     // false — different objects
System.out.println(a.equals(c));// true — same content

// Manual interning
String d = c.intern();
System.out.println(a == d);     // true — now in the pool
```

> The String Pool lives in the **heap** (since Java 7). Previously it resided in PermGen.

### Common operations

```java
String s = "  Hello, World!  ";

// Inspection
s.length();                     // 17
s.isEmpty();                    // false
s.isBlank();                    // false (Java 11+)
s.charAt(0);                    // ' '
s.indexOf("World");             // 9
s.contains("Hello");            // true
s.startsWith("  H");            // true
s.endsWith("!  ");              // true

// Comparison
"abc".equals("abc");            // true
"abc".equalsIgnoreCase("ABC");  // true
"abc".compareTo("def");         // negative

// Transformations (all return NEW strings)
s.toLowerCase();                // "  hello, world!  "
s.toUpperCase();                // "  HELLO, WORLD!  "
s.trim();                       // "Hello, World!" (Java 11+: strip() handles Unicode)
s.strip();                      // "Hello, World!" — Unicode-aware trim
s.stripLeading();               // "Hello, World!  "
s.stripTrailing();              // "  Hello, World!"
s.replace("World", "Java");     // "  Hello, Java!  "
s.replaceAll("\\s+", "-");      // "-Hello,-World!-"
s.substring(2, 7);              // "Hello"

// Splitting and joining
String[] parts = "a,b,c".split(",");           // ["a", "b", "c"]
String joined = String.join("-", parts);       // "a-b-c"

// Java 11+ convenience methods
"hello".repeat(3);              // "hellohellohello"
"  ".isBlank();                 // true
"\n\t ".isBlank();              // true
"hello".lines();                // Stream<String> — split by line terminators
"name=John".stripIndent();      // remove incidental whitespace (text blocks)
"hello".transform(String::toUpperCase);  // "HELLO"
```

### String templates (Preview, Java 21+)

```java
// STR template processor (standard)
String name = "World";
String message = STR."Hello, \{name}!";   // "Hello, World!"

// FMT — formatted interpolation
String formatted = FMT."Value: %10.2f\{Math.PI}";  // padded, 2 decimals
```

---

## StringBuilder and StringBuffer — Mutable Strings

When building strings in a loop or through multiple concatenations,
use a mutable builder to avoid creating numerous intermediate `String` objects.

| Class | Thread-safe | Use case | Performance |
|---|---|---|---|
| **StringBuilder** | No | Single-threaded string building | Fastest |
| **StringBuffer** | Yes (synchronized) | Shared across threads | Slower due to locking |

> **Always prefer `StringBuilder`** unless you explicitly need thread safety.
> Modern code rarely shares mutable string builders across threads.

```java
// Inefficient: creates O(n²) intermediate strings
String result = "";
for (String word : words) {
    result += word + " ";   // new String each iteration
}

// Efficient: single StringBuilder
StringBuilder sb = new StringBuilder();
for (String word : words) {
    sb.append(word).append(' ');
}
String result = sb.toString();

// With initial capacity (avoids resizing)
StringBuilder sb2 = new StringBuilder(1000);
sb2.append("Hello")
   .append(" ")
   .append("World")
   .insert(5, ",")           // "Hello, World"
   .delete(5, 6)              // "Hello World"
   .replace(6, 11, "Java")    // "Hello Java"
   .reverse();                // "avaJ olleH"

// Capacity management
StringBuilder sb3 = new StringBuilder();  // default capacity: 16
sb3.capacity();               // 16
sb3.ensureCapacity(100);      // pre-allocate
sb3.trimToSize();             // release unused capacity
```

### String concatenation: compiler optimizations

```java
// Simple concatenation — compiler uses StringBuilder automatically
String greeting = "Hello, " + name + "!";   // optimized by compiler

// Loop concatenation — NOT optimized, explicit builder required
String result = "";
for (String s : list) {
    result += s;   // Creates new StringBuilder each iteration!
}
```

---

## Text Blocks (Java 15+)

Text blocks provide a clean way to write multi-line string literals.

```java
String json = """
    {
        "name": "John",
        "age": 30,
        "city": "New York"
    }
    """;

// Trailing backslash removes the line terminator
String singleLine = """
    SELECT id, name, email \
    FROM users \
    WHERE active = true
    """;
// Result: "SELECT id, name, email FROM users WHERE active = true"

// Escapes still work
String withQuotes = """
    He said, "Hello!"
    """;
```

> Text blocks use `
` (LF) line terminators regardless of the platform.
> Use `String.translateEscapes()` or `replace("\n", System.lineSeparator())`
> if platform-specific line endings are required.

---

## String Formatting

### String.format

```java
String formatted = String.format("Name: %s, Age: %d, Score: %.2f",
    "Alice", 30, 95.567);
// "Name: Alice, Age: 30, Score: 95.57"

// Locale-aware formatting
String german = String.format(Locale.GERMAN, "%.2f", 1234.56);
// "1234,56"

// Common format specifiers
// %s   — string
// %d   — integer (decimal)
// %f   — floating-point
// %e   — scientific notation
// %x   — hexadecimal
// %o   — octal
// %c   — character
// %b   — boolean
// %n   — platform line separator
// %%   — literal percent

// Width and alignment
String.format("|%10s|", "hi");     // "|        hi|" — right-aligned
String.format("|%-10s|", "hi");    // "|hi        |" — left-aligned
String.format("|%010d|", 42);      // "|0000000042|" — zero-padded
String.format("|%,d|", 1000000);   // "|1,000,000|" — grouping
```

### Formatter and PrintStream shortcuts

```java
// System.out.printf — convenience for String.format + print
System.out.printf("Value: %.2f%n", 3.14159);

// String.formatted (Java 15+)
String result = "Value: %.2f".formatted(3.14159);

// Formatter for building complex output
Formatter formatter = new Formatter();
formatter.format("User: %s%n", username);
formatter.format("Score: %d%n", score);
String output = formatter.toString();
formatter.close();
```

### MessageFormat — localized parameterized messages

```java
// Positional arguments for i18n
String pattern = "At {0,time,short} on {0,date,long}, {1} logged in.";
String result = MessageFormat.format(pattern,
    new Date(), "Alice");
// "At 10:30 AM on January 15, 2024, Alice logged in."

// Choice format for pluralization
String choice = "There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.";
MessageFormat.format(choice, 0);   // "There are no files."
MessageFormat.format(choice, 1);   // "There is one file."
MessageFormat.format(choice, 5);   // "There are 5 files."
```

---

## StringJoiner and String.join

```java
// String.join — simple delimiter joining
String csv = String.join(", ", "apple", "banana", "cherry");
// "apple, banana, cherry"

// StringJoiner — prefix/suffix support
StringJoiner joiner = new StringJoiner(", ", "[", "]");
joiner.add("a");
joiner.add("b");
joiner.add("c");
String result = joiner.toString();   // "[a, b, c]"

// Collectors.joining — Stream integration
String result2 = list.stream()
    .map(String::toUpperCase)
    .collect(Collectors.joining(", ", "{", "}"));
```

---

## Scanner — Text Parsing

```java
// Parse from standard input
Scanner scanner = new Scanner(System.in);
System.out.print("Enter age: ");
int age = scanner.nextInt();

// Parse from string
Scanner s = new Scanner("10 20.5 hello true");
int i = s.nextInt();        // 10
double d = s.nextDouble();  // 20.5
String str = s.next();      // "hello"
boolean b = s.nextBoolean();// true

// Delimiter customization
Scanner csv = new Scanner("a,b,c").useDelimiter(",");
while (csv.hasNext()) {
    System.out.println(csv.next());
}

// Locale-aware number parsing
Scanner german = new Scanner("1.234,56").useLocale(Locale.GERMAN);
double value = german.nextDouble();   // 1234.56

// Always close when done
scanner.close();
```

> For production parsing of structured data (JSON, CSV, XML), prefer
> dedicated libraries (Jackson, OpenCSV, JAXB) over `Scanner`.

---

## Performance Comparison

```mermaid
flowchart TD
    subgraph SingleConcat["Single concatenation"]
        S1["String +"] -->|Compiler optimizes| OK1["✅ Fine"]
    end

    subgraph LoopConcat["Loop concatenation"]
        S2["String +"] -->|O(n²) objects| BAD["❌ Slow, memory-heavy"]
        SB["StringBuilder"] -->|O(n) amortized| GOOD["✅ Fast"]
    end

    subgraph ThreadShared["Shared mutable state"]
        SB2["StringBuilder"] -->|Race conditions| BAD2["❌ Unsafe"]
        SBF["StringBuffer"] -->|Synchronized| OK2["✅ Thread-safe"]
    end

    style BAD fill:#ffebee,stroke:#c62828
    style BAD2 fill:#ffebee,stroke:#c62828
    style GOOD fill:#e8f5e9,stroke:#388e3c
    style OK1 fill:#e8f5e9,stroke:#388e3c
    style OK2 fill:#e8f5e9,stroke:#388e3c
```

| Operation | String (+) | StringBuilder | StringBuffer |
|---|---|---|---|
| Single concatenation | Fast (compiler optimized) | Slight overhead | Slight overhead + sync |
| Loop concatenation | O(n²) — avoid | O(n) — preferred | O(n) — if thread-safe needed |
| Random access (charAt) | O(1) | O(1) | O(1) |
| Memory per append | New object each time | Grows buffer (2x when full) | Grows buffer (2x when full) |

---

## Decision Guide

| Need | Use | Avoid |
|---|---|---|
| Constant text, sharing, keys | `String` | `StringBuilder` |
| Building text in a loop | `StringBuilder` | `String` concatenation |
| Shared mutable text builder | `StringBuffer` | Unsynchronized `StringBuilder` |
| Multi-line literals (Java 15+) | Text blocks `"""` | Escaped `\n` in regular strings |
| Simple formatting | `String.format` / `.formatted()` | Manual concatenation |
| Localized messages | `MessageFormat` | Hardcoded strings |
| Complex joining with prefix/suffix | `StringJoiner` | Manual appending |
| Quick parse of simple tokens | `Scanner` | Regex for trivial cases |
| Parse structured data (JSON/CSV) | Jackson, OpenCSV | `Scanner` |
