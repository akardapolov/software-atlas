#!/bin/bash
#
# Generate stub pages for all entries in mkdocs.yml nav.
# Safe to run multiple times — does NOT overwrite existing files.
#

set -euo pipefail

DOCS_DIR="$(cd "$(dirname "$0")/../docs" && pwd)"

created=0
skipped=0

# Helper: create file only if it does not exist
create_stub() {
    local filepath="$1"
    local content="$2"

    local full_path="$DOCS_DIR/$filepath"
    local dir
    dir=$(dirname "$full_path")

    mkdir -p "$dir"

    if [[ -f "$full_path" ]]; then
        skipped=$((skipped + 1))
        return
    fi

    echo "$content" > "$full_path"
    echo "  ✅ Created: $filepath"
    created=$((created + 1))
}

echo "Software Atlas — Stub Generator"
echo "================================"
echo ""

# ── Maps ──────────────────────────────────────────────

create_stub "maps/master-timeline.md" "# Master Timeline

A comprehensive chronological view of key milestones in software development.

!!! note \"Work in progress\"
    This page is under construction. See the [main README](../index.md) for a preview timeline.
"

create_stub "maps/ideas-evolution.md" "# Ideas Evolution Map

Visual diagram showing how ideas influenced each other across decades.

!!! note \"Work in progress\"
    This page is under construction.
"

create_stub "maps/paradigms-map.md" "# Paradigms Map

How programming paradigms relate and evolved from each other.

!!! note \"Work in progress\"
    This page is under construction.
"

create_stub "maps/architecture-map.md" "# Architecture Map

Evolution of software architecture styles and patterns.

!!! note \"Work in progress\"
    This page is under construction.
"

create_stub "maps/languages-genealogy.md" "# Languages Genealogy

Family tree of programming languages showing lineage and influences.

!!! note \"Work in progress\"
    This page is under construction.
"

create_stub "maps/process-map.md" "# Process Map

Evolution of software development processes and methodologies.

!!! note \"Work in progress\"
    This page is under construction.
"

# ── Topics (sub-pages) ───────────────────────────────

for topic in paradigms architecture design types functional concurrency process distributed; do
    title=$(echo "$topic" | sed 's/./\U&/')
    create_stub "topics/$topic/index.md" "# $title

!!! note \"Work in progress\"
    This topic page is under construction.

## Overview

_Content coming soon._

## Key concepts

## Key authors

## Key works

## Related topics
"
done

# ── Languages ─────────────────────────────────────────

declare -A LANG_META
LANG_META[lisp]="Lisp|1958|John McCarthy|Functional, symbolic|Dynamic"
LANG_META[simula]="Simula|1967|Ole-Johan Dahl, Kristen Nygaard|OOP|Static"
LANG_META[smalltalk]="Smalltalk|1972|Alan Kay et al.|OOP, message passing|Dynamic"
LANG_META[c]="C|1972|Dennis Ritchie|Imperative, procedural|Static, weak"
LANG_META[ml]="ML|1978|Robin Milner|Functional|Static, inferred"
LANG_META[erlang]="Erlang|1986|Joe Armstrong|Functional, concurrent|Dynamic"
LANG_META[haskell]="Haskell|1990|Committee|Functional, pure|Static, inferred"
LANG_META[python]="Python|1991|Guido van Rossum|Multi-paradigm|Dynamic, strong"
LANG_META[java]="Java|1995|James Gosling|OOP, imperative|Static, nominal"
LANG_META[clojure]="Clojure|2007|Rich Hickey|Functional, Lisp|Dynamic, strong"
LANG_META[go]="Go|2009|Pike, Thompson, Griesemer|Imperative, CSP|Static, structural"
LANG_META[rust]="Rust|2010|Graydon Hoare|Multi-paradigm|Static, affine"
LANG_META[typescript]="TypeScript|2012|Anders Hejlsberg|Multi-paradigm|Static, structural"

for lang in "${!LANG_META[@]}"; do
    IFS='|' read -r name year creator paradigm typing <<< "${LANG_META[$lang]}"
    create_stub "languages/$lang/index.md" "---
name: $name
year: $year
creator: $creator
paradigm: $paradigm
typing: $typing
---

# $name

| | |
|---|---|
| **Year** | $year |
| **Creator(s)** | $creator |
| **Paradigm(s)** | $paradigm |
| **Typing** | $typing |

## Overview

_Content coming soon._

## Key ideas

## Historical context

## Influence

## Code examples

See [examples/$lang/](../../examples/$lang/) for runnable code.
"
done

# ── Reading paths ─────────────────────────────────────

create_stub "reading-paths/architecture-path.md" "# 🏗 Architecture Reading Path

A guided sequence from structured programming to modern architecture.

!!! note \"Work in progress\"
    This reading path is under construction.

## Sequence

1. Dijkstra — Go To Considered Harmful (1968)
2. Parnas — Information Hiding (1972)
3. Brooks — The Mythical Man-Month (1975)
4. Shaw & Garlan — Software Architecture (1996)
5. Cockburn — Hexagonal Architecture (2005)
6. Evans — Domain-Driven Design (2003)
7. Newman — Building Microservices (2015)
8. Skelton & Pais — Team Topologies (2019)
"

create_stub "reading-paths/oop-and-design-path.md" "# 🧩 OOP & Design Reading Path

From Simula to modern design principles.

!!! note \"Work in progress\"
    This reading path is under construction.

## Sequence

1. Dahl & Nygaard — Simula (1967)
2. Alan Kay — Smalltalk (1972)
3. GoF — Design Patterns (1994)
4. Liskov & Wing — Behavioral Subtyping (1994)
5. Fowler — Refactoring (1999)
6. R.C. Martin — SOLID (2003)
"

create_stub "reading-paths/fp-path.md" "# λ Functional Programming Reading Path

From lambda calculus to modern typed FP.

!!! note \"Work in progress\"
    This reading path is under construction.

## Sequence

1. Church — Lambda Calculus (1936)
2. McCarthy — Lisp (1958)
3. Backus — Can Programming Be Liberated? (1978)
4. Milner — ML (1978)
5. Hughes — Why Functional Programming Matters (1989)
6. Hickey — Simple Made Easy (2011)
7. Wlaschin — Domain Modeling Made Functional (2018)
"

create_stub "reading-paths/testing-and-delivery-path.md" "# 🧪 Testing & Delivery Reading Path

From XP to continuous delivery and DORA metrics.

!!! note \"Work in progress\"
    This reading path is under construction.

## Sequence

1. Beck — Extreme Programming Explained (1999)
2. Beck — TDD by Example (2002)
3. Feathers — Working Effectively with Legacy Code (2004)
4. Humble & Farley — Continuous Delivery (2010)
5. Forsgren et al. — Accelerate (2018)
"

create_stub "reading-paths/distributed-systems-path.md" "# 🌐 Distributed Systems Reading Path

From logical clocks to modern data-intensive applications.

!!! note \"Work in progress\"
    This reading path is under construction.

## Sequence

1. Lamport — Time, Clocks, and the Ordering of Events (1978)
2. Gray — Transaction Processing (1981)
3. Brewer — CAP conjecture (2000)
4. Helland — Life beyond Distributed Transactions (2007)
5. Kleppmann — Designing Data-Intensive Applications (2017)
"

# ── Works sub-indexes ─────────────────────────────────

create_stub "works/papers/index.md" "# Papers

Key academic papers that shaped software development.

| Year | Author(s) | Title | Topic |
|------|-----------|-------|-------|
| 1936 | Church | Lambda calculus | FP foundations |
| 1968 | Dijkstra | Go To Considered Harmful | Structured programming |
| 1972 | Parnas | Decomposing Systems into Modules | Modularity |
| 1978 | Backus | Can Programming Be Liberated? | FP |
| 1978 | Hoare | CSP | Concurrency |
| 1978 | Lamport | Time, Clocks, and the Ordering of Events | Distributed |
| 1989 | Hughes | Why Functional Programming Matters | FP |
| 1994 | Liskov & Wing | A Behavioral Notion of Subtyping | Types |
| 2000 | Brewer | CAP conjecture | Distributed |
| 2007 | Helland | Life beyond Distributed Transactions | Distributed |
"

create_stub "works/books/index.md" "# Books

Foundational books in software development.

| Year | Author(s) | Title | Topic |
|------|-----------|-------|-------|
| 1975 | Brooks | The Mythical Man-Month | Architecture, process |
| 1994 | GoF | Design Patterns | Design |
| 1999 | Fowler | Refactoring | Design |
| 2002 | Beck | TDD by Example | Testing |
| 2003 | Evans | Domain-Driven Design | Architecture |
| 2004 | Feathers | Working Effectively with Legacy Code | Testing |
| 2010 | Humble & Farley | Continuous Delivery | Delivery |
| 2017 | Kleppmann | Designing Data-Intensive Applications | Distributed |
| 2018 | Wlaschin | Domain Modeling Made Functional | FP, DDD |
| 2018 | Ousterhout | A Philosophy of Software Design | Design |
"

create_stub "works/talks/index.md" "# Talks

Influential conference talks and presentations.

| Year | Author | Title | Topic |
|------|--------|-------|-------|
| 2011 | Hickey | Simple Made Easy | Design, FP |
| 2012 | Bernhardt | Boundaries | FP, architecture |
"

echo ""
echo "================================"
echo "Done! Created: $created, Skipped (already exist): $skipped"
