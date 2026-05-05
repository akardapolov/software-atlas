# Jenkins

**Type:** Self-hosted, open-source automation server  
**Config file:** `Jenkinsfile` in repository root (or configured in UI)  
**Docs:** https://www.jenkins.io/doc/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Declarative vs Scripted Pipeline](#declarative-vs-scripted-pipeline)
- [Declarative Pipeline](#declarative-pipeline)
- [Agents](#agents)
- [Stages and Steps](#stages-and-steps)
- [Credentials and Secrets](#credentials-and-secrets)
- [Parameters](#parameters)
- [Artifacts and Archiving](#artifacts-and-archiving)
- [Shared Libraries](#shared-libraries)
- [Plugins Ecosystem](#plugins-ecosystem)
- [Installation Overview](#installation-overview)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Job / Project** | A configured build task (Freestyle, Pipeline, Multibranch) |
| **Pipeline** | Code-defined sequence of stages defined in Jenkinsfile |
| **Stage** | Logical phase of pipeline (Build, Test, Deploy) — shown in UI |
| **Step** | Single action within a stage (`sh`, `bat`, plugin step) |
| **Agent** | Machine or container where pipeline runs |
| **Node** | Jenkins agent registered to the controller |
| **Build** | Single execution of a job |
| **Workspace** | Directory on agent where build runs |
| **Credential** | Stored secret (username/password, SSH key, token) |
| **Shared Library** | Reusable Groovy code shared across pipelines |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All jobs | `Jenkins Dashboard` (home page) |
| Job detail | `Dashboard → <Folder> → <Job name>` |
| Build history | `Dashboard → <Job> → Build History` (left sidebar) |
| Build console output | `Dashboard → <Job> → <Build #> → Console Output` |
| Pipeline stage view | `Dashboard → <Job>` — Stage View (centre of page) |
| Stage logs | `Dashboard → <Job> → <Build #> → Pipeline Steps → <stage>` |
| Blue Ocean view | `Dashboard → <Job> → Open Blue Ocean` (left sidebar) |
| Job configuration | `Dashboard → <Job> → Configure` (left sidebar) |
| Multibranch branches | `Dashboard → <Multibranch Job> → <branch name>` |
| Credentials | `Dashboard → Manage Jenkins → Credentials → System → Global credentials` |
| Add credential | `Credentials → Global → Add Credentials` |
| Plugins | `Dashboard → Manage Jenkins → Plugins` |
| Installed plugins | `Manage Jenkins → Plugins → Installed plugins` |
| Agents / Nodes | `Dashboard → Manage Jenkins → Nodes` |
| Agent detail | `Manage Jenkins → Nodes → <node name>` |
| System configuration | `Dashboard → Manage Jenkins → System` |
| Global tools (JDK, Maven, etc.) | `Manage Jenkins → Tools` |
| Script console | `Manage Jenkins → Script Console` |
| Audit log | `Manage Jenkins → Audit Trail` (requires plugin) |

---

## Declarative vs Scripted Pipeline

| Aspect | Declarative | Scripted |
|--------|-------------|---------|
| Syntax | Structured `pipeline {}` block | Free-form Groovy |
| Validation | Linted before execution | Errors at runtime |
| Readability | More readable for standard cases | More flexible |
| `when` conditions | Built-in | Manual Groovy `if` |
| Recommended for | New pipelines | Complex logic |

Use Declarative unless you need Groovy flexibility that Declarative can't express.

---

## Declarative Pipeline

```groovy
// Jenkinsfile

pipeline {
    // Where to run
    agent {
        docker {
            image 'node:20-alpine'
            args '-v /tmp:/tmp'
        }
    }

    // Global environment variables
    environment {
        NODE_ENV = 'test'
        APP_NAME = 'my-app'
    }

    // Pipeline options
    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
        disableConcurrentBuilds()
    }

    // Trigger automatically
    triggers {
        pollSCM('H/5 * * * *')     // poll SCM every 5 minutes
        // cron('0 2 * * *')       // nightly at 02:00
    }

    stages {
        stage('Install') {
            steps {
                sh 'npm ci'
            }
        }

        stage('Test') {
            parallel {
                stage('Unit Tests') {
                    steps { sh 'npm run test:unit' }
                }
                stage('Lint') {
                    steps { sh 'npm run lint' }
                }
            }
        }

        stage('Build') {
            steps {
                sh 'npm run build'
                archiveArtifacts artifacts: 'dist/**', fingerprint: true
            }
        }

        stage('Deploy Staging') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([string(credentialsId: 'deploy-token', variable: 'TOKEN')]) {
                    sh './scripts/deploy.sh staging $TOKEN'
                }
            }
        }

        stage('Deploy Production') {
            when {
                buildingTag()
            }
            input {
                message 'Deploy to production?'
                ok 'Deploy'
            }
            steps {
                sh './scripts/deploy.sh production'
            }
        }
    }

    post {
        always {
            junit 'test-results/**/*.xml'
            cleanWs()                          // clean workspace after build
        }
        success {
            echo 'Pipeline succeeded'
        }
        failure {
            mail to: 'team@example.com',
                 subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "See ${env.BUILD_URL}"
        }
    }
}
```

---

## Agents

```groovy
// Run on any available agent
agent any

// Run on specific node by label
agent {
    label 'linux && docker'
}

// Run in Docker container
agent {
    docker {
        image 'maven:3.9-eclipse-temurin-21'
        label 'docker-host'
        registryUrl 'https://registry.example.com'
        registryCredentialsId 'registry-creds'
    }
}

// No global agent — each stage defines its own
agent none

// Per-stage agent override
stage('Build') {
    agent { docker { image 'node:20' } }
    steps { sh 'npm run build' }
}
```

Nodes management: `Manage Jenkins → Nodes → New Node`.

Online/offline status: `Manage Jenkins → Nodes → <node name>` — shows connection status, executor count, labels.

---

## Stages and Steps

### Common step types

```groovy
steps {
    // Shell (Linux/macOS)
    sh 'npm test'
    sh '''
        echo "Multi-line"
        npm run build
    '''

    // Windows
    bat 'npm test'

    // Print to log
    echo "Building branch: ${env.BRANCH_NAME}"

    // Set environment variable for current scope
    withEnv(['DEBUG=true', 'PORT=3000']) {
        sh 'npm start'
    }

    // Change directory
    dir('frontend') {
        sh 'npm ci'
    }

    // Retry on failure
    retry(3) {
        sh './flaky-script.sh'
    }

    // Timeout for a step
    timeout(time: 5, unit: 'MINUTES') {
        sh './long-running-step.sh'
    }
}
```

### `when` conditions

```groovy
stage('Deploy') {
    when {
        anyOf {
            branch 'main'
            branch 'release/*'
        }
        not { changeRequest() }    // not a PR
    }
    steps { sh './deploy.sh' }
}
```

---

## Credentials and Secrets

Add credentials: `Manage Jenkins → Credentials → System → Global credentials → Add Credentials`.

Credential types:
- **Username with password**
- **Secret text** (token, API key)
- **SSH Username with private key**
- **Certificate**
- **Secret file**

```groovy
// Secret text
withCredentials([string(credentialsId: 'my-api-key', variable: 'API_KEY')]) {
    sh 'curl -H "Authorization: $API_KEY" https://api.example.com'
}

// Username + password
withCredentials([usernamePassword(
    credentialsId: 'docker-registry',
    usernameVariable: 'DOCKER_USER',
    passwordVariable: 'DOCKER_PASS'
)]) {
    sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS registry.example.com'
}

// SSH key
withCredentials([sshUserPrivateKey(
    credentialsId: 'deploy-key',
    keyFileVariable: 'SSH_KEY'
)]) {
    sh 'ssh -i $SSH_KEY deploy@server.example.com ./restart.sh'
}

// In environment block (auto-masked in logs)
environment {
    DB_CREDS = credentials('db-credentials')   // sets DB_CREDS_USR and DB_CREDS_PSW
}
```

---

## Parameters

```groovy
pipeline {
    parameters {
        string(name: 'VERSION', defaultValue: 'latest', description: 'Docker tag')
        choice(name: 'ENVIRONMENT', choices: ['staging', 'production'], description: 'Target env')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Skip test stage')
    }

    stages {
        stage('Deploy') {
            steps {
                sh "./deploy.sh ${params.ENVIRONMENT} ${params.VERSION}"
            }
        }
    }
}
```

UI path to trigger with parameters: `Dashboard → <Job> → Build with Parameters` (left sidebar, shown after first build).

---

## Artifacts and Archiving

```groovy
// Archive files (stored in Jenkins, linked from build page)
archiveArtifacts artifacts: 'dist/**/*.jar', fingerprint: true

// Publish JUnit test results (shown in build page)
junit 'build/test-results/**/*.xml'

// Publish HTML reports
publishHTML([
    allowMissing: false,
    reportDir: 'coverage/lcov-report',
    reportFiles: 'index.html',
    reportName: 'Code Coverage'
])
```

UI path: `Dashboard → <Job> → <Build #> → Build Artifacts` / `Test Results`.

---

## Shared Libraries

Add library: `Manage Jenkins → System → Global Pipeline Libraries → Add`.
