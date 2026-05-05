# TeamCity

**Type:** Self-hosted CI/CD server by JetBrains (+ cloud option via JetBrains Space)  
**Config:** Kotlin DSL (`.teamcity/settings.kts`) **or** UI configuration  
**Docs:** https://www.jetbrains.com/help/teamcity/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Kotlin DSL Configuration](#kotlin-dsl-configuration)
- [Build Steps](#build-steps)
- [Build Triggers](#build-triggers)
- [Parameters and Secrets](#parameters-and-secrets)
- [Build Agents](#build-agents)
- [Artifacts and Caching](#artifacts-and-caching)
- [Build Chains and Dependencies](#build-chains-and-dependencies)
- [Templates and Reuse](#templates-and-reuse)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Project** | Hierarchy node grouping build configurations and sub-projects |
| **Build Configuration** | A named set of steps, triggers, and settings — the main unit |
| **Build** | Single execution of a build configuration |
| **Build Step** | An atomic task within a build configuration (Runner type) |
| **Runner** | Plugin that executes a step type (Gradle, Maven, Python, CLI, etc.) |
| **Agent** | Machine registered to TeamCity that runs builds |
| **VCS Root** | Source control connection (URL, branch, credentials) |
| **Build Chain** | Multiple build configurations connected by snapshot/artifact dependencies |
| **Template** | Reusable build configuration shared by multiple configurations |
| **Parameter** | Named variable available as `%param.name%` in configuration |
| **Kotlin DSL** | Programmatic configuration in `.teamcity/settings.kts` |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All projects | `TeamCity root → Projects` (left sidebar) |
| Project detail | `Projects → <project name>` |
| Build configurations in project | `Projects → <project> → Build Configurations` tab |
| Run build manually | `Build Configuration → Run` button (top right) |
| Build history | `Build Configuration → Builds` tab |
| Single build detail | `Build Configuration → Builds → <build row>` |
| Build log | `Build → Build Log` tab |
| Build log (specific step) | `Build → Build Log → expand step name` |
| Test results | `Build → Tests` tab |
| Artifacts | `Build → Artifacts` tab |
| Parameters (config level) | `Build Configuration → Edit → Parameters` tab |
| Parameters (project level) | `Projects → <project> → Edit → Parameters` tab |
| VCS Roots | `Projects → <project> → Edit → VCS Roots` tab |
| Build triggers | `Build Configuration → Edit → Triggers` tab |
| Build steps | `Build Configuration → Edit → Build Steps` tab |
| Agent requirements | `Build Configuration → Edit → Agent Requirements` tab |
| Artifact dependencies | `Build Configuration → Edit → Artifact Dependencies` tab |
| Snapshot dependencies | `Build Configuration → Edit → Snapshot Dependencies` tab |
| Build chains visualization | `Build Configuration → Dependencies (tab, if enabled)` |
| Agents | `Agents` (top navigation) |
| Agent pools | `Agents → Pools` tab |
| Agent detail | `Agents → <agent name>` |
| Cleanup rules | `Administration → Server Administration → Clean-Up` |
| Version Control Settings (DSL) | `Projects → <project> → Edit → Versioned Settings` |
| Kotlin DSL source | `.teamcity/` folder in your repository |

---

## Kotlin DSL Configuration

Enable: `Projects → <project> → Edit → Versioned Settings → Enable → Store as Kotlin DSL`.

After enabling, TeamCity writes current config to `.teamcity/` in your VCS root and keeps them in sync.

### Minimal example

```kotlin
// .teamcity/settings.kts

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2024.03"

project {
    buildType(Build)
    buildType(Deploy)
}

object Build : BuildType({
    name = "Build and Test"
    description = "Run tests on every commit"

    vcs {
        root(DslContext.settingsRoot)
        cleanCheckout = true
    }

    steps {
        script {
            name = "Install"
            scriptContent = "npm ci"
        }
        script {
            name = "Test"
            scriptContent = "npm test"
        }
        script {
            name = "Build"
            scriptContent = "npm run build"
        }
    }

    triggers {
        vcs {
            branchFilter = "+:*"
        }
    }

    artifactRules = """
        dist/** => dist.zip
        test-results/** => test-results.zip
    """.trimIndent()
})

object Deploy : BuildType({
    name = "Deploy to Staging"

    dependencies {
        snapshot(Build) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }

    steps {
        script {
            name = "Deploy"
            scriptContent = "./deploy.sh staging"
        }
    }

    params {
        param("env.TARGET", "staging")
    }
})
```

### Project hierarchy

```kotlin
project {
    subProject(FrontendProject)
    subProject(BackendProject)

    buildType(IntegrationTests)
}

object FrontendProject : Project({
    name = "Frontend"
    buildType(FrontendBuild)
    buildType(FrontendDeploy)
})
```

---

## Build Steps

### Available runner types

| Runner | Used for |
|--------|----------|
| **Command Line** | Any shell/bat script |
| **Gradle** | Java/Kotlin Gradle builds |
| **Maven** | Java Maven builds |
| **MSBuild** | .NET / C# projects |
| **.NET** | dotnet CLI commands |
| **Python** | Python scripts |
| **Node.js** | npm/yarn/pnpm scripts |
| **Docker** | Build, push, run containers |
| **Kotlin Script** | Kotlin scripts |

### In Kotlin DSL

```kotlin
steps {
    // Command line
    script {
        name = "Run tests"
        scriptContent = "npm test"
        workingDir = "frontend"
    }

    // Gradle
    gradle {
        name = "Build"
        tasks = "clean build"
        buildFile = "build.gradle.kts"
        gradleParams = "-Penv=production"
    }

    // Maven
    maven {
        name = "Package"
        goals = "clean package"
        runnerArgs = "-DskipTests=false"
    }

    // Docker
    dockerCommand {
        name = "Build image"
        commandType = build {
            source = file { path = "Dockerfile" }
            namesAndTags = "myapp:%build.number%"
        }
    }
}
```

### In UI

Path: `Build Configuration → Edit → Build Steps → Add build step → choose Runner type`.

---

## Build Triggers

```kotlin
triggers {
    // Trigger on VCS changes
    vcs {
        branchFilter = """
            +:main
            +:release/*
            -:feature/*
        """.trimIndent()
        perCheckinTriggering = true     // trigger per commit, not per push
        triggerRules = "+:src/**"       // only when these paths change
    }

    // Scheduled trigger
    schedule {
        schedulingPolicy = cron {
            hours = "2"
            dayOfWeek = "MON-FRI"
        }
        branchFilter = "+:main"
        triggerBuild = always()
    }

    // Trigger when another build finishes
    finishBuildTrigger {
        buildType = "${Build.id}"
        successfulOnly = true
        branchFilter = "+:main"
    }
}
```

Configure in UI: `Build Configuration → Edit → Triggers → Add new trigger`.

---

## Parameters and Secrets

### Types

| Type | Prefix | Description |
|------|--------|-------------|
| **Configuration** | `%param.name%` | Visible in UI and logs |
| **Environment** | `%env.NAME%` | Passed as env variable to steps |
| **System** | `%system.name%` | Passed to build tools |
| **Password** | any, marked as Password type | Masked in logs |

### Kotlin DSL

```kotlin
params {
    param("env.NODE_ENV", "production")
    param("app.version", "%build.number%")

    // Password — value set in UI, referenced here
    password("env.API_KEY", "credentialsJSON:my-stored-cred")

    // Checkbox for manual runs
    checkbox("deploy.enabled", "false",
        label = "Enable deployment",
        checked = "true",
        unchecked = "false"
    )

    // Select list
    select("target.env", "staging",
        label = "Target environment",
        options = listOf("staging", "production")
    )
}
```

### Predefined TeamCity variables

| Variable | Value |
|----------|-------|
| `%build.number%` | Auto-incremented build number |
| `%build.vcs.number%` | VCS revision (commit hash) |
| `%teamcity.build.branch%` | Branch being built |
| `%system.teamcity.projectName%` | Project name |
| `%teamcity.agent.name%` | Name of the agent |

Set password parameters: `Build Configuration → Edit → Parameters → + Add → Type: Password`.

---

## Build Agents

### Installing an agent

1. `Agents → Install Build Agents → (choose method)` — download the agent installer
2. Follow instructions on screen: download `.zip`, unpack, edit `conf/buildAgent.properties`
3. Set `serverUrl=http://your-teamcity:8111`
4. Start agent: `./bin/agent.sh start` (Linux) or `bin\agent.bat start` (Windows)
5. Authorize in UI: `Agents → Unauthorized → Authorize`

### Agent pools

Pool created: `Agents → Pools → Create new pool`.

Assign agent: `Agents → <agent> → Agent pools tab → assign pool`.

Assign pool to project: `Projects → <project> → Edit → Agent Pools → assign`.

```kotlin
// Require specific agent capability in Kotlin DSL
requirements {
    equals("docker.version", "24")
    contains("system.agent.name", "linux")
    exists("env.JAVA_HOME")
}
```

---

## Artifacts and Caching

### Artifacts

```kotlin
artifactRules = """
    dist/** => artifacts/dist.zip
    build/reports/ => reports
    *.log
""".trimIndent()
```

In UI: `Build Configuration → Edit → General Settings → Artifact paths`.

Download: `Build → Artifacts tab`.

### Artifact dependencies

```kotlin
dependencies {
    artifacts(Build) {
        buildRule = lastSuccessful()
        artifactRules = "dist.zip!** => dist"
    }
}
```

### Build caches (TeamCity 2024.03+)

```kotlin
// Cache npm node_modules between builds
features {
    buildCache {
        name = "npm-cache"
        use = true
        publish = true
        rules = "node_modules"
        keyIncludes = "package-lock.json"
    }
}
```

For earlier versions: use artifact dependencies with `lastSuccessful()` as a cache substitute.

---

## Build Chains and Dependencies

### Snapshot dependency — run in sequence, share source revision

```kotlin
object Deploy : BuildType({
    dependencies {
        snapshot(Build) {
            onDependencyFailure = FailureAction.FAIL_TO_START
            onDependencyCancel = FailureAction.CANCEL
        }
    }
})
```

Build chain visualization: `Build Configuration → Dependencies → Build Chain (tab)` — shows the full graph.

### Composite build — aggregate multiple builds

```kotlin
object AllTests : BuildType({
    type = BuildTypeSettings.Type.COMPOSITE

    dependencies {
        snapshot(UnitTests) {}
        snapshot(IntegrationTests) {}
        snapshot(LintCheck) {}
    }
})
```

---

## Templates and Reuse

### Build template

Create in UI: `Project → Edit → Build Configuration Templates → Create new template`.

Or in Kotlin DSL:

```kotlin
object NodeTemplate : Template({
    name = "Node.js Build Template"

    params {
        param("env.NODE_VERSION", "20")
    }

    steps {
        script { scriptContent = "npm ci" }
        script { scriptContent = "npm test" }
    }

    triggers {
        vcs { branchFilter = "+:*" }
    }
})

object MyApp : BuildType({
    name = "My App Build"
    templates(NodeTemplate)            // inherit from template

    // Override or extend:
    steps {
        script {
            name = "Extra step"
            scriptContent = "npm run extra"
        }
    }
})
```

---

## Common Patterns

### Multi-stage pipeline with build chain

```kotlin
project {
    sequence {
        build(Compile)
        parallel {
            build(UnitTests)
            build(Lint)
        }
        build(IntegrationTests)
        build(BuildDockerImage)
        build(DeployStaging)
    }
}
```

`sequence` and `parallel` are Kotlin DSL helpers that set up snapshot dependencies automatically.

### Docker image build and push

```kotlin
steps {
    script {
        name = "Login to registry"
        scriptContent = """
            docker login -u %env.REGISTRY_USER% -p %env.REGISTRY_PASS% registry.example.com
        """.trimIndent()
    }

    dockerCommand {
        name = "Build"
        commandType = build {
            source = file { path = "Dockerfile" }
            namesAndTags = "registry.example.com/myapp:%build.vcs.number%"
        }
    }

    dockerCommand {
        name = "Push"
        commandType = push {
            namesAndTags = "registry.example.com/myapp:%build.vcs.number%"
        }
    }
}
```

---

## Limitations

| Aspect | Free (Professional) | Enterprise |
|--------|---------------------|------------|
| Build configurations | 100 | Unlimited |
| Build agents | 3 | Unlimited |
| Users | Unlimited | Unlimited |
| Projects | Unlimited | Unlimited |
| Support | Community | Commercial |

TeamCity is self-hosted — you manage server and agents.
JetBrains Cloud (via JetBrains Space) provides a hosted option.

---

## Related

- [CI/CD Providers Overview](index.md)
- [GitHub Actions](github-actions.md)
- [GitLab CI/CD](gitlab-ci.md)
- [Jenkins](jenkins.md)
- [Continuous Delivery](../index.md#continuous-delivery-2010)
