# Simple Made Easy

| | |
|---|---|
| **Speaker** | Rich Hickey |
| **Year** | 2011 |
| **Event** | Strange Loop |
| **Topic(s)** | Design, Simplicity, Complexity, FP |
| **Video** | [Watch on YouTube](https://www.youtube.com/watch?v=SxdOUGdseq4) |

## Summary

In this iconic talk, Clojure creator Rich Hickey draws a clear distinction between the concepts of **simple** (простой) and **easy** (лёгкий). This distinction, in his opinion, is the key to creating quality software.

**Simple** (from Latin *simplex* — one fold/weaving) means "unmixed, unbraided" — a system whose components are not intertwined with each other. **Easy** (from Latin *adjacere* — to lie near) means "close to our current understanding or skills."

Hickey argues that the industry focuses too much on "easy" (what's quick to learn) at the expense of "simple" (what doesn't create complex interdependencies). This leads to systems that are easy to start but impossible to maintain.

## Key Ideas

1. **Simple ≠ Easy** — simplicity (simple) is an objective characteristic of a system (lack of coupling). Ease (easy) is a subjective characteristic (closeness to the developer's experience). We confuse them, choosing "easy" and getting "complex."

2. **Complect = Intertwine** — Hickey introduces the term "complect" (to braid) to describe the process of creating unnecessary interdependencies. State complects value with time. ORM complects data with objects. Each "intertwining" increases cognitive load.

3. **Simplicity is a Choice** — simplicity requires work and discipline. It's easier (easy) to take a ready-made framework, but this can lead to a tangled (complex) system. You need to consciously avoid intertwining.

4. **Benefits are Delayed** — the advantages of simplicity don't appear immediately but after months and years of maintenance. The advantages of "easy" are visible immediately but cost dearly later.

## Memorable Quotes

> "Simplicity is a prerequisite for reliability."
> — Edsger Dijkstra (cited by Hickey)

> "Easy is relative. Simple is objective."

> "If you want everything to be familiar, you will never learn anything new."

> "Programmers know the benefits of everything and the tradeoffs of nothing."

## Connections

- **Speaker:** [Rich Hickey](../../authors/rich-hickey.md)
- **Related talk:** [Hickey — Hammock Driven Development](../talks/hickey-hammock.md)
- **Related topic:** [Functional Programming](../../topics/paradigms/functional/index.md)
- **Related language:** [Clojure](../../languages/clojure.md)

## Further Reading

- [Transcript](https://github.com/matthiasn/talk-transcripts/blob/master/Hickey_Rich/SimpleMadeEasy.md)
- [Slides](https://www.slideshare.net/evaboroday/simple-made-easy)

## Personal Notes

_This talk changed my approach to design. Now every time I hear "let's take this framework, it integrates easily," I ask: "Is it simple? What does it intertwine?" The idea that simple and easy are different axes should be part of a programmer's basic education._
