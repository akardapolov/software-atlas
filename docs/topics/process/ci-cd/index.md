# CI/CD Providers

Overview of CI/CD platforms used to automate build, test, and deployment pipelines.

## Contents

- [What Is a CI/CD Provider?](#what-is-a-cicd-provider)
- [Choosing a Provider](#choosing-a-provider)
- [Comparison](#comparison)
- [Core Concepts Across Providers](#core-concepts-across-providers)
- [Providers](#providers)

---

## What Is a CI/CD Provider?

A CI/CD provider is a platform that:

1. **Listens** to version control events (push, pull request, tag)
2. **Runs** a pipeline defined in a config file
3. **Reports** results back to the VCS and team
4. **Deploys** artifacts to target environments

The pipeline definition lives in the repository — this is **pipeline as code**:
config is versioned, reviewed, and evolved alongside application code.

---

## Choosing a Provider

| Factor | Considerations |
|--------|---------------|
| **VCS integration** | Native support for GitHub / GitLab / Bitbucket? |
| **Hosting model** | SaaS vs self-hosted — who manages the infrastructure? |
| **Runner control** | Can you run on your own machines / cloud? |
| **Ecosystem** | Marketplace of reusable actions/orbs/plugins? |
| **Pricing** | Per-minute, per-seat, open-source tiers? |
| **Compliance** | Data residency, on-prem requirements? |
| **Language / stack** | Native support for your toolchain? |

---

## Comparison

| Provider | Hosting | Config | Runners | Free tier | Key strength |
|----------|---------|--------|---------|-----------|--------------|
| [GitHub Actions](github-actions.md) | SaaS | YAML | Hosted + self-hosted | 2 000 min/mo | GitHub marketplace, Actions ecosystem |
| [GitLab CI/CD](gitlab-ci.md) | SaaS + Self | YAML | Hosted + self-managed | 400 min/mo | Built-in to GitLab, full DevSecOps |
| [Jenkins](jenkins.md) | Self-hosted | Groovy DSL | Any | Free (OSS) | Full control, huge plugin ecosystem |
| [CircleCI](circleci.md) | SaaS + Self | YAML | Hosted + self-hosted | 6 000 min/mo | Speed, parallelism, orbs |
| [Azure DevOps](azure-devops.md) | SaaS | YAML | Hosted + self-hosted | 1 800 min/mo | Microsoft stack, Azure integration |
| [Bitbucket Pipelines](bitbucket-pipelines.md) | SaaS | YAML | Hosted | 50 min/mo | Atlassian stack integration |
| [TeamCity](teamcity.md) | Self + Cloud | Kotlin DSL / UI | Any | Free (3 agents) | JetBrains ecosystem, powerful UI |

---

## Core Concepts Across Providers

Every CI/CD provider uses slightly different terminology, but the concepts map to each other:

| Concept | GitHub Actions | GitLab CI/CD | Jenkins | CircleCI | Azure DevOps | Bitbucket | TeamCity |
|---------|---------------|-------------|---------|----------|-------------|-----------|----------|
| **Top-level unit** | Workflow | Pipeline | Pipeline | Workflow | Pipeline | Pipeline | Build Configuration |
| **Execution group** | Job | Stage | Stage | Workflow | Stage | Step | Build Step group |
| **Atomic unit** | Step | Job | Step | Step | Task / Step | Script line | Build Step |
| **Trigger** | `on:` | `only:` / `rules:` | `triggers {}` | `triggers:` | `trigger:` | `branches:` / `default:` | VCS Trigger |
| **Runner** | Runner | Runner | Agent | Executor | Agent | Runner (Atlassian) | Build Agent |
| **Reusable unit** | Action | Template / Include | Shared Library | Orb | Template / Task | Pipe | Kotlin DSL object |
| **Secrets** | `secrets.*` | CI/CD Variables | Credentials | Context | Variable Groups | Repository Variables | Parameters |
| **Artifacts** | `upload-artifact` | `artifacts:` | `archiveArtifacts` | `store_artifacts` | `PublishBuildArtifacts` | `artifacts:` | Artifact rules |
| **Cache** | `actions/cache` | `cache:` | `cache step` | `restore_cache` | `Cache@2` | `caches:` | Build cache |

---

## Providers

- [GitHub Actions](github-actions.md) — GitHub-native, Actions marketplace
- [GitLab CI/CD](gitlab-ci.md) — built-in to GitLab, full DevSecOps platform
- [Jenkins](jenkins.md) — self-hosted, maximum flexibility
- [CircleCI](circleci.md) — speed and parallelism
- [Azure DevOps Pipelines](azure-devops.md) — Microsoft / Azure ecosystem
- [Bitbucket Pipelines](bitbucket-pipelines.md) — Atlassian stack
- [TeamCity](teamcity.md) — JetBrains, Kotlin DSL

---

## Related

- [Continuous Delivery](../index.md#continuous-delivery-2010) — the practice these tools implement
- [DevOps](../index.md#devops-2009) — cultural context
- [Static Analysis](../index.md#static-analysis) — tools commonly run inside pipelines
- [Observability](../index.md#observability) — what you monitor after deployment
