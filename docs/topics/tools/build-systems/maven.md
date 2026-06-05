# Maven

**Type:** Convention-over-configuration build system for the JVM
**Config file:** `pom.xml` in project root (per-module)
**Docs:** https://maven.apache.org/guides/

---

## Contents

- [Key Concepts](#key-concepts)
- [Project Structure](#project-structure)
- [Lifecycle and Phases](#lifecycle-and-phases)
- [Dependencies and Repositories](#dependencies-and-repositories)
- [Common Commands](#common-commands)
- [Where to Find Things](#where-to-find-things)
- [Code Examples](#code-examples)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **POM** | *Project Object Model* — the `pom.xml` file describing a project |
| **Coordinates** | `groupId:artifactId:version` — unique identity of an artifact |
| **Goal** | An action a plugin can perform (`compile`, `install`, `deploy`) |
| **Phase** | An ordered step in the build lifecycle; phases bind to goals |
| **Lifecycle** | The fixed sequence of phases (`default`, `clean`, `site`) |
| **Plugin** | A bundle of goals; almost everything Maven does is a plugin |
| **Dependency** | Another artifact your project needs at compile / test / runtime |
| **Scope** | `compile` (default), `test`, `provided`, `runtime`, `system`, `import` |
| **Repository** | Where artifacts are downloaded from (Central) and stored (`~/.m2`) |
| **Parent POM** | A `pom.xml` other modules inherit from; centralises versions and config |
| **BOM** | *Bill of Materials* — a `pom.xml` with `<dependencyManagement>` only |
| **Profile** | A named set of overrides activated by env, JDK, OS, or `-P` flag |
| **Reactor** | Maven's multi-module build orchestrator |

---

## Project Structure

Maven prescribes a standard layout. Following it means almost zero
configuration; deviating means fighting the tool.

```text
my-app/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/        # production sources
│   │   ├── resources/   # production resources (classpath)
│   │   └── webapp/      # WAR projects only
│   └── test/
│       ├── java/        # test sources
│       └── resources/   # test resources
└── target/              # build output (compiled classes, JARs, reports)
```

Multi-module:

```text
parent/
├── pom.xml              # packaging=pom, lists <modules>
├── module-a/
│   ├── pom.xml
│   └── src/main/java/
└── module-b/
    ├── pom.xml
    └── src/main/java/
```

---

## Lifecycle and Phases

Maven has three built-in lifecycles. The `default` lifecycle is what you
use 95% of the time:

```mermaid
flowchart LR
    Validate["validate"] --> Compile["compile"]
    Compile --> Test["test"]
    Test --> Package["package"]
    Package --> Verify["verify"]
    Verify --> Install["install"]
    Install --> Deploy["deploy"]

    style Compile fill:#a5d6a7
    style Test fill:#fff59d
    style Package fill:#bbdefb
    style Deploy fill:#c8e6c9
```

| Phase | What it does |
|-------|--------------|
| **validate** | Check project structure |
| **compile** | Compile `src/main/java` to `target/classes` |
| **test-compile** | Compile `src/test/java` to `target/test-classes` |
| **test** | Run unit tests via Surefire |
| **package** | Bundle into JAR / WAR / EAR in `target/` |
| **verify** | Run integration tests via Failsafe |
| **install** | Copy artifact into the local `~/.m2/repository` |
| **deploy** | Upload artifact to a remote repository |

**Rule:** running phase X executes all preceding phases too.
`mvn package` runs `validate → compile → test → package`.

The `clean` lifecycle (`mvn clean`) deletes `target/`. Combine:
`mvn clean install` is the canonical "rebuild everything" command.

---

## Dependencies and Repositories

Dependencies are declared as coordinates with a scope:

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.0</version>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```

Maven resolves the **transitive closure** automatically. Conflicting
versions are resolved by *nearest definition wins* — see them with
`mvn dependency:tree`.

**Repository hierarchy** (search order):

1. Local cache: `~/.m2/repository`
2. Repositories in `pom.xml` (`<repositories>`)
3. Repositories in `~/.m2/settings.xml`
4. Maven Central (default — `repo1.maven.org/maven2/`)

Override defaults in `~/.m2/settings.xml` (mirrors, credentials,
proxies, profiles).

**`<dependencyManagement>`** centralises versions without forcing them:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>3.2.0</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

Child modules then declare the dependency without `<version>`.

---

## Common Commands

```bash
# Compile only
mvn compile

# Run tests
mvn test

# Build a JAR (skips deploy)
mvn package

# Install to local ~/.m2 repo
mvn install

# Skip tests (use sparingly)
mvn install -DskipTests

# Run a specific module from the parent
mvn -pl module-a -am install     # -am also builds dependencies

# Activate a profile
mvn install -P production

# Set a property
mvn install -Drevision=1.2.3-SNAPSHOT

# Inspect the resolved dependency graph
mvn dependency:tree

# Find why a specific transitive dep is included
mvn dependency:tree -Dincludes=com.example:slf4j-api

# Check for newer plugin / dependency versions
mvn versions:display-plugin-updates
mvn versions:display-dependency-updates

# Run a specific goal (without going through the lifecycle)
mvn jacoco:report
```

---

## Where to Find Things

| What | Where |
|------|-------|
| Local artifact cache | `~/.m2/repository/<groupId>/<artifactId>/<version>/` |
| User settings (mirrors, credentials) | `~/.m2/settings.xml` |
| Compiled main classes | `target/classes/` |
| Compiled test classes | `target/test-classes/` |
| JAR / WAR output | `target/<artifactId>-<version>.jar` |
| Surefire test reports | `target/surefire-reports/` |
| Failsafe IT reports | `target/failsafe-reports/` |
| Generated sources | `target/generated-sources/` |
| Effective POM (after inheritance) | `mvn help:effective-pom` |
| Effective settings | `mvn help:effective-settings` |
| Wrapper script | `./mvnw` (or `mvnw.cmd`) — generated by `mvn wrapper:wrapper` |

---

## Code Examples

### Minimal application `pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.5.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals><goal>shade</goal></goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.example.App</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### Multi-module parent `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>module-a</module>
        <module>module-b</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>5.10.0</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

---

## Common Patterns

### Maven Wrapper (`mvnw`)

Pin Maven version per-project so contributors and CI use the same one:

```bash
mvn wrapper:wrapper -Dmaven=3.9.6
./mvnw clean install                # uses pinned version
```

The wrapper downloads Maven into `~/.m2/wrapper/` on first run.

### Profiles

Activate a different config in CI vs local:

```xml
<profiles>
    <profile>
        <id>production</id>
        <properties>
            <log.level>WARN</log.level>
        </properties>
    </profile>
</profiles>
```

Activate via `-P production`, by JDK, by OS, or by missing/present file.

### Spring Boot starter

A typical Spring Boot project uses the parent POM to inherit dependency
versions and the `spring-boot-maven-plugin`:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.0</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### Jib (containerise without a Dockerfile)

```xml
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.4.0</version>
    <configuration>
        <to><image>registry.example.com/my-app:${project.version}</image></to>
    </configuration>
</plugin>
```

Run `mvn jib:build` to push a layered OCI image — no Docker daemon needed.

---

## Limitations

- **XML verbosity** — `pom.xml` files become large quickly; `dependencyManagement` and parent POMs only partly help
- **Imperative escape hatches are awkward** — anything outside the standard lifecycle (custom code generation, conditional logic) means writing or hunting for a plugin
- **No first-class polyglot support** — Maven is JVM-centric; mixing TypeScript or Go in the same build means external scripts
- **Build performance** — single-threaded by default; `mvn -T 1C` (one thread per core) helps, but is still slow vs Gradle/Bazel for large projects
- **Incremental builds are weak** — `mvn install` recompiles much more than necessary; the takari incremental compiler partly fixes this
- **Convention is rigid** — non-standard layouts require fighting Maven, not configuring it
- **No build cache out of the box** — every CI run starts cold (workarounds: cache `~/.m2/repository`, but that's not the same as a build cache)

---

## Related

- [Gradle](gradle.md) — incremental, polyglot, more flexible alternative
- [sbt](sbt.md) — Scala-native cousin
- [Build Systems Overview](index.md) — comparison and core concepts
- [Java](../../../languages/java/index.md) — primary ecosystem
- [CI/CD Providers](../ci-cd/index.md) — Maven runs inside Jenkins, GitHub Actions, etc.
- [Containers & Orchestration](../../containers/index.md) — Jib plugin produces OCI images
</content>
