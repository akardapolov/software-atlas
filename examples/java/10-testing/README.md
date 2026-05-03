# Testing in Java

## Overview

Java has built-in `assert` keyword and multiple testing frameworks.
**JUnit** is the de facto standard, with TestNG, Spock as alternatives.
Testing covers unit, integration, and with Mockito — mocking.

## Code

See `Main.java` in this directory.

## How to Run

```bash
javac -cp junit.jar Main.java && java -cp junit.jar:. org.junit.runner.JUnitCore Main
# Or with Maven/Gradle
mvn test
gradle test
```

## Key Concepts

- **JUnit 5** — annotations: `@Test`, `@BeforeEach`, `@ParameterizedTest`
- **Assertions** — `assertEquals`, `assertTrue`, `assertThrows`
- **Test lifecycle** — `@BeforeAll`, `@BeforeEach`, `@AfterEach`
- **Parameterized tests** — run same test with multiple inputs
- **Mocking** — Mockito: `mock()`, `when()`, `verify()`
- **Test doubles** — stubs, fakes, spies
- **Integration tests** — Spring Boot tests, @SpringBootTest
- **Code coverage** — JaCoCo, Cobertura

## Historical Context

JUnit (1997, Kent Beck, Erich Gamma) pioneered xUnit pattern.
It influenced testing frameworks across all languages. Java's assert keyword
(2004) provides runtime assertions. JUnit 5 (2017) modernized
the framework with lambda support.

For more on Java, see [docs/languages/java/](../../../docs/languages/java/)
