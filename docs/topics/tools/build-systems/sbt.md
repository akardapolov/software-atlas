# sbt

**Type:** Interactive build tool for Scala (and JVM)
**Config file:** `build.sbt` in project root; `project/` for meta-build
**Docs:** https://www.scala-sbt.org/1.x/docs/

---

## Contents

- [Key Concepts](#key-concepts)
- [Project Structure](#project-structure)
- [Tasks, Settings, and the Interactive Shell](#tasks-settings-and-the-interactive-shell)
- [Dependencies](#dependencies)
- [Common Commands](#common-commands)
- [Where to Find Things](#where-to-find-things)
- [Code Examples](#code-examples)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Setting** | A value computed once per session (`scalaVersion`, `name`) |
| **Task** | A value re-computed each time it runs (`compile`, `test`) |
| **Key** | The identity of a setting/task — `scalaVersion`, `Compile / sources` |
| **Scope axis** | Settings/tasks live in a 3-axis space: project, configuration, task |
| **Configuration** | `Compile`, `Test`, `IntegrationTest` — distinct compilation contexts |
| **Plugin** | A Scala project added via `project/plugins.sbt`, providing extra tasks |
| **Project** | A subproject (defined with `lazy val foo = (project in file("foo"))`) |
| **Cross-build** | Compile against multiple Scala versions (`crossScalaVersions`) |
| **Zinc** | The incremental Scala compiler used internally |

---

## Project Structure

sbt is conventionally laid out like Maven, but adds a `project/`
directory for the meta-build (the build of the build):

```text
my-app/
├── build.sbt
├── project/
│   ├── build.properties     # sbt version
│   ├── plugins.sbt          # sbt plugins
│   └── Dependencies.scala   # optional: extracted versions/deps
└── src/
    ├── main/
    │   ├── scala/
    │   └── resources/
    └── test/
        ├── scala/
        └── resources/
```

Multi-module:

```text
root/
├── build.sbt                # defines lazy val core, lazy val server
├── project/
├── core/
│   └── src/main/scala/
└── server/
    └── src/main/scala/
```

---

## Tasks, Settings, and the Interactive Shell

sbt's defining feature: launching `sbt` opens an **interactive shell**
that keeps a JVM and the Scala compiler hot.

```bash
$ sbt
[info] welcome to sbt 1.9.8
sbt:my-app> compile
sbt:my-app> ~test       # ~ = re-run on file changes
sbt:my-app> show name   # inspect a setting
sbt:my-app> inspect compile   # see scopes
sbt:my-app> exit
```

The shell amortises JVM startup; for casual use it's the cheapest way
to drive an iterative compile/test loop.

**Settings** are evaluated once when sbt loads:

```scala
ThisBuild / scalaVersion := "3.4.0"
```

**Tasks** re-evaluate each invocation:

```scala
Compile / compile := (Compile / compile).dependsOn(myCustomTask).value
```

The 3-axis scope `<project> / <configuration> / <key>` is the part
that confuses newcomers most. Common shorthands:

```scala
// Whole build
ThisBuild / scalaVersion := "3.4.0"

// One project, default scope
libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"

// Test configuration only
Test / fork := true

// Compile configuration's source dirs
Compile / unmanagedSourceDirectories += baseDirectory.value / "src/main/legacy"
```

---

## Dependencies

```scala
libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core"      % "2.10.0",
    "org.typelevel" %% "cats-effect"    % "3.5.4",
    "ch.qos.logback" % "logback-classic" % "1.5.6",

    "org.scalatest" %% "scalatest"     % "3.2.18" % Test
)
```

Operators:

| Operator | Meaning |
|----------|---------|
| `%` | Java-style coordinate (no Scala suffix) |
| `%%` | Scala-style — appends `_<scalaBinaryVersion>` (`cats-core_3`) |
| `% Test` | Scope — only on test classpath |

Repositories default to Maven Central. Add resolvers:

```scala
resolvers ++= Seq(
    "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "JitPack" at "https://jitpack.io"
)
```

Artifacts are cached under `~/.ivy2/cache/` (or Coursier's `~/.cache/coursier/`
when using the Coursier resolver, which is the default in modern sbt).

---

## Common Commands

```bash
# One-shot mode (CI, scripts) — slow, cold JVM each time
sbt compile
sbt test
sbt "Test/test" "publishLocal"
sbt clean compile

# Interactive (preferred for development)
sbt
> compile
> test
> ~test                       # watch mode
> testOnly *MySpec
> testOnly *MySpec -- -z "renders correctly"
> reload                      # re-read build.sbt after edits

# Multi-project
> projects
> project core
> compile
> rootProject/compile

# Inspect
> show scalaVersion
> inspect tree compile
> last compile                # last log output for a task
> dependencyTree              # via sbt-dependency-graph plugin

# Cross-versions
> +compile                    # compile across all crossScalaVersions
> ++3.4.0 compile             # set Scala version then run

# Publishing
> publishLocal                # to ~/.ivy2/local
> publishM2                   # to ~/.m2/repository
> publishSigned               # via sbt-pgp + sonatype
```

---

## Where to Find Things

| What | Where |
|------|-------|
| Compiled classes | `target/scala-3.4.0/classes/` (or `scala-2.13/classes/`) |
| Test compiled classes | `target/scala-3.4.0/test-classes/` |
| Packaged JAR | `target/scala-3.4.0/<name>_3-<version>.jar` |
| Test reports | `target/test-reports/` |
| Generated sources | `target/scala-3.4.0/src_managed/` |
| Coursier cache | `~/.cache/coursier/v1/https/repo1.maven.org/maven2/...` |
| Ivy2 cache (older sbt) | `~/.ivy2/cache/` |
| Local publish output | `~/.ivy2/local/` (`publishLocal`) or `~/.m2/repository` (`publishM2`) |
| Plugins | `project/plugins.sbt` |
| sbt version pin | `project/build.properties` (`sbt.version=1.9.8`) |
| Per-user global config | `~/.sbt/1.0/global.sbt`, `~/.sbt/1.0/plugins/plugins.sbt` |
| Build log | inside the interactive shell: `last <taskName>` |

---

## Code Examples

### Minimal `build.sbt`

```scala
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.0"
ThisBuild / organization := "com.example"

lazy val root = (project in file("."))
    .settings(
        name := "my-app",
        libraryDependencies ++= Seq(
            "org.typelevel" %% "cats-core" % "2.10.0",
            "org.scalatest" %% "scalatest" % "3.2.18" % Test
        ),
        scalacOptions ++= Seq(
            "-deprecation",
            "-feature",
            "-Wunused:all",
            "-Xfatal-warnings"
        )
    )
```

### Multi-module

```scala
ThisBuild / scalaVersion := "3.4.0"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"

lazy val core = (project in file("core"))
    .settings(
        libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"
    )

lazy val server = (project in file("server"))
    .dependsOn(core)
    .settings(
        libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.6.0"
    )

lazy val root = (project in file("."))
    .aggregate(core, server)
    .settings(publish / skip := true)
```

### `project/plugins.sbt`

```scala
addSbtPlugin("com.eed3si9n"        % "sbt-assembly"       % "2.2.0")
addSbtPlugin("ch.epfl.scala"       % "sbt-scalafix"       % "0.12.0")
addSbtPlugin("org.scalameta"       % "sbt-scalafmt"       % "2.5.2")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.5.1")
```

### `project/build.properties`

```properties
sbt.version=1.9.8
```

---

## Common Patterns

### sbt-assembly for fat JARs

```scala
// project/plugins.sbt
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.2.0")

// build.sbt
assembly / mainClass := Some("com.example.Main")
assembly / assemblyMergeStrategy := {
    case PathList("META-INF", _*) => MergeStrategy.discard
    case _                        => MergeStrategy.first
}
```

```bash
sbt assembly
java -jar target/scala-3.4.0/my-app-assembly-0.1.0-SNAPSHOT.jar
```

### Cross-building Scala 2 + Scala 3

```scala
ThisBuild / crossScalaVersions := Seq("2.13.13", "3.4.0")

lazy val core = (project in file("core"))
    .settings(
        scalacOptions ++= {
            CrossVersion.partialVersion(scalaVersion.value) match {
                case Some((3, _)) => Seq("-Wunused:all")
                case _            => Seq("-Wunused")
            }
        }
    )
```

```bash
sbt +test            # tests against all crossScalaVersions
sbt ++3.4.0 test     # just one
```

### scalafmt + scalafix on save

```scala
// project/plugins.sbt
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"   % "2.5.2")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"   % "0.12.0")

// build.sbt
ThisBuild / semanticdbEnabled := true   // required by scalafix
ThisBuild / scalafixOnCompile := true
```

---

## Limitations

- **Cold start is slow** — even simple commands need a JVM and the Scala
  compiler to load; **stay in the interactive shell** for development
- **DSL is a Scala program** — full power, full risk; build files can be
  bugs of arbitrary complexity
- **Scope confusion** — the 3-axis scope (project / configuration / task)
  is hard to reason about until you've internalised it
- **Plugin version friction** — sbt 1.x plugins are stable, but plugins
  sometimes lag major Scala releases
- **Cryptic error messages** — Scala compiler errors plus sbt's task
  graph make for deep stack traces
- **Limited polyglot support** — works for Java in the same project,
  but TypeScript / Python / native code requires external tooling
- **Memory hungry** — sbt + Scala compiler easily consume 2-4 GB; CI
  containers must be sized for this

---

## Related

- [Maven](maven.md) — sbt borrows Maven's conventions and repositories
- [Gradle](gradle.md) — alternative for Scala on the JVM (via the Scala
  plugin)
- [Bazel](bazel.md) — what to consider for very large Scala monorepos
- [Build Systems Overview](index.md) — comparison and core concepts
- [Functional Programming](../../functional/index.md) — sbt's home
  ecosystem
</content>
