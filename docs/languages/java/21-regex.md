# Regular Expressions

Java's regular expression API resides in `java.util.regex` and provides
powerful pattern matching, search, replace, and split capabilities.

---

## Core Classes

| Class | Purpose |
|---|---|
| **`Pattern`** | Compiled representation of a regex |
| **`Matcher`** | Engine that matches a `Pattern` against an input `CharSequence` |
| **`PatternSyntaxException`** | Thrown when regex syntax is invalid |

```java
// Two-step process: compile, then match
Pattern pattern = Pattern.compile("[a-z]+");
Matcher matcher = pattern.matcher("hello123");

// Convenience methods on String (compile + match internally)
"abc".matches("[a-z]+");            // true
"a,b,c".split(",");                  // ["a", "b", "c"]
"hello123".replaceAll("\\d+", "!");  // "hello!"
```

> **Performance tip:** When using a regex repeatedly, pre-compile the `Pattern`
> and reuse the `Matcher`. `String.matches()` and `String.replaceAll()` compile
> the pattern on every call.

---

## Pattern Compilation and Flags

```java
// Compile with flags
Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);

// Common flags (can be combined with |)
Pattern.CASE_INSENSITIVE    // (?i) — ignore case
Pattern.MULTILINE           // (?m) — ^ and $ match line boundaries
Pattern.DOTALL              // (?s) — . matches newlines too
Pattern.UNIX_LINES          // (?d) — \n is the only line terminator
Pattern.COMMENTS            // (?x) — ignore whitespace and comments in pattern
Pattern.LITERAL             // treat pattern as literal string (no metacharacters)

// Inline flags in the pattern itself
Pattern.compile("(?i)[a-z]+");          // case-insensitive
Pattern.compile("(?im)^[a-z]+$");       // multiline + case-insensitive
Pattern.compile("(?i:hello)");          // flag applies to group only
```

---

## Matching Operations

```java
Pattern p = Pattern.compile("\\b\\w+\\b");
Matcher m = p.matcher("The quick brown fox");

// Boolean checks
m.matches();        // true ONLY if entire input matches
m.find();           // true if any match found (advances cursor)
m.lookingAt();      // true if match at beginning (like ^ without consuming)

// Iterating all matches
while (m.find()) {
    System.out.println(m.group());      // full match
    System.out.println(m.start());      // start index
    System.out.println(m.end());        // end index (exclusive)
}

// Reset and reuse matcher
m.reset("new input text");
while (m.find()) { ... }

// Find from specific position
m.region(5, 10);    // limit matching to substring [5, 10)
m.find();
```

---

## Groups and Capturing

```java
Pattern p = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
Matcher m = p.matcher("Date: 2024-03-15");

if (m.find()) {
    m.group(0);     // "2024-03-15" — entire match
    m.group(1);     // "2024" — first group
    m.group(2);     // "03" — second group
    m.group(3);     // "15" — third group
}

// Named groups (Java 7+)
Pattern p2 = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
Matcher m2 = p2.matcher("2024-03-15");
if (m2.find()) {
    m2.group("year");   // "2024"
    m2.group("month");  // "03"
    m2.group("day");    // "15"
}

// Non-capturing group — faster, doesn't store match
Pattern p3 = Pattern.compile("(?:\\d{4})-(\\d{2})-(\\d{2})");
// group(1) is now month, group(2) is day

// Group count
m.groupCount();     // number of capturing groups
```

---

## Replacement Operations

```java
String input = "The price is $100 and $200";

// Replace all matches
input.replaceAll("\\$\\d+", "$$XXX");     // "The price is $XXX and $XXX"

// Replace first match only
input.replaceFirst("\\$\\d+", "$$XXX");   // "The price is $XXX and $200"

// Matcher-based replacement (more control)
Matcher m = Pattern.compile("\\$(\\d+)").matcher(input);

// AppendReplacement / appendTail for incremental building
StringBuffer sb = new StringBuffer();
while (m.find()) {
    int amount = Integer.parseInt(m.group(1));
    m.appendReplacement(sb, "$" + (amount * 2));
}
m.appendTail(sb);
// sb: "The price is $200 and $400"

// Replacement with callback (Java 9+)
String result = m.replaceAll(match -> {
    int amount = Integer.parseInt(match.group(1));
    return "$" + (amount * 2);
});
```

> In replacement strings, `$1` refers to group 1, and `$$` is an escaped literal `$`.

---

## Lookahead and Lookbehind

Lookaround assertions check conditions without consuming characters.

| Assertion | Meaning | Example |
|---|---|---|
| `(?=...)` | Positive lookahead | `\\w+(?=\\.)` — word followed by dot |
| `(?!...)` | Negative lookahead | `\\b(?!foo)\\w+\\b` — word that is not "foo" |
| `(?<=...)` | Positive lookbehind | `(?<=\\$)\\d+` — digits preceded by `$` |
| `(?<!...)` | Negative lookbehind | `(?<!\\d)\\.` — dot not preceded by digit |

```java
// Positive lookahead: match "Java" only if followed by " 21"
Pattern.compile("Java(?= 21)").matcher("Java 21 is great").find();  // true
Pattern.compile("Java(?= 21)").matcher("Java 17 is fine").find();   // false

// Negative lookahead: match "Java" NOT followed by "Script"
Pattern.compile("Java(?!Script)").matcher("JavaScript").find();     // false
Pattern.compile("Java(?!Script)").matcher("Java is great").find();  // true

// Positive lookbehind: match price amount
Matcher m = Pattern.compile("(?<=\\$)\\d+").matcher("Price: $100");
m.find();
m.group();   // "100"

// Lookbehind with variable length (Java 13+)
Pattern.compile("(?<=ab+)\\d+");   // Now supported in modern Java
```

---

## Common Regex Patterns

```java
// Email (simplified, not RFC-compliant)
"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"

// Phone number (US format)
"^\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$"

// IPv4 address
"^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$"

// URL
"^https?://[\\w.-]+(?:/[\\w./-]*)?(?:\\?[\\w=&-]*)?$"

// Hex color
"^#[A-Fa-f0-9]{6}$"

// Positive integer
"^\\d+$"

// Floating point number
"^-?\\d+(?:\\.\\d+)?$"

// CamelCase to snake_case conversion
String camel = "helloWorldExample";
String snake = camel.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
// "hello_world_example"
```

---

## String Methods vs Pattern.compile

| Approach | When to use | Performance |
|---|---|---|
| `String.matches(regex)` | One-off validation | Compiles pattern every call |
| `String.split(regex)` | Simple splitting | Compiles pattern every call |
| `String.replaceAll(regex, repl)` | Simple replacement | Compiles pattern every call |
| `Pattern.compile(regex)` + reuse | Repeated operations | Compile once, match many |

```java
// ❌ Inefficient: compiles pattern on every iteration
for (String email : emails) {
    if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) { ... }
}

// ✅ Efficient: compile once
Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
for (String email : emails) {
    if (emailPattern.matcher(email).matches()) { ... }
}
```

---

## Splitting with Regex

```java
// Simple delimiter
"a,b,c".split(",");              // ["a", "b", "c"]

// Regex delimiter
"a  b   c".split("\\s+");        // ["a", "b", "c"]

// Limit parameter
"a,b,c,d".split(",", 2);         // ["a", "b,c,d"] — max 2 elements
"a,b,c,d".split(",", -1);        // ["a", "b", "c", "d"] — include trailing empty

// Edge cases
"a,b,".split(",");               // ["a", "b"] — trailing empty omitted
"a,b,".split(",", -1);           // ["a", "b", ""] — trailing empty preserved

// Splitting with Pattern (more control)
Pattern.compile("\\s*,\\s*").split("a , b , c");
// ["a", "b", "c"] — handles surrounding whitespace
```

---

## Decision Guide

| Task | Approach |
|---|---|
| Validate email/phone once | `String.matches(regex)` |
| Validate in a loop | Pre-compile `Pattern`, reuse `Matcher` |
| Extract groups from text | `Pattern` + `Matcher.group()` |
| Split CSV with quotes | Use OpenCSV library, not regex |
| Simple string replacement | `String.replace()` (no regex, faster) |
| Regex-based replacement | `String.replaceAll()` or `Matcher.replaceAll()` |
| Complex replacement logic | `Matcher.appendReplacement()` + callback |
| Match literal special chars | `Pattern.quote(string)` or `Pattern.LITERAL` |
| Tokenize input | `Scanner` with regex delimiter, or `String.split()` |
