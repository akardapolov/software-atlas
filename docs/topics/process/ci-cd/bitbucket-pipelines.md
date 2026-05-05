# Bitbucket Pipelines

**Type:** SaaS CI/CD, built into Bitbucket Cloud  
**Config file:** `bitbucket-pipelines.yml` in repository root  
**Docs:** https://support.atlassian.com/bitbucket-cloud/docs/bitbucket-pipelines/

---

## Contents

- [Key Concepts](#key-concepts)
- [Where to Find Things in the UI](#where-to-find-things-in-the-ui)
- [Pipeline Structure](#pipeline-structure)
- [Steps and Parallelism](#steps-and-parallelism)
- [Variables and Secrets](#variables-and-secrets)
- [Caches and Artifacts](#caches-and-artifacts)
- [Deployments and Environments](#deployments-and-environments)
- [Pipes](#pipes)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Pipeline** | Full run triggered by push or PR |
| **Step** | Single unit of work on one runner; runs a `script` |
| **Stage** | Named grouping of parallel steps |
| **Pipe** | Reusable Docker-based integration (similar to Action/Orb) |
| **Runner** | Agent that executes steps (Atlassian-hosted or self-hosted) |
| **Artifact** | Files passed between steps or downloaded after pipeline |
| **Deployment** | Step targeting a named environment with deployment tracking |

---

## Where to Find Things in the UI

| What | UI Path |
|------|---------|
| All pipeline runs | `Repository → Pipelines` (left sidebar) |
| Single pipeline run | `Pipelines → <pipeline row>` |
| Step logs | `Pipeline run → <step name>` (expand) |
| Artifacts | `Pipeline run → <step> → Artifacts tab` |
| Test reports | `Pipeline run → <step> → Test Reports tab` |
| Enable Pipelines | `Repository → Settings → Pipelines → Settings → Enable Pipelines` |
| Repository variables | `Repository → Settings → Pipelines → Repository variables` |
| Deployment variables | `Repository → Settings → Pipelines → Deployments → <environment> → Variables` |
| SSH keys | `Repository → Settings → Pipelines → SSH keys` |
| Runners (self-hosted) | `Repository → Settings → Pipelines → Runners` |
| Workspace runners | `Workspace → Settings → Pipelines → Runners` |
| Plan / usage | `Workspace → Settings → Plans & billing` |
| Jira integration | `Repository → Settings → Integrations → Jira` |

---

## Pipeline Structure

```yaml
# bitbucket-pipelines.yml

image: node:20-alpine               # default Docker image for all steps

definitions:
  caches:
    npm: ~/.npm                     # named cache definition

  steps:
    - step: &install-step
        name: Install
        caches: [npm]
        script:
          - npm ci

pipelines:
  default:                          # runs on every push (unless branch-specific matches)
    - step: *install-step
    - step:
        name: Test
        script:
          - npm test

  branches:
    main:                           # only when pushing to main
      - step: *install-step
      - step:
          name: Test
          script:
            - npm test
      - step:
          name: Deploy Staging
          deployment: staging
          script:
            - ./deploy.sh staging

    'release/*':
      - step: *install-step
      - step:
          name: Build Release
          script:
            - npm run build
          artifacts:
            - dist/**

  pull-requests:
    '**':                           # all pull requests
      - step:
          name: Validate
          script:
            - npm ci
            - npm run lint
            - npm test

  tags:
    'v*.*.*':                       # on version tags
      - step:
          name: Deploy Production
          deployment: production
          trigger: manual           # requires click in UI
          script:
            - ./deploy.sh production
```

---

## Steps and Parallelism

### Parallel steps

```yaml
pipelines:
  default:
    - step:
        name: Install
        script: [npm ci]
        artifacts: [node_modules/**]    # pass to parallel steps

    - parallel:
        - step:
            name: Unit Tests
            script: [npm run test:unit]
        - step:
            name: Lint
            script: [npm run lint]
        - step:
            name: Type Check
            script: [npx tsc --noEmit]
```

### Step options

```yaml
- step:
    name: Build
    image: node:20                   # override default image
    size: 2x                         # double the resources (4 GB RAM)
    max-time: 10                     # timeout in minutes
    clone:
      depth: 1                       # shallow clone
    script:
      - npm ci
      - npm run build
    artifacts:
      - dist/**
      - '*.log'
    after-script:                    # always runs, even on failure
      - cat build.log
```

### Manual steps

```yaml
- step:
    name: Deploy Production
    trigger: manual
    deployment: production
    script:
      - ./deploy.sh production
```

UI path: `Pipelines → <run> → <step with play button icon> → Run`.

---

## Variables and Secrets

### Repository variables

Add: `Repository → Settings → Pipelines → Repository variables → Add variable`.

Toggle `Secured` to mask the value in logs.

```yaml
script:
  - echo "Deploying to $TARGET_HOST"   # $TARGET_HOST defined in settings
  - curl -H "Auth: $API_TOKEN" ...     # $API_TOKEN — secured variable
```

### Deployment environment variables

Add: `Repository → Settings → Pipelines → Deployments → <environment> → Add variable`.

Variables are only available in steps targeting that environment:

```yaml
- step:
    deployment: production            # unlocks production-scoped variables
    script:
      - echo $PROD_DATABASE_URL       # defined under 'production' environment
```

### Predefined variables

| Variable | Value |
|----------|-------|
| `$BITBUCKET_COMMIT` | Full commit hash |
| `$BITBUCKET_BRANCH` | Branch name |
| `$BITBUCKET_TAG` | Tag name |
| `$BITBUCKET_PR_ID` | Pull request ID |
| `$BITBUCKET_BUILD_NUMBER` | Auto-incremented build number |
| `$BITBUCKET_REPO_SLUG` | Repository slug |
| `$BITBUCKET_WORKSPACE` | Workspace name |

---

## Caches and Artifacts

### Cache

```yaml
definitions:
  caches:
    npm: ~/.npm
    maven: ~/.m2

- step:
    caches: [npm]                    # restore and save around script
    script:
      - npm ci
```

Custom cache path:

```yaml
definitions:
  caches:
    gradle: .gradle

- step:
    caches: [gradle]
    script:
      - ./gradlew build
```

Clear cache: `Repository → Settings → Pipelines → Caches → clear icon`.

### Artifacts (between steps in the same pipeline)

```yaml
- step:
    name: Build
    script:
      - npm run build
    artifacts:
      - dist/**              # passed to subsequent steps automatically

- step:
    name: Deploy
    script:
      - ls dist/             # dist/ available here
      - ./deploy.sh
```

Artifacts are **not** available between separate pipeline runs.

Download from UI: `Pipeline run → <step> → Artifacts tab`.

---

## Deployments and Environments

Bitbucket Pipelines tracks deployments to named environments.

Default environments: `Test`, `Staging`, `Production`. Custom ones can be added.

Configure: `Repository → Settings → Pipelines → Deployments`.

```yaml
- step:
    name: Deploy to Staging
    deployment: staging               # environment name
    script:
      - ./deploy.sh staging

- step:
    name: Deploy to Production
    deployment: production
    trigger: manual
    script:
      - ./deploy.sh production
```

Deployment history: `Repository → Deployments` (left sidebar).

Jira integration: deployments linked to Jira issues automatically when commit messages reference issue keys (e.g., `PROJ-123`).

---

## Pipes

Pipes are Docker-based reusable integrations maintained by Atlassian and the community.

Browse: https://bitbucket.org/product/features/pipelines/integrations

```yaml
- step:
    script:
      # AWS S3 deploy pipe
      - pipe: atlassian/aws-s3-deploy:1.7.0
        variables:
          AWS_ACCESS_KEY_ID: $AWS_ACCESS_KEY_ID
          AWS_SECRET_ACCESS_KEY: $AWS_SECRET_ACCESS_KEY
          AWS_DEFAULT_REGION: us-east-1
          S3_BUCKET: my-bucket
          LOCAL_PATH: dist/

      # Slack notification pipe
      - pipe: atlassian/slack-notify:2.1.0
        variables:
          WEBHOOK_URL: $SLACK_WEBHOOK
          MESSAGE: 'Deployment complete!'
```

---

## Common Patterns

### Full CI/CD with branch strategy

```yaml
image: node:20-alpine

definitions:
  caches:
    npm: ~/.npm
  steps:
    - step: &test
        name: Test
        caches: [npm]
        script:
          - npm ci
          - npm test
        after-script:
          - npx nyc report --reporter=text

pipelines:
  pull-requests:
    '**':
      - step: *test

  branches:
    main:
      - step: *test
      - step:
          name: Deploy Staging
          deployment: staging
          script:
            - npm run build
            - ./deploy.sh staging

  tags:
    'v*.*.*':
      - step:
          name: Deploy Production
          deployment: production
          trigger: manual
          script:
            - npm run build
            - ./deploy.sh production
```

### Docker build and push

```yaml
- step:
    name: Build Docker image
    services: [docker]              # enable Docker daemon
    script:
      - docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
      - docker build -t myorg/myapp:$BITBUCKET_COMMIT .
      - docker push myorg/myapp:$BITBUCKET_COMMIT
```

---

## Limitations

| Limit | Free | Standard | Premium |
|-------|------|----------|---------|
| Build minutes/month | 50 | 2 500 | 3 500 |
| Parallel steps | 1 | 4 | 8 |
| Step timeout | 120 min | 120 min | 120 min |
| Storage for artifacts | During run only | During run only | During run only |

Bitbucket Pipelines is SaaS only — no self-managed instance (unlike GitLab).
For self-hosted, use self-hosted runners pointing to Bitbucket Cloud.

---

## Related

- [CI/CD Providers Overview](index.md)
- [GitHub Actions](github-actions.md)
- [GitLab CI/CD](gitlab-ci.md)
- [Continuous Delivery](../index.md#continuous-delivery-2010)
