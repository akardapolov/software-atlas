# Data Structures in Java

## Overview

Java's **Collections Framework** provides rich implementations: `ArrayList`, `LinkedList`,
`HashSet`, `TreeSet`, `HashMap`, `PriorityQueue`, `Deque`. Java 14+ adds
`record` classes for immutable data carriers. All collections use generics
for type safety.

## How to Run

```bash
# Compile
javac Main.java

# Run
java DataStructures

# Or with Maven/Gradle
mvn compile
mvn exec:java -Dexec.mainClass="DataStructures"
```

## Key Concepts

- **List** — ordered sequences (ArrayList, LinkedList)
- **Set** — unique elements (HashSet, TreeSet)
- **Map** — key-value pairs (HashMap, TreeMap)
- **Queue** — FIFO (LinkedList as Queue)
- **Stack** — LIFO (Stack class)
- **Deque** — double-ended (ArrayDeque)
- **PriorityQueue** — heap-based priority queue
- **Generics** — type-safe collections (`List<String>`)
- **Records** — immutable data carriers (Java 14+)

## Historical Context

Java (1995, Sun) introduced Collections Framework in Java 1.2 (1998) with extensive library. Generics (Java 5, 2004) added type safety. Records (Java 14, 2020) provided concise way to define immutable data classes.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
