# GitHub Actions

**Type:** SaaS CI/CD, built into GitHub  
**Config file:** `.github/workflows/<name>.yml`  
**Docs:** https://docs.github.com/en/actions

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Triggers](#triggers)
- [Jobs and Steps](#jobs-and-steps)
- [Runners](#runners)
- [Secrets and Variables](#secrets-and-variables)
- [Artifacts and Cache](#artifacts-and-cache)
- [Reusable Workflows and Actions](#reusable-workflows-and-actions)
- [Environments and Deployments](#environments-and-deployments)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Workflow** | YAML file in `.github/workflows/` — top-level automation unit |
| **Job** | Group of steps that run on the same runner |
| **Step** | Single task — shell command or Action |
| **Action** | Reusable unit of automation (from Marketplace or local) |
| **Runner** | Machine that executes jobs (GitHub-hosted or self-hosted) |
| **Event** | Trigger for a workflow (`push`, `pull_request`, `schedule`, etc.) |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All workflows | `Repository → Actions` (tab) |
| Specific workflow runs | `Repository → Actions → <Workflow name>` (left sidebar) |
| Individual run detail | `Repository → Actions → <Workflow name> → <run row>` |
| Job logs | `Repository → Actions → <run> → <job name>` |
| Step logs | `Repository → Actions → <run> → <job> → <step name>` (expand) |
| Workflow file in repo | `Repository → Code → .github/workflows/<name>.yml` |
| Secrets (repo level) | `Repository → Settings → Secrets and variables → Actions → Secrets` |
| Variables (repo level) | `Repository → Settings → Secrets and variables → Actions → Variables` |
| Secrets (org level) | `Organization → Settings → Secrets and variables → Actions` |
| Environments | `Repository → Settings → Environments` |
| Self-hosted runners | `Repository → Settings → Actions → Runners` |
| Org-level runners | `Organization → Settings → Actions → Runners` |
| Billing / usage | `Account → Settings → Billing and plans → Plans and usage` |
| Workflow permissions | `Repository → Settings → Actions → General → Workflow permissions` |

---

## Triggers

```yaml
on:
  push:
    branches: [main, 'release/**']
    paths: ['src/**', 'package.json']      # only when these files change

  pull_request:
    branches: [main]
    types: [opened, synchronize, reopened]

  schedule:
    - cron: '0 2 * * 1'                   # every Monday at 02:00 UTC

  workflow_dispatch:                        # manual trigger via UI or API
    inputs:
      environment:
        description: 'Target environment'
        required: true
        default: 'staging'
        type: choice
        options: [staging, production]

  workflow_call:                            # called from another workflow
    inputs:
      version:
        type: string
        required: true
```

UI path to run manually: `Repository → Actions → <Workflow> → Run workflow (button, top right)`.

---

## Jobs and Steps

```yaml
jobs:
  build:
    name: Build and Test
    runs-on: ubuntu-latest

    steps:
      # 1. Checkout code
      - name: Checkout
        uses: actions/checkout@v4

      # 2. Use a setup action from Marketplace
      - name: Set up Node.js
        uses: actions/setup-node@v4
        with:
          node-version: '20'
          cache: 'npm'

      # 3. Run shell commands
      - name: Install dependencies
        run: npm ci

      - name: Run tests
        run: npm test

      # 4. Conditional step
      - name: Notify on failure
        if: failure()
        run: ./scripts/notify-slack.sh

  deploy:
    name: Deploy
    runs-on: ubuntu-latest
    needs: build                            # wait for 'build' job
    if: github.ref == 'refs/heads/main'

    steps:
      - uses: actions/checkout@v4
      - name: Deploy to production
        run: ./scripts/deploy.sh
        env:
          DEPLOY_TOKEN: ${{ secrets.DEPLOY_TOKEN }}
```

### Job dependencies

```yaml
jobs:
  lint:    { runs-on: ubuntu-latest, steps: [...] }
  test:    { runs-on: ubuntu-latest, steps: [...] }
  build:
    needs: [lint, test]                     # runs after both succeed
    runs-on: ubuntu-latest
    steps: [...]
  deploy:
    needs: build
    runs-on: ubuntu-latest
    steps: [...]
```

### Matrix strategy

```yaml
jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        node-version: [18, 20, 22]
        os: [ubuntu-latest, windows-latest]
      fail-fast: false                      # don't cancel others on first failure
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-node@v4
        with:
          node-version: ${{ matrix.node-version }}
      - run: npm test
```

---

## Runners

### GitHub-hosted runners

| Label | OS | Specs |
|-------|----|-------|
| `ubuntu-latest` | Ubuntu 24.04 | 4 vCPU, 16 GB RAM |
| `ubuntu-22.04` | Ubuntu 22.04 | 4 vCPU, 16 GB RAM |
| `windows-latest` | Windows Server 2022 | 4 vCPU, 16 GB RAM |
| `macos-latest` | macOS 14 (arm64) | 3 vCPU, 7 GB RAM |

### Self-hosted runners

Installation path in UI: `Repository → Settings → Actions → Runners → New self-hosted runner`.

Follow the shown commands to download, configure, and start the runner agent on your machine.

```yaml
jobs:
  build:
    runs-on: [self-hosted, linux, x64]     # target your runner by labels
```

Runner service management on the machine:
- Linux: `./svc.sh install && ./svc.sh start`
- Windows: install as Windows Service via the setup script shown in the UI

---

## Secrets and Variables

### Secrets — encrypted, not shown in logs

```yaml
steps:
  - name: Deploy
    run: ./deploy.sh
    env:
      API_KEY: ${{ secrets.API_KEY }}
      DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
```

To add: `Repository → Settings → Secrets and variables → Actions → New repository secret`.

Scopes:
- **Repository secret** — available to all workflows in this repo
- **Environment secret** — available only when job targets that environment
- **Organisation secret** — shared across repos in the organisation (`Organization → Settings → Secrets and variables → Actions`)

### Variables — plain text, visible in logs

```yaml
env:
  APP_ENV: ${{ vars.APP_ENV }}
  BASE_URL: ${{ vars.BASE_URL }}
```

To add: `Repository → Settings → Secrets and variables → Actions → Variables → New repository variable`.

---

## Artifacts and Cache

### Uploading and downloading artifacts

```yaml
- name: Upload test results
  uses: actions/upload-artifact@v4
  with:
    name: test-results
    path: ./test-output/
    retention-days: 7

# In another job (needs: build):
- name: Download test results
  uses: actions/download-artifact@v4
  with:
    name: test-results
    path: ./test-output/
```

UI path to download artifacts: `Repository → Actions → <run> → Artifacts (bottom of page)`.

### Caching dependencies

```yaml
- uses: actions/cache@v4
  with:
    path: ~/.npm
    key: ${{ runner.os }}-node-${{ hashFiles('**/package-lock.json') }}
    restore-keys: |
      ${{ runner.os }}-node-
```

Cache usage per run: `Repository → Actions → <run> → <job> → Set up job → (cache hit/miss shown in step)`.

All caches: `Repository → Actions → Caches` (left sidebar).

---

## Reusable Workflows and Actions

### Calling a reusable workflow

```yaml
# Caller workflow
jobs:
  deploy:
    uses: ./.github/workflows/deploy-template.yml
    with:
      environment: production
    secrets: inherit
```

```yaml
# deploy-template.yml (reusable)
on:
  workflow_call:
    inputs:
      environment:
        type: string
        required: true

jobs:
  deploy:
    runs-on: ubuntu-latest
    environment: ${{ inputs.environment }}
    steps:
      - run: echo "Deploying to ${{ inputs.environment }}"
```

### Composite action (local)
