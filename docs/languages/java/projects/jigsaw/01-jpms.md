# Module System (JPMS)

> **Project:** Jigsaw
> **Java version:** 9 (final)
> **Status:** Released

The Java Platform Module System (JPMS) adds a module layer on top of packages, providing strong encapsulation, explicit dependencies, and reliable configuration for both application code and the JDK itself.

For the complete API reference with all directives, module types, and migration strategies, see the main feature page: [Module System (JPMS)](../../17-modules.md).

---

## History and Evolution

### The Monolithic JDK

Before Java 9, the JDK was a single monolithic artifact. The `rt.jar` file contained everything:

- Core classes (`java.lang`, `java.util`)
- GUI toolkits (AWT, Swing, JavaFX)
- Enterprise APIs (CORBA, JAX-WS)
- XML processing
- Database connectivity (JDBC)

A "Hello World" program dragged in Swing and CORBA even though it never used them. This made the JDK unnecessarily large for deployment scenarios like containers and microservices.

### Project Jigsaw Timeline

| Milestone | Year | Event |
|---|---|---|
| Jigsaw announced | 2008 | Originally planned for Java 7 |
| Deferred | 2010 | Postponed to Java 8 |
| Deferred again | 2013 | Postponed to Java 9 |
| Java 9 released | 2017 | JPMS finalized |
| Java 11 LTS | 2018 | Stable, ecosystem adoption begins |
| Java 17 LTS | 2021 | Most frameworks support modules |

The repeated delays reflect the enormous scope of the change. Modularity affects:
- The JDK itself (95+ modules)
- Every JVM classloader
- Reflection and deep introspection
- Build tools (Maven, Gradle)
- IDEs (IntelliJ, Eclipse, VS Code)
- Frameworks (Spring, Hibernate, OSGi)

### The Design Philosophy

JPMS was designed with three principles:

1. **Reliable configuration** — Fail fast at startup, not mid-execution
2. **Strong encapsulation** — Internal APIs are truly internal
3. **Improved security** — Reduced attack surface through explicit exports

Unlike OSGi (which provides dynamic module lifecycle) or Maven (which manages build-time dependencies), JPMS focuses on **compile-time and runtime integrity**.

## Implementation Deep Dive

### Module Descriptors

A module is declared in `module-info.java`:

```java
module com.example.app {
    requires java.base;        // implicit, can be omitted
    requires java.sql;
    requires transitive com.example.lib;

    exports com.example.app.api;           // public API
    opens com.example.app.entity;          // reflection access

    uses com.example.app.spi.LoggerFactory;
    provides com.example.app.spi.LoggerFactory
        with com.example.app.impl.ConsoleLoggerFactory;
}
```

The descriptor is compiled to `module-info.class` and placed at the root of the module's JAR.

### Readability and Accessibility

Two distinct concepts govern module interaction:

**Readability** (dependency direction):
```
Module A ---requires---> Module B    (A reads B)
```

**Accessibility** (visibility of types):
```
Package P in Module B ---exports---> All modules (or specific module)
```

For code in A to access a type in B:
1. A must read B (`requires`)
2. B must export the package containing the type (`exports`)
3. The type must be public

This two-layer model prevents accidental coupling and makes dependencies explicit.

### The Unnamed Module

All classpath JARs are combined into a single **unnamed module**:

- Exports all packages
- Reads all named modules
- Cannot be read by named modules (unless via `--add-reads`)

This is the **backward compatibility mode**. Legacy applications run unchanged on the classpath, but they don't get the benefits of strong encapsulation.

### Migration Strategy

```
Phase 1: Classpath (unnamed module)
    ↓
Phase 2: Module path as automatic modules
    ↓
Phase 3: Named modules with module-info.java
```

**Automatic modules** (Phase 2) are plain JARs placed on the module path:
- Module name derived from `Automatic-Module-Name` manifest entry or JAR filename
- Exports all packages
- Reads all other modules

This bridges the gap: your application can be modularized while dependencies remain on the classpath or module path as automatic modules.

### jlink: Custom Runtime Images

One of JPMS's most practical features is `jlink`:

```bash
# Create a minimal JVM containing only needed modules
jlink --module-path $JAVA_HOME/jmods:lib \
      --add-modules com.example.app \
      --launcher app=com.example.app/com.example.app.Main \
      --output my-runtime

# Result: a ~40 MB runtime vs. ~200 MB full JDK
my-runtime/bin/app
```

This is transformative for:
- **Container images** — Smaller base images, faster deploys
- **Desktop apps** — Self-contained distributions
- **Embedded systems** — Minimal footprint

### Framework Integration

Modern frameworks have adapted to JPMS:

| Framework | Module Support | Notes |
|---|---|---|
| Spring Boot 3.x | Full | `module-info.java` recommended |
| Hibernate 6.x | Full | Uses `opens` for entity reflection |
| Jackson 2.x | Full | Automatic module name |
| JUnit 5 | Full | Platform, Jupiter, Vintage as modules |
| Log4j2 / SLF4J | Full | ServiceLoader integration |

The `--add-opens` flag is commonly needed during migration:

```bash
java --add-opens java.base/java.lang=ALL-UNNAMED \
     --add-opens com.example.app/com.example.app.model=org.hibernate.orm.core \
     -p lib:app.jar \
     -m com.example.app/com.example.app.Main
```

## Impact on the Ecosystem

JPMS changed how the Java ecosystem thinks about encapsulation:

- **Libraries** now distinguish public API from internal implementation
- **Frameworks** use `ServiceLoader` instead of classpath scanning
- **Build tools** support `module-info.java` in dependency resolution
- **JDK itself** can evolve faster — internal APIs can be changed or removed

## See Also

- [Module System (JPMS) — main feature page](../../17-modules.md)
- [Reflection API](../../16-reflection.md) — `opens` and deep reflection
- [Project Jigsaw Overview](index.md)
- [Examples: OOP and Modules](../../../examples/java/06-oop-modules/README.md)
