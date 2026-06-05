# Docker

**Type:** Container runtime + image builder + local orchestrator (Compose)  
**Config files:** `Dockerfile`, `compose.yaml`, `~/.docker/config.json`  
**Docs:** https://docs.docker.com

---

## Contents

- [Key Concepts](#key-concepts)
- [Architecture](#architecture)
- [Where to Find Things](#where-to-find-things)
- [Lifecycle](#lifecycle)
- [Dockerfile](#dockerfile)
- [Docker Compose](#docker-compose)
- [Images and Registries](#images-and-registries)
- [Networking](#networking)
- [Storage](#storage)
- [Security](#security)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Image** | Immutable, layered, content-addressed package — what you ship |
| **Container** | Running (or stopped) instance of an image |
| **Layer** | Filesystem diff produced by one Dockerfile instruction |
| **Volume** | Persistent storage managed by Docker, decoupled from container lifecycle |
| **Network** | Virtual network connecting containers (`bridge`, `host`, `overlay`, `none`, `macvlan`) |
| **Registry** | Server storing images (Docker Hub, GHCR, ECR, GAR, Harbor) |
| **Daemon** | Long-running `dockerd` process that manages images, containers, networks, volumes |
| **Engine API** | REST API exposed by the daemon over a Unix socket or TCP |

---

## Architecture

### High-Level Components

```mermaid
graph TB
    CLI[Docker CLI<br/>docker command]
    API[Docker Engine API<br/>REST over socket]
    Daemon[dockerd<br/>Docker Daemon]
    containerd[containerd<br/>Container runtime]
    runc[runc<br/>OCI runtime]
    
    CLI -->|HTTP/Unix socket| API
    API --> Daemon
    Daemon --> containerd
    containerd --> runc
    
    Daemon --> Images[(Images)]
    Daemon --> Containers[(Containers)]
    Daemon --> Networks[(Networks)]
    Daemon --> Volumes[(Volumes)]
    
    style Daemon fill:#2496ED,color:#fff
    style containerd fill:#575757,color:#fff
    style runc fill:#262626,color:#fff
```

### Image Layer Architecture

```mermaid
graph BT
    Container[Container Layer<br/>Read/Write]
    Layer3[Layer 3: COPY app files]
    Layer2[Layer 2: RUN npm install]
    Layer1[Layer 1: WORKDIR /app]
    Base[Base Image: node:20-alpine]
    
    Container -.->|CoW| Layer3
    Layer3 --> Layer2
    Layer2 --> Layer1
    Layer1 --> Base
    
    style Container fill:#90EE90
    style Layer3 fill:#87CEEB
    style Layer2 fill:#87CEEB
    style Layer1 fill:#87CEEB
    style Base fill:#FFD700
```

### Container Runtime Stack

```mermaid
graph TB
    subgraph "Docker Engine"
        dockerd[dockerd]
        containerd[containerd]
        shim[containerd-shim]
        runc[runc]
    end
    
    subgraph "Linux Kernel"
        namespaces[Namespaces<br/>pid, net, mnt, uts, ipc]
        cgroups[Control Groups<br/>CPU, memory, I/O limits]
        capabilities[Capabilities]
        seccomp[Seccomp]
    end
    
    dockerd --> containerd
    containerd --> shim
    shim --> runc
    runc --> namespaces
    runc --> cgroups
    runc --> capabilities
    runc --> seccomp
    
    style dockerd fill:#2496ED,color:#fff
    style containerd fill:#575757,color:#fff
    style namespaces fill:#FF6B6B
    style cgroups fill:#4ECDC4
```

### Network Architecture

```mermaid
graph TB
    subgraph "Host"
        docker0[docker0 bridge]
        
        subgraph "Container 1"
            eth0_1[eth0<br/>172.17.0.2]
        end
        
        subgraph "Container 2"
            eth0_2[eth0<br/>172.17.0.3]
        end
        
        subgraph "Custom Bridge Network"
            customBr[br-abc123]
            
            subgraph "Container 3"
                eth0_3[eth0<br/>172.18.0.2]
            end
            
            subgraph "Container 4"
                eth0_4[eth0<br/>172.18.0.3]
            end
        end
        
        hostNIC[Host NIC<br/>eth0]
    end
    
    eth0_1 -.veth.-> docker0
    eth0_2 -.veth.-> docker0
    docker0 --> hostNIC
    
    eth0_3 -.veth.-> customBr
    eth0_4 -.veth.-> customBr
    customBr --> hostNIC
    
    style docker0 fill:#FFD700
    style customBr fill:#90EE90
```

### Storage Driver Architecture

```mermaid
graph TB
    subgraph Container["Container Filesystem"]
        ContainerLayer[Container Layer<br/>Read-Write<br/>Thin CoW]
    end
    
    subgraph Image["Image Layers"]
        L3[Layer 3]
        L2[Layer 2]
        L1[Layer 1]
        BaseLayer[Base Layer]
    end
    
    subgraph Driver["Storage Driver<br/>overlay2, btrfs, zfs"]
        UnionFS[Union Filesystem]
    end
    
    subgraph Host["Host Filesystem"]
        HostFS[var/lib/docker]
    end
    
    ContainerLayer --> UnionFS
    L3 --> UnionFS
    L2 --> UnionFS
    L1 --> UnionFS
    BaseLayer --> UnionFS
    UnionFS --> HostFS
    
    style ContainerLayer fill:#90EE90
    style UnionFS fill:#FF6B6B
```

---

## Where to Find Things

| What | Where |
|------|-------|
| Daemon socket (Linux) | `/var/run/docker.sock` |
| Image and container storage | `/var/lib/docker/` |
| Per-user CLI config | `~/.docker/config.json` |
| Daemon config | `/etc/docker/daemon.json` |
| Registry credentials | `~/.docker/config.json` `auths` block (or credential helper) |
| Build cache | `/var/lib/docker/buildkit/` |
| Compose files | `compose.yaml` (or `docker-compose.yml`) in project root |
| Docker Desktop GUI | Containers / Images / Volumes / Builds tabs |

---

## Lifecycle

```mermaid
flowchart LR
    Build["docker build"] --> Tag["docker tag"]
    Tag --> Push["docker push"]
    Push --> Pull["docker pull"]
    Pull --> Run["docker run"]
    Run --> Exec["docker exec"]
    Exec --> Stop["docker stop"]
    Stop --> Rm["docker rm"]
```

### Container State Machine

```mermaid
stateDiagram-v2
    [*] --> Created: docker create
    Created --> Running: docker start
    Running --> Paused: docker pause
    Paused --> Running: docker unpause
    Running --> Stopped: docker stop
    Stopped --> Running: docker start
    Running --> Restarting: docker restart
    Restarting --> Running
    Stopped --> [*]: docker rm
    Created --> [*]: docker rm
```

| Verb | What it does |
|------|--------------|
| `build` | Read `Dockerfile`, produce a tagged image |
| `tag` | Add a name (`registry/repo:tag`) to an existing image ID |
| `push` / `pull` | Move images between local store and registry |
| `run` | Create + start a container in one step |
| `start` / `stop` / `restart` | Manage running state without recreating |
| `exec` | Run a process in a running container |
| `logs` | Stream stdout/stderr of a container |
| `inspect` | Dump container or image metadata as JSON |
| `rm` / `rmi` | Remove containers / images |
| `system prune` | Garbage-collect stopped containers, dangling images, unused networks |

---

## Dockerfile

Declarative recipe for building an image. Each instruction creates a new layer.

```dockerfile
# syntax=docker/dockerfile:1.7
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN --mount=type=cache,target=/root/.npm npm ci
COPY . .
RUN npm run build

FROM node:20-alpine
WORKDIR /app
COPY --from=build /app/dist ./dist
COPY --from=build /app/node_modules ./node_modules
USER node
EXPOSE 3000
CMD ["node", "dist/server.js"]
```

### Multi-Stage Build Flow

```mermaid
graph LR
    subgraph "Stage 1: Build"
        FROM1[FROM node:20-alpine AS build]
        WORK1[WORKDIR /app]
        COPY1[COPY package*.json]
        RUN1[RUN npm ci]
        COPY2[COPY source]
        RUN2[RUN npm build]
    end
    
    subgraph "Stage 2: Runtime"
        FROM2[FROM node:20-alpine]
        WORK2[WORKDIR /app]
        COPY3[COPY --from=build]
        USER[USER node]
        CMD[CMD node dist/server.js]
    end
    
    FROM1 --> WORK1 --> COPY1 --> RUN1 --> COPY2 --> RUN2
    FROM2 --> WORK2 --> COPY3 --> USER --> CMD
    RUN2 -.artifacts.-> COPY3
    
    style FROM1 fill:#FFD700
    style FROM2 fill:#90EE90
```

**Key instructions:**

| Instruction | Purpose |
|-------------|---------|
| `FROM` | Base image (use `AS <name>` for multi-stage builds) |
| `WORKDIR` | Set working directory; preferred over `RUN cd` |
| `COPY` / `ADD` | Add files into the image (prefer `COPY`; `ADD` also handles URLs/tar) |
| `RUN` | Execute a command at build time |
| `ENV` / `ARG` | Runtime env vars / build-time args |
| `EXPOSE` | Document the port (does not publish it) |
| `USER` | Drop root before `CMD` runs |
| `ENTRYPOINT` / `CMD` | Default command and its arguments |
| `HEALTHCHECK` | Container-level liveness probe |

**BuildKit features** (default since Docker 23):

- Parallel stage execution
- Cache mounts (`--mount=type=cache`)
- Secret mounts (`--mount=type=secret`)
- Multi-platform builds via `docker buildx`
- Remote cache export/import

---

## Docker Compose

Declarative spec for multi-container apps on a single host.

```yaml
services:
  web:
    build: .
    ports: ["3000:3000"]
    environment:
      DATABASE_URL: postgres://app@db:5432/app
    depends_on:
      db:
        condition: service_healthy
  db:
    image: postgres:16
    volumes: [pgdata:/var/lib/postgresql/data]
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U app"]
      interval: 5s
volumes:
  pgdata:
```

### Compose Architecture

```mermaid
graph TB
    ComposeFile[compose.yaml]
    ComposeCLI[docker compose CLI]
    
    subgraph "Docker Engine"
        Network[Custom Bridge Network]
        
        subgraph "Service: web"
            WebContainer1[web_1]
            WebContainer2[web_2]
        end
        
        subgraph "Service: db"
            DBContainer[db_1]
        end
        
        Volume[Named Volume: pgdata]
    end
    
    ComposeFile --> ComposeCLI
    ComposeCLI --> Network
    ComposeCLI --> WebContainer1
    ComposeCLI --> WebContainer2
    ComposeCLI --> DBContainer
    ComposeCLI --> Volume
    
    WebContainer1 -.-> Network
    WebContainer2 -.-> Network
    DBContainer -.-> Network
    DBContainer -.-> Volume
    
    style ComposeFile fill:#4A90E2,color:#fff
    style Network fill:#FFD700
    style Volume fill:#90EE90
```

| Verb | What it does |
|------|--------------|
| `compose up` | Build + create + start all services |
| `compose down` | Stop + remove containers, networks (volumes optional) |
| `compose logs -f` | Follow logs of all services |
| `compose ps` | Show service state |
| `compose exec <svc>` | Shell into a running service |
| `compose --profile <p>` | Activate a profile-tagged subset of services |

---

## Images and Registries

### Image Distribution Flow

```mermaid
sequenceDiagram
    participant Dev as Developer
    participant Local as Local Docker
    participant Registry as Container Registry
    participant Prod as Production
    
    Dev->>Local: docker build -t myapp:1.0 .
    Local->>Local: Create image layers
    Dev->>Local: docker tag myapp:1.0 registry/myapp:1.0
    Dev->>Registry: docker push registry/myapp:1.0
    Registry->>Registry: Store layers (deduplicated)
    Prod->>Registry: docker pull registry/myapp:1.0
    Registry->>Prod: Download only missing layers
    Prod->>Prod: docker run registry/myapp:1.0
```

- **Layered storage** — each instruction is a layer; layers are content-addressed
  and shared across images, so pulls are deduplicated.
- **Tags** — mutable pointers (`myapp:1.2.3`, `myapp:latest`); also content
  digests (`myapp@sha256:…`) for immutable references.
- **Registries** — Docker Hub (default), GitHub Container Registry (`ghcr.io`),
  Amazon ECR, Google Artifact Registry, Azure Container Registry, Harbor (self-hosted).
- **Authentication** — `docker login`; in CI prefer short-lived OIDC tokens
  (GitHub `id-token`, AWS `aws-actions/configure-aws-credentials`).

---

## Networking

### Network Drivers Comparison

```mermaid
graph TB
    subgraph "Bridge Network (default)"
        B1[Container A<br/>172.17.0.2]
        B2[Container B<br/>172.17.0.3]
        Bridge[docker0]
        B1 --- Bridge
        B2 --- Bridge
    end
    
    subgraph "Host Network"
        H1[Container C<br/>shares host IPs]
        HostNS[Host Network Namespace]
        H1 -.-> HostNS
    end
    
    subgraph "Overlay Network (Swarm)"
        O1[Container D<br/>Node 1]
        O2[Container E<br/>Node 2]
        VXLAN[VXLAN Tunnel]
        O1 -.-> VXLAN
        O2 -.-> VXLAN
    end
    
    style Bridge fill:#FFD700
    style HostNS fill:#FF6B6B
    style VXLAN fill:#4ECDC4
```

| Driver | Use case |
|--------|----------|
| `bridge` | Default, single-host private network |
| `host` | Container shares the host's network namespace |
| `overlay` | Multi-host network (Swarm or external orchestrator) |
| `macvlan` | Container gets its own MAC on the physical network |
| `none` | No networking; only loopback |

Containers on the same custom bridge can resolve each other by name (built-in DNS).

---

## Storage

### Volume Types Comparison

```mermaid
graph TB
    subgraph "Named Volume"
        V1[/var/lib/docker/volumes/mydata]
        C1[Container] -.mount.-> V1
    end
    
    subgraph "Bind Mount"
        H1[/home/user/project]
        C2[Container] -.mount.-> H1
    end
    
    subgraph "tmpfs Mount"
        M1[Memory]
        C3[Container] -.mount.-> M1
    end
    
    style V1 fill:#90EE90
    style H1 fill:#FFD700
    style M1 fill:#FF6B6B
```

| Mount type | Use case |
|------------|----------|
| **Volume** | Docker-managed storage (preferred for persistent data) |
| **Bind mount** | Map a host path into the container (best for dev) |
| **tmpfs** | In-memory only; nothing persisted |

Volumes survive container removal; bind mounts depend on host filesystem.

---

## Security

### Security Layers

```mermaid
graph TB
    subgraph "Container Security"
        User[Non-root User<br/>USER node]
        ReadOnly[Read-only Rootfs<br/>--read-only]
        Caps[Dropped Capabilities<br/>--cap-drop ALL]
        Seccomp[Seccomp Profile]
        AppArmor[AppArmor/SELinux]
        Scan[Image Scanning<br/>Trivy, Grype]
    end
    
    subgraph "Runtime Security"
        Namespaces[Kernel Namespaces]
        Cgroups[Resource Limits]
        Rootless[Rootless Docker]
    end
    
    User --> ReadOnly
    ReadOnly --> Caps
    Caps --> Seccomp
    Seccomp --> AppArmor
    AppArmor --> Scan
    
    Namespaces --> Cgroups
    Cgroups --> Rootless
    
    style User fill:#90EE90
    style Scan fill:#4ECDC4
    style Rootless fill:#FFD700
```

- **Run as non-root** — `USER` in Dockerfile or `--user` at runtime
- **Drop capabilities** — `--cap-drop=ALL --cap-add=NET_BIND_SERVICE`
- **Read-only root filesystem** — `--read-only` plus tmpfs for writable areas
- **Seccomp and AppArmor profiles** — default profiles ship with Docker
- **Rootless mode** — run the daemon and containers as a non-root user
  (avoids the `docker` group = root tradeoff)
- **Image scanning** — `docker scout`, Trivy, Grype in CI
- **Distroless / minimal bases** — fewer packages, fewer CVEs

---

## Common Patterns

| Pattern | Description |
|---------|-------------|
| **Multi-stage build** | One stage builds, the next stage copies only artifacts — small final image |
| **Build once, deploy many** | Same digest goes through staging → prod; tags only point, never rebuild |
| **Sidecar** | Companion container shares network/volume with the main one |
| **Init container** | Run a setup task before the main container starts |
| **`.dockerignore`** | Exclude `.git`, `node_modules`, secrets from build context |
| **Reproducible tags** | Pin base image by digest (`FROM node:20-alpine@sha256:…`) |

---

## Limitations

- **Linux-only kernel sharing** — on Windows and macOS, Docker Desktop runs a Linux VM
- **Daemon as single point of failure** — restart kills all running containers (mitigated by `--restart` policies and `live-restore`)
- **Root daemon** — historical security concern; rootless mode addresses it but with caveats
- **Licensing** — Docker Desktop requires a paid subscription for larger organisations (since 2021)
- **Single-host scope** — Compose handles one machine; production scale needs an orchestrator

---

## Related

- [Containers & Orchestration](index.md) — overview
- [Podman](podman.md) — daemonless, rootless alternative
- [containerd](containerd.md) — Docker's underlying runtime
- [Kubernetes](kubernetes.md) — multi-host orchestration
- [Helm](helm.md) — packaging for Kubernetes
- [Solomon Hykes](../../authors/solomon-hykes.md) — Docker creator
- [CI/CD Providers](../process/ci-cd/index.md) — almost all use Docker as a runner