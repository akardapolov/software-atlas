# npm / pnpm / yarn

**Type:** Package manager + script runner for JavaScript / TypeScript
**Config file:** `package.json` in project root; lockfile per tool
**Docs:** https://docs.npmjs.com/ · https://pnpm.io/ · https://classic.yarnpkg.com/docs/

---

## Contents

- [Three Tools, One Ecosystem](#three-tools-one-ecosystem)
- [Key Concepts](#key-concepts)
- [Project Structure](#project-structure)
- [Dependencies and Lockfiles](#dependencies-and-lockfiles)
- [Scripts (npm as Build Tool)](#scripts-npm-as-build-tool)
- [Common Commands](#common-commands)
- [Where to Find Things](#where-to-find-things)
- [Code Examples](#code-examples)
- [Common Patterns](#common-patterns)
- [Limitations](#limitations)

---

## Three Tools, One Ecosystem

The Node ecosystem has three major package managers — all read
`package.json` and resolve from npm registry, but differ in how they
store and link `node_modules`.

| Tool | Year | `node_modules` layout | Lockfile | Strengths |
|------|------|----------------------|----------|-----------|
| **npm** | 2010 | Flat (hoisted) | `package-lock.json` | Default with Node, ubiquitous |
| **yarn classic** (1.x) | 2016 | Flat (hoisted) | `yarn.lock` | First with lockfile + workspaces; legacy |
| **yarn berry** (2+) | 2020 | Plug'n'Play (zipped, no `node_modules`) | `yarn.lock` | Fastest installs, strict resolution |
| **pnpm** | 2017 | Symlinked content-addressable store | `pnpm-lock.yaml` | Disk-efficient, strict, monorepo-friendly |

This page is mostly **npm**, with side notes on the two main alternatives.

---

## Key Concepts

| Term | Meaning |
|------|---------|
| **Package** | A directory with a `package.json` — every Node project, every published library |
| **`node_modules/`** | Where dependencies are installed |
| **Registry** | A server hosting tarballs (`npmjs.com` is the default; companies often run private mirrors) |
| **Scope** | A namespace prefix `@org/pkg`; private/scoped packages |
| **dependency / devDependency / peerDependency / optionalDependency** | Different roles in `package.json` |
| **Semver range** | `^1.2.3` (compatible), `~1.2.3` (patch only), `>=1.2.3 <2.0.0` etc. |
| **Lockfile** | Pins exact resolved versions for reproducibility |
| **Workspace** | A monorepo of multiple packages managed by one tool |
| **Script** | A command in `package.json`'s `"scripts"` block, run via `npm run <name>` |

---

## Project Structure

```text
my-app/
├── package.json
├── package-lock.json       # npm (or pnpm-lock.yaml / yarn.lock)
├── node_modules/           # installed deps (do NOT commit)
├── .npmrc                  # registry, auth, hoist settings
├── .gitignore
└── src/
    ├── index.js
    └── ...
```

Workspaces (monorepo):

```text
root/
├── package.json            # "workspaces": ["packages/*"]
├── package-lock.json
├── node_modules/
└── packages/
    ├── lib-a/
    │   ├── package.json
    │   └── src/
    └── app/
        ├── package.json
        └── src/
```

---

## Dependencies and Lockfiles

`package.json` declares dependencies with semver ranges:

```json
{
  "dependencies": {
    "react": "^18.2.0",
    "express": "~4.18.2"
  },
  "devDependencies": {
    "vitest": "^1.4.0",
    "typescript": "^5.4.0"
  },
  "peerDependencies": {
    "react": "^18.0.0 || ^19.0.0"
  }
}
```

| Field | Meaning |
|-------|---------|
| `dependencies` | Required at runtime |
| `devDependencies` | Only needed for development / build / test |
| `peerDependencies` | "If the consumer also installs me, they need a compatible version of X" — used by libraries (React plugins, ESLint plugins) |
| `optionalDependencies` | Install if possible; don't fail otherwise (native modules) |

The **lockfile** records the exact resolved tree:

```json
{
  "name": "my-app",
  "lockfileVersion": 3,
  "packages": {
    "node_modules/react": {
      "version": "18.2.0",
      "resolved": "https://registry.npmjs.org/react/-/react-18.2.0.tgz",
      "integrity": "sha512-..."
    }
  }
}
```

**Always commit the lockfile**. The lockfile guarantees that
`npm ci` / `pnpm install --frozen-lockfile` reproduce the same tree
on every machine.

---

## Scripts (npm as Build Tool)

`package.json` doubles as a task runner:

```json
{
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "test": "vitest",
    "lint": "eslint . --max-warnings 0",
    "format": "prettier --write .",
    "typecheck": "tsc --noEmit",
    "ci": "npm run lint && npm run typecheck && npm test && npm run build"
  }
}
```

```bash
npm run dev          # vite
npm run ci           # composite
npm test             # shorthand for npm run test
npm start            # shorthand for npm run start
```

Scripts inherit a `PATH` that prepends `node_modules/.bin/`, so
locally installed CLIs (`vite`, `eslint`, `tsc`) work without globals.

**Lifecycle hooks** (built-in script names): `preinstall`, `postinstall`,
`prepublish`, `prepare`. `npm install` runs `prepare` automatically —
useful for husky and pre-build steps.

---

## Common Commands

### npm

```bash
# Install everything from package.json + lockfile
npm install              # writes/updates lockfile
npm ci                   # CI mode: requires lockfile, no writes, faster

# Add / remove
npm install react
npm install --save-dev vitest
npm install --save-exact lodash@4.17.21
npm uninstall lodash

# Update within semver range
npm update
npm outdated             # show what could be updated

# Audit
npm audit
npm audit fix

# Run scripts
npm run build
npm test
npm start

# Publish
npm version patch        # bump version + git tag
npm publish              # publish to registry
npm publish --tag beta

# Workspaces
npm install --workspaces
npm run build --workspace=packages/lib-a
```

### pnpm (mostly drop-in compatible)

```bash
pnpm install
pnpm add react
pnpm add -D vitest
pnpm run build
pnpm -r run build           # recursive: all workspace packages
pnpm --filter app run dev
```

### yarn (berry)

```bash
yarn install
yarn add react
yarn add --dev vitest
yarn build              # scripts implicitly invokable
yarn workspaces foreach run build
```

---

## Where to Find Things

| What | Where |
|------|-------|
| Installed packages | `node_modules/` (or pnpm's `~/.local/share/pnpm/store/v3/`) |
| Locally installed binaries | `node_modules/.bin/` |
| Lockfile | `package-lock.json` (npm), `pnpm-lock.yaml` (pnpm), `yarn.lock` (yarn) |
| Cache | `~/.npm/_cacache/`, `~/.local/share/pnpm/`, `~/.yarn/cache/` |
| User config (registry, auth) | `~/.npmrc` (npm/pnpm), `~/.yarnrc.yml` (yarn berry) |
| Project config | `.npmrc` / `.yarnrc.yml` at repo root |
| Global packages | `npm root -g` (often `/usr/local/lib/node_modules/`); pnpm: `~/.local/share/pnpm/global/` |
| Auth tokens | `~/.npmrc`: `//registry.npmjs.org/:_authToken=...` |

---

## Code Examples

### Minimal app `package.json`

```json
{
  "name": "my-app",
  "version": "0.1.0",
  "private": true,
  "type": "module",
  "engines": {
    "node": ">=20.0.0"
  },
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview",
    "test": "vitest",
    "lint": "eslint .",
    "format": "prettier --write ."
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0"
  },
  "devDependencies": {
    "@types/react": "^18.2.0",
    "@vitejs/plugin-react": "^4.2.1",
    "eslint": "^8.57.0",
    "prettier": "^3.2.5",
    "typescript": "^5.4.0",
    "vite": "^5.2.0",
    "vitest": "^1.4.0"
  }
}
```

### Library `package.json`

```json
{
  "name": "@example/utils",
  "version": "1.2.0",
  "description": "Shared utilities",
  "license": "MIT",
  "main": "./dist/index.cjs",
  "module": "./dist/index.mjs",
  "types": "./dist/index.d.ts",
  "exports": {
    ".": {
      "types": "./dist/index.d.ts",
      "import": "./dist/index.mjs",
      "require": "./dist/index.cjs"
    }
  },
  "files": ["dist"],
  "scripts": {
    "build": "tsup src/index.ts --format esm,cjs --dts",
    "test": "vitest",
    "prepublishOnly": "npm run build"
  },
  "peerDependencies": {
    "react": "^18.0.0"
  },
  "devDependencies": {
    "tsup": "^8.0.2",
    "typescript": "^5.4.0",
    "vitest": "^1.4.0"
  }
}
```

### Workspaces

```json
// root package.json
{
  "name": "monorepo",
  "private": true,
  "workspaces": ["packages/*"],
  "scripts": {
    "build": "npm run build --workspaces",
    "test": "npm test --workspaces"
  }
}
```

For pnpm, use `pnpm-workspace.yaml`:

```yaml
packages:
  - "packages/*"
```

---

## Common Patterns

### Pin Node.js version

`.nvmrc`:

```text
20.11.1
```

`package.json`:

```json
{ "engines": { "node": ">=20.0.0" } }
```

CI typically reads `.nvmrc` (`actions/setup-node` on GitHub Actions).

### Use `npm ci` in CI

`npm install` may modify the lockfile if dependencies drift. In CI use:

```bash
npm ci             # fail if lockfile out of sync; reproducible
```

### `.npmrc` for private registries

```ini
# Project .npmrc
@example:registry=https://npm.example.com/
//npm.example.com/:_authToken=${NPM_TOKEN}
save-exact=true
```

### Exact versions for libraries

Apps can use semver ranges (`^1.2.3`) freely — the lockfile pins them.
Published libraries should usually be **strict** about peer dep ranges
to avoid version skew.

### Husky + lint-staged on commit

```json
{
  "scripts": { "prepare": "husky install" },
  "devDependencies": {
    "husky": "^9.0.0",
    "lint-staged": "^15.2.0"
  },
  "lint-staged": {
    "*.{ts,tsx}": ["eslint --fix", "prettier --write"]
  }
}
```

---

## Limitations

- **`node_modules/` is huge** — flat hoisting duplicates dependencies;
  pnpm's content-addressable store and yarn berry's PnP avoid this
- **Phantom dependencies** — flat layout lets you accidentally `import`
  something not declared in `package.json`. pnpm and yarn PnP block this
- **Postinstall scripts run arbitrary code** — security risk; use
  `--ignore-scripts` for untrusted deps
- **Lockfile churn between tools** — switching from npm → pnpm requires
  migrating; lockfiles aren't compatible
- **`npm install` is non-deterministic** by default; use `npm ci`
  for CI/reproducible builds
- **No first-class incrementality** — npm doesn't itself cache build
  outputs; that's the job of bundlers (Vite, Webpack, esbuild) or
  task runners (Turborepo, Nx)
- **Registry availability** — outages of `npmjs.com` block builds
  unless you run a private mirror

---

## Related

- [Cargo](cargo.md) — Rust's package manager has a much cleaner model
  (no flat hoisting, lockfile by default, exact builds)
- [Build Systems Overview](index.md) — comparison and core concepts
- [CI/CD Providers](../ci-cd/index.md) — `npm ci` is the canonical CI
  install command; cache `~/.npm` for speed
- [TypeScript Examples](../../../../examples/typescript/) — npm in
  practice
</content>
