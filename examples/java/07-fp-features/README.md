# FP Features in Java

## Overview

Java 8 (2014) added significant functional features: lambda
expressions, functional interfaces, streams, and `Optional`.
Java 21 (2023) added pattern matching, records, and more FP
features.

## Code

See \`Main.java\` in this directory.

## How to Run

\`\`\`bash
javac Main.java && java Main
\`\`\`

## Key Concepts

- **Lambda expressions** — \`() -> {}\` inline functions
- **Functional interfaces** — Function, Predicate, Consumer, Supplier
- **Method references** — \`String::toUpperCase\` shorthand
- **Streams** — lazy data processing: map, filter, reduce
- **Optional** — null-safe container for values
- **andThen/compose** — function composition
- **Collectors** — toList(), groupingBy(), partitioningBy()
- **Closures** — lambdas capture effectively final variables
- **Record** — immutable data class (Java 16+)
- **Pattern matching** — switch expressions (Java 21+)

## Historical Context

Java 8 was influenced by C#, Scala, and functional languages.
The Streams API mimics LINQ and functional pipeline patterns.
Lambda expressions brought functional programming to mainstream Java.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
