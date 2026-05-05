# Azure DevOps Pipelines

**Type:** SaaS CI/CD, part of Azure DevOps  
**Config file:** `azure-pipelines.yml` in repository root  
**Docs:** https://learn.microsoft.com/en-us/azure/devops/pipelines/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Pipeline Structure](#pipeline-structure)
- [Jobs and Steps](#jobs-and-steps)
- [Tasks](#tasks)
- [Templates](#templates)
- [Variables and Secrets](#variables-and-secrets)
- [Agents](#agents)
- [Artifacts](#artifacts)
- [Environments and Deployments](#environments-and-deployments)
- [Service Connections](#service-connections)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Pipeline** | Full automation definition in YAML or classic editor |
| **Stage** | Group of jobs (e.g., Build, Test, Deploy) — shown as swimlane |
| **Job** | Set of steps on one agent |
| **Step** | Single task (script or Task plugin) |
| **Agent** | Machine that runs jobs (Microsoft-hosted or self-hosted) |
| **Task** | Reusable step from Marketplace (`- task: TaskName@version`) |
| **Template** | Reusable YAML fragment (steps, jobs, stages, variables) |
| **Variable** | Name/value pair available in pipeline |
| **Variable group** | Named set of variables shared across pipelines |
| **Service connection** | Secure external resource connection (Azure, Docker, etc.) |
| **Environment** | Named deployment target with approval gates and history |
| **Artifact** | Files published by pipeline and consumed by later stages |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All pipelines | `Azure DevOps Project → Pipelines → Pipelines` |
| Pipeline runs | `Pipelines → <pipeline name>` |
| Single run detail | `Pipelines → <pipeline name> → <run row>` |
| Stage / job view | `Run → <stage box> → <job>` |
| Step logs | `Run → <job> → <step name>` (expand) |
| Create new pipeline | `Pipelines → New pipeline` |
| Edit pipeline YAML | `Pipelines → <pipeline> → Edit` |
| Pipeline settings | `Pipelines → <pipeline> → ⋯ (three dots) → Settings` |
| Variables (pipeline) | `Pipelines → <pipeline> → Edit → Variables` (top right) |
| Variable groups | `Pipelines → Library → Variable groups` |
| Secure files | `Pipelines → Library → Secure files` |
| Service connections | `Project Settings → Service connections` |
| Agent pools | `Project Settings → Agent pools` |
| Self-hosted agents | `Project Settings → Agent pools → <pool> → Agents` |
| Environments | `Pipelines → Environments` |
| Deployment history | `Pipelines → Environments → <environment name>` |
| Releases (classic) | `Pipelines → Releases` |
| Artifacts | `Run → <job> → Published artifacts (tab)` |
| Test results | `Run → Tests (tab)` |
| Extensions (marketplace) | `Project Settings → Extensions` or https://marketplace.visualstudio.com/azuredevops |

---

## Pipeline Structure

```yaml
# azure-pipelines.yml

trigger:
  branches:
    include:
      - main
      - release/*
  paths:
    include:
      - src/
      - tests/

pr:
  branches:
    include:
      - main

variables:
  NODE_VERSION: '20.x'
  BUILD_CONFIGURATION: Release

stages:
  - stage: Build
    displayName: Build and Test
    jobs:
      - job: BuildJob
        displayName: Build
        pool:
          vmImage: ubuntu-latest
        steps:
          - task: NodeTool@0
            inputs:
              versionSpec: $(NODE_VERSION)
            displayName: Install Node.js

          - script: npm ci
            displayName: Install dependencies

          - script: npm test
            displayName: Run tests

          - task: PublishTestResults@2
            inputs:
              testResultsFormat: JUnit
              testResultsFiles: '**/test-results.xml'
            condition: always()

  - stage: Deploy
    displayName: Deploy to Staging
    dependsOn: Build
    condition: and(succeeded(), eq(variables['Build.SourceBranch'], 'refs/heads/main'))
    jobs:
      - deployment: DeployStaging
        displayName: Deploy
        environment: staging
        strategy:
          runOnce:
            deploy:
              steps:
                - script: ./deploy.sh staging
```

---

## Jobs and Steps

### Parallel jobs

```yaml
jobs:
  - job: UnitTests
    pool:
      vmImage: ubuntu-latest
    steps:
      - script: npm run test:unit

  - job: IntegrationTests
    pool:
      vmImage: ubuntu-latest
    steps:
      - script: npm run test:integration

  - job: PublishResults
    dependsOn:
      - UnitTests
      - IntegrationTests
    condition: succeeded()
    steps:
      - script: ./aggregate-results.sh
```

### Matrix strategy

```yaml
strategy:
  matrix:
    node18:
      NODE_VERSION: '18.x'
    node20:
      NODE_VERSION: '20.x'
    node22:
      NODE_VERSION: '22.x'
  maxParallel: 3

steps:
  - task: NodeTool@0
    inputs:
      versionSpec: $(NODE_VERSION)
  - script: npm test
```

### Step conditions

```yaml
steps:
  - script: npm test
    displayName: Run tests

  - task: PublishCodeCoverageResults@1
    condition: succeededOrFailed()    # run even if tests fail

  - script: ./notify-failure.sh
    condition: failed()

  # Condition expressions
  - script: ./deploy.sh
    condition: |
      and(
        succeeded(),
        eq(variables['Build.SourceBranch'], 'refs/heads/main'),
        ne(variables['Build.Reason'], 'PullRequest')
      )
```

---

## Tasks

Tasks are pre-built steps from Azure DevOps Marketplace.

```yaml
steps:
  # Node.js setup
  - task: NodeTool@0
    inputs:
      versionSpec: '20.x'

  # .NET build
  - task: DotNetCoreCLI@2
    inputs:
      command: build
      projects: '**/*.csproj'
      arguments: '--configuration Release'

  # Docker build and push
  - task: Docker@2
    inputs:
      command: buildAndPush
      repository: myorg/myapp
      dockerfile: Dockerfile
      containerRegistry: my-docker-service-connection
      tags: |
        $(Build.BuildId)
        latest

  # Publish artifacts
  - task: PublishBuildArtifacts@1
    inputs:
      pathToPublish: dist/
      artifactName: build-output

  # Azure Web App deploy
  - task: AzureWebApp@1
    inputs:
      azureSubscription: my-azure-service-connection
      appName: my-web-app
      package: dist/

  # Kubernetes deploy
  - task: KubernetesManifest@0
    inputs:
      action: deploy
      kubernetesServiceConnection: my-k8s-connection
      manifests: k8s/deployment.yml
```

Browse tasks: `Pipelines → <pipeline> → Edit → Show assistant` (right panel) or https://marketplace.visualstudio.com/azuredevops.

---

## Templates

### Step template

```yaml
# templates/install-and-test.yml
parameters:
  - name: nodeVersion
    type: string
    default: '20.x'

steps:
  - task: NodeTool@0
    inputs:
      versionSpec: ${{ parameters.nodeVersion }}
  - script: npm ci
  - script: npm test
```

```yaml
# azure-pipelines.yml — using template
jobs:
  - job: Test
    steps:
      - template: templates/install-and-test.yml
        parameters:
          nodeVersion: '20.x'
```

### Stage template

```yaml
# templates/deploy-stage.yml
parameters:
  - name: environment
    type: string
  - name: serviceConnection
    type: string

stages:
  - stage: Deploy_${{ parameters.environment }}
    jobs:
      - deployment: Deploy
        environment: ${{ parameters.environment }}
        strategy:
          runOnce:
            deploy:
              steps:
                - script: ./deploy.sh ${{ parameters.environment }}
```

---

## Variables and Secrets

### Inline variables

```yaml
variables:
  APP_NAME: my-app
  BUILD_ENV: production
  IMAGE_TAG: $(Build.BuildId)       # use predefined variable
```

### Variable groups (shared across pipelines)

Create: `Pipelines → Library → Variable groups → + Variable group`.

```yaml
variables:
  - group: production-secrets       # name of the variable group
  - name: APP_NAME
    value: my-app
```

### Secret variables

In the UI: `Pipelines → <pipeline> → Edit → Variables → + Add → lock icon (secret)`.

Or in a variable group: `Library → <group> → lock icon next to variable`.

Secret variables are masked in logs automatically.

```yaml
steps:
  - script: ./deploy.sh
    env:
      API_KEY: $(MY_SECRET_KEY)     # $(varname) syntax
```

### Predefined pipeline variables

| Variable | Value |
|----------|-------|
| `$(Build.BuildId)` | Numeric build ID |
| `$(Build.SourceBranch)` | `refs/heads/main` |
| `$(Build.SourceBranchName)` | `main` |
| `$(Build.Repository.Name)` | Repo name |
| `$(Build.DefinitionName)` | Pipeline name |
| `$(System.TeamProject)` | Project name |
| `$(Agent.OS)` | `Linux`, `Windows_NT`, `Darwin` |

---

## Agents

### Microsoft-hosted agents

| Image | OS | Pre-installed |
|-------|----|--------------|
| `ubuntu-latest` | Ubuntu 22.04 | Node, Python, Java, Docker, etc. |
| `ubuntu-22.04` | Ubuntu 22.04 | Same |
| `windows-latest` | Windows Server 2022 | VS Build Tools, Node, etc. |
| `macos-latest` | macOS 13 | Xcode, Node, etc. |

### Self-hosted agents

Register: `Project Settings → Agent pools → <pool name> → New agent`.

Download and configure agent on machine, then run `./run.sh` (Linux/macOS) or `run.cmd` (Windows).

```yaml
pool:
  name: MyPrivatePool             # self-hosted pool name
  demands:
    - docker                      # agent capability
    - java
```

Agent status: `Project Settings → Agent pools → <pool> → Agents → <agent row>`.

---

## Artifacts

### Build artifacts

```yaml
- task: PublishBuildArtifacts@1
  inputs:
    pathToPublish: dist/
    artifactName: webapp
    publishLocation: Container    # stored in Azure DevOps

# Download in another stage/job
- task: DownloadBuildArtifacts@1
  inputs:
    artifactName: webapp
    downloadPath: $(System.ArtifactsDirectory)
```

UI path: `Run → Summary → Published → <artifact name>`.

### Pipeline artifacts (faster)

```yaml
- task: PublishPipelineArtifact@1
  inputs:
    targetPath: dist/
    artifact: webapp
    publishLocation: pipeline

- task: DownloadPipelineArtifact@2
  inputs:
    artifact: webapp
    path: $(Pipeline.Workspace)/webapp
```

---

## Environments and Deployments

Create environment: `Pipelines → Environments → New environment`.

Add approvals: `Environments → <environment> → ⋯ → Approvals and checks → + Approvals`.

```yaml
stages:
  - stage: DeployProd
    jobs:
      - deployment: DeployProduction
        environment:
          name: production
          resourceType: VirtualMachine   # or Kubernetes
        strategy:
          runOnce:
            deploy:
              steps:
                - script: ./deploy.sh production
```

Deployment history: `Pipelines → Environments → <environment> → Deployments` tab.

---

## Service Connections

Service connections store credentials for external systems.

Create: `Project Settings → Service connections → New service connection`.

Types:
- **Azure Resource Manager** — deploy to Azure services
- **Docker Registry** — push/pull Docker images
- **Kubernetes** — deploy to K8s cluster
- **GitHub** — checkout from GitHub repos
- **Generic** — custom HTTP connections
- **SSH** — SSH to servers

```yaml
steps:
  - task: AzureCLI@2
    inputs:
      azureSubscription: my-azure-connection    # service connection name
      scriptType: bash
      scriptLocation: inlineScript
      inlineScript: az webapp restart --name myapp --resource-group mygroup
```

---

## Common Patterns

### PR validation + main branch deploy

```yaml
trigger:
  branches:
    include: [main]

pr:
  branches:
    include: [main]

stages:
  - stage: Validate
    jobs:
      - job: Test
        steps:
          - script: npm ci && npm test

  - stage: Deploy
    dependsOn: Validate
    condition: and(succeeded(), ne(variables['Build.Reason'], 'PullRequest'))
    jobs:
      - deployment: Deploy
        environment: staging
        strategy:
          runOnce:
            deploy:
              steps:
                - script: ./deploy.sh staging
```

### Container build and push to ACR

```yaml
- task: Docker@2
  inputs:
    command: buildAndPush
    containerRegistry: acr-service-connection
    repository: $(IMAGE_REPOSITORY)
    dockerfile: Dockerfile
    buildContext: .
    tags: |
      $(Build.BuildId)
      latest
```

---

## Limitations

| Limit | Free (public repo) | Free (private repo) |
|-------|-------------------|---------------------|
| Parallel jobs | 10 | 1 |
| Minutes/month | Unlimited | 1 800 |
| Self-hosted parallel jobs | Unlimited | Unlimited |

---

## Related

- [CI/CD Providers Overview](index.md)
- [GitHub Actions](github-actions.md)
- [GitLab CI/CD](gitlab-ci.md)
- [Continuous Delivery](../index.md#continuous-delivery-2010)
