# Solomon Hykes

| | |
|---|---|
| **Born** | 1983, Paris, France |
| **Fields** | Distributed systems, developer tools, cloud platforms |
| **Known for** | Docker, Open Container Initiative (OCI), Dagger |

## Biography

Solomon Hykes is a French entrepreneur and software engineer who created Docker and helped trigger the modern container revolution. He co-founded **dotCloud** in 2010 — a Platform-as-a-Service company built on top of Linux containers — and in March 2013 demonstrated the internal tooling that would become Docker at a five-minute lightning talk at PyCon US.

After dotCloud pivoted into Docker, Inc., Hykes served as CTO and led the project until 2018. In 2015 he played a central role in donating Docker's container format and runtime to the **Open Container Initiative (OCI)**, ensuring the ecosystem would not be locked to a single vendor. In 2018 he co-founded **Dagger**, a programmable CI/CD engine built on the same container primitives.

## Key Contributions

### Docker (2013)

Docker packaged existing Linux primitives — namespaces, cgroups, union filesystems — into a developer-friendly tool with three accessible building blocks:

- **Image** — an immutable, layered, content-addressed bundle that can be shipped between machines.
- **Container** — a running process tree isolated from the host but sharing its kernel.
- **Registry** — a network-accessible store for images, with `docker push` / `docker pull` as universal verbs.

The breakthrough was not the underlying technology (LXC, FreeBSD jails, and Solaris zones predated it) but the developer experience: a `Dockerfile`, a single CLI, and a public registry made containers usable by ordinary application developers, not only by ops specialists.

### Open Container Initiative (2015)

To prevent fragmentation and vendor capture of the container format, Hykes and Docker, Inc. donated the `runc` runtime and the image specification to a Linux Foundation working group that became the **OCI**. The OCI now governs three specifications:

- **image-spec** — the on-disk and registry format
- **runtime-spec** — the contract between high-level tools and low-level runtimes
- **distribution-spec** — the registry HTTP API

This step made Podman, containerd, CRI-O, Kubernetes, and others possible as drop-in alternatives.

### Dagger (2018)

After leaving Docker, Hykes co-founded Dagger to apply the container model to CI/CD itself: pipelines are written as code (Go, Python, TypeScript) that runs against a container engine, decoupling pipeline definition from any specific CI provider.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2013 | "The future of Linux Containers" (PyCon US lightning talk) | Talk | — |
| 2014 | Docker 1.0 | Software release | — |
| 2015 | Open Container Initiative announcement | Specification | — |
| 2018 | Dagger | Software | — |

## Influence

### Influenced by

- **Linux kernel namespaces / cgroups** — the underlying isolation primitives Docker exposed
- **LXC** — Docker's first execution backend (later replaced by `libcontainer` / `runc`)
- **Heroku and the dotCloud PaaS lineage** — the "git push to deploy" developer experience
- **Plan 9 union mounts** — conceptual ancestor of layered filesystems

### Influenced

- **Kubernetes (Google, 2014)** — built on top of the container primitives Docker popularized
- **Podman, containerd, CRI-O, Buildah** — alternative implementations enabled by the OCI
- **Microservices adoption** — Docker turned "one process per VM" into "one process per container", lowering the cost of fine-grained services
- **Modern CI/CD** — every major CI provider now offers Docker-based executors
- **Serverless platforms** — Firecracker, Fargate, and Cloud Run extend the container model into ephemeral compute

## Quotes

> "Containers are the new static binary."

> "We started with a simple idea: what if we made it as easy to ship a Linux process as it is to ship an email?"

## Further Reading

- [Wikipedia — Solomon Hykes](https://en.wikipedia.org/wiki/Solomon_Hykes)
- [Docker, Inc. — original PyCon 2013 talk](https://www.youtube.com/watch?v=wW9CAH9nSLs)
- [Open Container Initiative](https://opencontainers.org/)
- [Dagger](https://dagger.io/)

## Related Pages

- [Containers & Orchestration](../topics/containers/index.md)
- [Docker](../topics/containers/docker.md)
- [Kubernetes](../topics/containers/kubernetes.md)
- [DevOps](../topics/process/index.md#devops-2009)
- [Continuous Delivery](../topics/process/index.md#continuous-delivery-2010)
- [Microservices](../topics/architecture/structural/microservices.md)
- [Jez Humble](jez-humble.md) — Continuous Delivery (containerization is its substrate)
- [David Farley](david-farley.md) — Continuous Delivery
