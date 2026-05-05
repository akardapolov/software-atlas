# CircleCI

**Type:** SaaS CI/CD platform (cloud + self-hosted option)  
**Config file:** `.circleci/config.yml` in repository root  
**Docs:** https://circleci.com/docs/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Config Structure](#config-structure)
- [Jobs](#jobs)
- [Workflows](#workflows)
- [Executors](#executors)
- [Orbs](#orbs)
- [Contexts and Secrets](#contexts-and-secrets)
- [Artifacts and Cache](#artifacts-and-cache)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Pipeline** | A run triggered by a VCS event; contains workflows |
| **Workflow** | Directed graph of jobs with dependencies and conditions |
| **Job** | A set of steps running on one executor |
| **Step** | Single action (`run`, `checkout`, orb command) |
| **Executor** | Execution environment — Docker, Machine, macOS, Windows |
| **Orb** | Reusable, shareable package of commands, jobs, executors |
| **Context** | Group of environment variables shared across projects |
| **Resource class** | CPU/RAM size for the executor |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All pipelines | `CircleCI Dashboard → Projects → <project> → Pipelines` |
| Specific pipeline | `Pipelines → <pipeline row>` |
| Workflow detail | `Pipelines → <pipeline> → <workflow name>` |
| Job detail and steps | `Workflow → <job box>` |
| Step logs | `Job → <step name>` (expand) |
| Artifacts | `Job → Artifacts` tab |
| Test results | `Job → Tests` tab |
| Project settings | `Projects → <project> → Project Settings` (gear icon) |
| Environment variables (project) | `Project Settings → Environment Variables` |
| SSH keys | `Project Settings → SSH Keys` |
| Webhooks | `Project Settings → Webhooks` |
| Contexts | `Organization Settings → Contexts` |
| Self-hosted runners | `Organization Settings → Self-Hosted Runners` |
| Orb registry | https://circleci.com/developer/orbs |
| Config file validation | `CircleCI CLI: circleci config validate` |
| Billing / usage | `Organization Settings → Plan` |

---

## Config Structure

```yaml
# .circleci/config.yml
version: 2.1

# Reusable executor definitions
executors:
  node-executor:
    docker:
      - image: cimg/node:20.0
    resource_class: medium

# Reusable command definitions
commands:
  install-deps:
    steps:
      - restore_cache:
          keys:
            - npm-{{ checksum "package-lock.json" }}
      - run: npm ci
      - save_cache:
          key: npm-{{ checksum "package-lock.json" }}
          paths:
            - ~/.npm

# Jobs
jobs:
  build:
    executor: node-executor
    steps:
      - checkout
      - install-deps
      - run: npm run build
      - persist_to_workspace:
          root: .
          paths: [dist]

  test:
    executor: node-executor
    steps:
      - checkout
      - install-deps
      - run: npm test
      - store_test_results:
          path: test-results/
      - store_artifacts:
          path: coverage/

  deploy:
    executor: node-executor
    steps:
      - checkout
      - attach_workspace:
          at: .
      - run: ./deploy.sh

# Workflows
workflows:
  ci-cd:
    jobs:
      - build
      - test:
          requires: [build]
      - deploy:
          requires: [test]
          filters:
            branches:
              only: main
```

---

## Jobs

### Parallelism

```yaml
test:
  executor: node-executor
  parallelism: 4                    # split tests across 4 containers
  steps:
    - checkout
    - run: npm ci
    - run:
        command: |
          # CircleCI CLI splits test files across containers
          TESTS=$(circleci tests glob "src/**/*.test.js" | circleci tests split --split-by=timings)
          npx jest $TESTS
    - store_test_results:
        path: test-results/
```

### Resource classes

```yaml
jobs:
  build:
    docker:
      - image: cimg/node:20.0
    resource_class: large           # 4 vCPU, 8 GB RAM
```

| Class | vCPU | RAM |
|-------|------|-----|
| `small` | 1 | 2 GB |
| `medium` (default) | 2 | 4 GB |
| `large` | 4 | 8 GB |
| `xlarge` | 8 | 16 GB |
| `2xlarge` | 16 | 32 GB |

---

## Workflows

### Fan-out / Fan-in

```yaml
workflows:
  build-test-deploy:
    jobs:
      - build
      - test-unit:
          requires: [build]
      - test-integration:
          requires: [build]
      - test-e2e:
          requires: [build]
      - deploy:
          requires:             # waits for ALL three
            - test-unit
            - test-integration
            - test-e2e
          filters:
            branches:
              only: main
```

### Manual approval gate

```yaml
workflows:
  deploy:
    jobs:
      - build
      - test:
          requires: [build]
      - hold-for-approval:
          type: approval         # creates a button in UI
          requires: [test]
      - deploy-production:
          requires: [hold-for-approval]
```

UI path to approve: `Pipeline → Workflow → hold-for-approval (click Approve button)`.

### Scheduled pipeline

```yaml
# In Project Settings → Triggers → Add Scheduled Trigger
# (UI-based, not in config.yml)
# Or via API: https://circleci.com/docs/scheduled-pipelines/
```

Path: `Project Settings → Triggers → Add Scheduled Trigger` → set cron expression and branch.

---

## Executors

```yaml
jobs:
  # Docker executor
  docker-job:
    docker:
      - image: cimg/python:3.12
      - image: postgres:16           # additional service container
        environment:
          POSTGRES_PASSWORD: test
    steps:
      - checkout
      - run: pytest

  # Machine executor (full VM, Docker available)
  machine-job:
    machine:
      image: ubuntu-2204:current
    steps:
      - checkout
      - run: docker build -t myapp .

  # macOS executor
  macos-job:
    macos:
      xcode: '15.3.0'
    steps:
      - checkout
      - run: xcodebuild test

  # Windows executor
  windows-job:
    machine:
      image: windows-server-2022-gui:current
      shell: powershell.exe
    resource_class: windows.medium
    steps:
      - checkout
      - run: dotnet test
```

---

## Orbs

Orbs are reusable packages. Browse at: https://circleci.com/developer/orbs

```yaml
version: 2.1

orbs:
  node: circleci/node@5.2
  aws-cli: circleci/aws-cli@4.1
  slack: circleci/slack@4.13

jobs:
  build:
    executor: node/default
    steps:
      - checkout
      - node/install-packages        # orb command: npm ci + cache
      - run: npm test
      - slack/notify:                # orb command: send Slack notification
          event: fail
          channel: 'ci-alerts'

workflows:
  ci:
    jobs:
      - build:
          context: slack-credentials
```

---

## Contexts and Secrets

### Environment Variables (project-scoped)

Add: `Project Settings → Environment Variables → Add Environment Variable`.

```yaml
jobs:
  deploy:
    steps:
      - run:
          command: ./deploy.sh
          environment:
            DEPLOY_ENV: production   # inline (non-secret)
          # $API_KEY comes from project env vars — no yaml needed
```

### Contexts (org-scoped, shared)

Add context: `Organization Settings → Contexts → Create Context`.

Add variables to context: `Contexts → <context name> → Add Environment Variable`.

```yaml
workflows:
  deploy:
    jobs:
      - deploy:
          context:
            - aws-production         # injects vars from this context
            - slack-notifications
```

---

## Artifacts and Cache

### Storing artifacts

```yaml
- store_artifacts:
    path: dist/
    destination: build-output

- store_artifacts:
    path: coverage/
    destination: coverage-report
```

UI path: `Job → Artifacts` tab — list of uploaded files with download links.

### Workspace (pass data between jobs)

```yaml
# Job 1: persist
- persist_to_workspace:
    root: .
    paths:
      - dist/
      - node_modules/

# Job 2: use
- attach_workspace:
    at: .
```

### Cache

```yaml
- restore_cache:
    keys:
      - v1-deps-{{ checksum "package-lock.json" }}
      - v1-deps-                    # fallback — partial match

- run: npm ci

- save_cache:
    key: v1-deps-{{ checksum "package-lock.json" }}
    paths:
      - ~/.npm
```

---

## Common Patterns

### Dynamic config (config splitting)

```yaml
# .circleci/config.yml — setup phase
version: 2.1
setup: true

orbs:
  continuation: circleci/continuation@1.0

jobs:
  generate-config:
    docker:
      - image: cimg/base:stable
    steps:
      - checkout
      - run:
          name: Generate dynamic config
          command: ./scripts/generate-ci-config.sh > generated_config.yml
      - continuation/continue:
          configuration_path: generated_config.yml

workflows:
  setup:
    jobs:
      - generate-config
```

### Docker build and push

```yaml
orbs:
  docker: circleci/docker@2.6

jobs:
  build-push:
    executor: docker/docker
    steps:
      - setup_remote_docker
      - checkout
      - docker/build:
          image: myorg/myapp
          tag: $CIRCLE_SHA1
      - docker/push:
          image: myorg/myapp
          tag: $CIRCLE_SHA1
```

---

## Limitations

| Limit | Free | Performance plan |
|-------|------|-----------------|
| Free credits/month | 6 000 min | Paid by usage |
| Parallelism | Up to 80 | Higher |
| Storage | 1 GB | More |
| Network | 100 GB | More |
| Concurrent jobs | Limited | More |

---

## Related

- [CI/CD Providers Overview](index.md)
- [GitHub Actions](github-actions.md)
- [Continuous Delivery](../index.md#continuous-delivery-2010)
