## Overview

Java uses **exceptions** and **checked exceptions** for compile-time
error detection. Error types are classes extending \`Throwable\`.
Java 21+ added \`record\` classes for structured data.

## Code

See \`Main.java\` in this directory.

## How to Run

\`\`\`bash
javac Main.java && java Main
\`\`\`

## Key Concepts

- **try-catch-finally** — \`try { ... } catch (Exception e) { ... } finally { ... }\`
- **Exception hierarchy** — Throwable → Error/RuntimeException
- **Checked exceptions** — must declare or catch (compile-time enforcement)
- **Unchecked exceptions** — RuntimeException, NullPointerException
- **Custom exceptions** — extend Exception or RuntimeException
- **throws** — declare exceptions a method might throw
- **Multi-catch** — \`catch (IOException | NumberFormatException)\`
- **finally** — always executes, exception or not
- **Record classes** — \`record\` for immutable data (Java 21+)
- **try-with-resources** — \`try (Resource r) { ... }\` auto-closing

## Historical Context

Java 1.0 had no checked exceptions. Java 2 (1995) added
compile-time checking. Java 7 (2011) simplified with try-with-resources.
Checked exceptions remain unique to Java among modern languages.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
