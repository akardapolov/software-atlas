# GitLab CI/CD

**Type:** Built-in CI/CD platform inside GitLab (SaaS + Self-managed)  
**Config file:** `.gitlab-ci.yml` in repository root  
**Docs:** https://docs.gitlab.com/ee/ci/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Pipeline Structure](#pipeline-structure)
- [Jobs](#jobs)
- [Stages](#stages)
- [Rules and Conditions](#rules-and-conditions)
- [Variables and Secrets](#variables-and-secrets)
- [Runners](#runners)
- [Artifacts and Cache](#artifacts-and-cache)
- [Environments and Deployments](#environments-and-deployments)
- [Templates and Includes](#templates-and-includes)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Pipeline** | Full set of stages and jobs triggered by an event |
| **Stage** | Logical group of jobs (run in parallel within the stage) |
| **Job** | Single unit of work — runs a `script` on a runner |
| **Runner** | Agent that picks up and executes jobs |
| **Artifact** | Files produced by a job, passed to subsequent jobs or downloaded |
| **Cache** | Files reused across pipeline runs to speed up jobs |
| **Environment** | Named deployment target (staging, production) with history |
| **Variable** | CI/CD variable — available as environment variable in jobs |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All pipelines | `Project → Build → Pipelines` |
| Single pipeline detail | `Project → Build → Pipelines → <pipeline row>` |
| Job log | `Project → Build → Pipelines → <pipeline> → <stage badge> → <job>` |
| Job artifacts | `Project → Build → Pipelines → <pipeline> → <job> → Download artifacts` |
| All jobs across pipelines | `Project → Build → Jobs` |
| Schedule pipelines | `Project → Build → Pipeline schedules` |
| Manual pipeline trigger | `Project → Build → Pipelines → Run pipeline (button)` |
| CI/CD Variables (project) | `Project → Settings → CI/CD → Variables` (expand) |
| CI/CD Variables (group) | `Group → Settings → CI/CD → Variables` |
| Runners (project) | `Project → Settings → CI/CD → Runners` (expand) |
| Environments | `Project → Operate → Environments` |
| Deployment history | `Project → Operate → Environments → <environment name>` |
| Pipeline editor (visual) | `Project → Build → Pipeline editor` |
| Pipeline config lint | `Project → Build → Pipeline editor → Validate` tab |
| SAST / security scans | `Project → Secure → Security dashboard` |

---

## Pipeline Structure

```yaml
# .gitlab-ci.yml

# Default settings applied to all jobs
default:
  image: node:20-alpine
  before_script:
    - npm ci

# Stages run sequentially; jobs within a stage run in parallel
stages:
  - build
  - test
  - deploy

# Jobs
build:
  stage: build
  script:
    - npm run build
  artifacts:
    paths:
      - dist/
    expire_in: 1 hour

unit-tests:
  stage: test
  script:
    - npm run test:unit

lint:
  stage: test
  script:
    - npm run lint

deploy-staging:
  stage: deploy
  script:
    - ./scripts/deploy.sh staging
  environment:
    name: staging
    url: https://staging.myapp.com
  rules:
    - if: $CI_COMMIT_BRANCH == "main"
```

---

## Jobs

### Basic job

```yaml
job-name:
  stage: test
  image: python:3.12-slim        # override default image
  before_script:
    - pip install -r requirements.txt
  script:
    - pytest tests/
  after_script:
    - echo "Cleanup if needed"
  tags:
    - docker                     # target runners with this tag
```

### Job with dependencies

```yaml
deploy:
  stage: deploy
  needs:                         # start immediately when listed jobs finish
    - build                      # don't wait for all jobs in previous stage
    - unit-tests
  script:
    - ./deploy.sh
```

### Manual jobs

```yaml
deploy-production:
  stage: deploy
  script:
    - ./deploy.sh production
  when: manual                   # requires human click in UI
  allow_failure: false           # blocks pipeline if not triggered
```

UI path to trigger: `Project → Build → Pipelines → <pipeline> → <stage> → <job (play button)>`.

---

## Stages

```yaml
stages:
  - validate      # lint, security checks
  - build         # compile, package
  - test          # unit, integration, e2e
  - publish       # push Docker image, npm package
  - deploy        # deploy to environment
  - notify        # Slack, email

# Jobs not assigned to a stage use the default "test" stage
```

---

## Rules and Conditions

`rules:` replaces the older `only:`/`except:` syntax.

```yaml
job:
  script: ./run.sh
  rules:
    - if: $CI_PIPELINE_SOURCE == "merge_request_event"
      when: always
    - if: $CI_COMMIT_BRANCH == $CI_DEFAULT_BRANCH
      when: always
    - when: never                # default — don't run in other cases

# Predefined CI variables useful in rules:
# $CI_COMMIT_BRANCH        — current branch name
# $CI_COMMIT_TAG           — tag name if triggered by tag push
# $CI_PIPELINE_SOURCE      — push / merge_request_event / schedule / web / trigger
# $CI_DEFAULT_BRANCH       — typically 'main'
# $CI_MERGE_REQUEST_ID     — ID if inside an MR pipeline
```

---

## Variables and Secrets

### Defining in `.gitlab-ci.yml`

```yaml
variables:
  NODE_ENV: production
  CACHE_VERSION: v2

job:
  script: echo $NODE_ENV
```

### Defining in UI

To add: `Project → Settings → CI/CD → Variables → Add variable`.

Options per variable:
- **Type:** Variable (plain text) or File (written to disk)
- **Environment scope:** `*` (all) or specific environment name
- **Protected:** only exposed on protected branches / tags
- **Masked:** value hidden in job logs

```yaml
# Accessing a masked/protected variable
deploy:
  script: ./deploy.sh
  environment: production
  variables:
    DEPLOY_TOKEN: $DEPLOY_TOKEN   # defined in UI, not in yaml
```

### Predefined CI/CD variables

| Variable | Value |
|----------|-------|
| `$CI_COMMIT_SHA` | Full commit hash |
| `$CI_COMMIT_SHORT_SHA` | First 8 chars of commit hash |
| `$CI_COMMIT_BRANCH` | Branch name |
| `$CI_PROJECT_PATH` | `namespace/project` |
| `$CI_REGISTRY_IMAGE` | Container registry URL for the project |
| `$CI_ENVIRONMENT_NAME` | Current environment name |
| `$GITLAB_USER_LOGIN` | Username who triggered the pipeline |

Full list: `Project → Settings → CI/CD → Variables` — scroll to "Predefined variables" link.

---

## Runners

### Shared runners (GitLab.com)

Available by default on GitLab.com. No configuration needed.

To check availability: `Project → Settings → CI/CD → Runners → Available shared runners`.

### Group / Project runners

Registered to a group or project specifically.

To register: `Project → Settings → CI/CD → Runners → New project runner`.

Follow the shown `gitlab-runner register` command on your machine.

```yaml
job:
  tags:
    - docker         # route to runners with this tag
    - my-team        # combine tags for specificity
```

Runner tags: `Project → Settings → CI/CD → Runners → <runner> → Edit`.

### Runner executor types

| Executor | When to use |
|----------|-------------|
| `docker` | Isolated containers — most common |
| `shell` | Runs directly on host — faster, less isolated |
| `kubernetes` | Auto-scales on K8s clusters |
| `docker-machine` | Auto-scale on cloud VMs (legacy) |

---

## Artifacts and Cache

### Artifacts — passed between jobs, downloadable

```yaml
build:
  script: npm run build
  artifacts:
    paths:
      - dist/
      - coverage/
    reports:
      junit: test-results.xml    # shown in MR test summary
      coverage_report:
        coverage_format: cobertura
        path: coverage/cobertura.xml
    expire_in: 1 week

test:
  needs:
    - job: build
      artifacts: true            # download artifacts from 'build'
  script: npm test
```

UI path: `Project → Build → Pipelines → <pipeline> → <job> → Browse / Download (right side)`.

### Cache — reused across runs, not guaranteed

```yaml
default:
  cache:
    key:
      files:
        - package-lock.json
    paths:
      - node_modules/

# Per-job override
build:
  cache:
    key: $CI_COMMIT_REF_SLUG
    paths:
      - .gradle/
```

Cache management: `Project → Build → Pipelines` — select a pipeline, then `Clear runner caches` button.

---

## Environments and Deployments

```yaml
deploy-staging:
  script: ./deploy.sh staging
  environment:
    name: staging
    url: https://staging.myapp.com
    on_stop: stop-staging         # job to call when stopping env

stop-staging:
  script: ./teardown.sh staging
  environment:
    name: staging
    action: stop
  when: manual

deploy-production:
  script: ./deploy.sh production
  environment:
    name: production
    url: https://myapp.com
  rules:
    - if: $CI_COMMIT_TAG
      when: manual
```

Environment history: `Project → Operate → Environments → <environment name> → Deployment history`.

Rollback: `Project → Operate → Environments → <environment> → <deployment row> → Re-deploy`.

---

## Templates and Includes

### Including external files

```yaml
include:
  # From GitLab's template library
  - template: Security/SAST.gitlab-ci.yml
  - template: Code-Quality.gitlab-ci.yml

  # From another file in the same repo
  - local: '.gitlab/ci/build.yml'

  # From another project
  - project: 'mygroup/shared-ci'
    file: '/templates/deploy.yml'
    ref: main

  # From a URL
  - remote: 'https://example.com/shared-pipeline.yml'
```

### Extending jobs

```yaml
.base-deploy:                    # hidden job (dot prefix) — template only
  script:
    - ./deploy.sh $TARGET
  rules:
    - if: $CI_COMMIT_BRANCH == "main"

deploy-eu:
  extends: .base-deploy
  variables:
    TARGET: eu-west-1

deploy-us:
  extends: .base-deploy
  variables:
    TARGET: us-east-1
```

---

## Common Patterns

### Docker image build and push to GitLab registry

```yaml
build-image:
  stage: build
  image: docker:24
  services:
    - docker:24-dind
  variables:
    DOCKER_TLS_CERTDIR: '/certs'
  script:
    - docker login -u $CI_REGISTRY_USER -p $CI_REGISTRY_PASSWORD $CI_REGISTRY
    - docker build -t $CI_REGISTRY_IMAGE:$CI_COMMIT_SHORT_SHA .
    - docker push $CI_REGISTRY_IMAGE:$CI_COMMIT_SHORT_SHA
```

### Merge request pipeline + main branch pipeline

```yaml
.mr-only:
  rules:
    - if: $CI_PIPELINE_SOURCE == "merge_request_event"

.main-only:
  rules:
    - if: $CI_COMMIT_BRANCH == $CI_DEFAULT_BRANCH

lint:
  extends: .mr-only
  script: npm run lint

deploy:
  extends: .main-only
  script: ./deploy.sh
```

### Parallel matrix

```yaml
test:
  parallel:
    matrix:
      - NODE_VERSION: ['18', '20', '22']
        OS: ['ubuntu', 'alpine']
  image: node:${NODE_VERSION}-${OS}
  script: npm test
```

---

## Limitations

| Limit | GitLab.com (Free) | GitLab.com (Premium+) |
|-------|-------------------|-----------------------|
| CI/CD minutes/month | 400 | 10 000+ |
| Parallel jobs | 5 | More |
| Storage for artifacts | 5 GB per namespace | More |
| Pipeline timeout (default) | 60 minutes | Configurable up to 1 month |

Self-managed GitLab: limits depend on your runner capacity — no minute cap.

---

## Related

- [CI/CD Providers Overview](index.md)
- [GitHub Actions](github-actions.md)
- [Continuous Delivery](../index.md#continuous-delivery-2010)
