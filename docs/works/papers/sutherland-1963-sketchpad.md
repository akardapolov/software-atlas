# Sketchpad: A Man-Machine Graphical Communication System

| | |
|---|---|
| **Author** | Ivan Sutherland |
| **Year** | 1963 |
| **Publication** | PhD Thesis, MIT |
| **Topic(s)** | Computer graphics, HCI, object-like concepts |
| **PDF** | [Local copy](../../../library/open-access-papers/sutherland-1963-sketchpad.pdf) |

## Summary

Sutherland's PhD thesis introduced **Sketchpad**, the first interactive computer graphics system that enabled users to create and manipulate graphical objects directly on screen. More importantly for software development, Sketchpad introduced the **master/instance concept** that directly influenced Alan Kay's vision of object-oriented programming.

## Key Ideas

### Master and Instance

Sketchpad's innovation was the **master drawing** concept:

- **Master drawing** — a template or prototype
- **Instance** — a copy of the master that can be modified independently
- **Inheritance-like relationship** — instances inherit from master

This was one of the first manifestations of what would become **classes and objects** in OOP.

### Constraints

Sketchpad introduced **constraints** — objects maintain their own rules:

```
circle constrained to be tangent to line
line constrained to be horizontal
```

When user moves one object, related objects adjust to satisfy constraints. This foreshadowed:
- **Encapsulation** — objects maintain their own invariants
- **Reactive systems** — automatic updates through relationships

### Direct Manipulation

Sketchpad pioneered **direct manipulation interfaces**:

- User works with objects directly (light pen, screen)
- No intermediate command language
- **What you see is what you get** editing model

This directly influenced:
- **GUI systems** — Xerox PARC, Apple Lisa/Mac, Windows
- **Drawing programs** — Paint, CAD systems
- **Touch interfaces** — modern tablet and phone interactions

## Historical Significance

### Foundation for OOP

Alan Kay saw Sketchpad as a graduate student at Utah and recognized:

- **Objects are natural for graphics** — graphical entities have state and behavior
- **Master/instance = class/object** — template and instance relationship
- **Constraints = encapsulation** — objects maintain their own rules

This was one of the **three key influences** on Kay's Smalltalk (along with Simula and Lisp).

### Computer Graphics Pioneer

Sketchpad was groundbreaking for graphics:
- **Real-time interaction** — instant feedback, not batch processing
- **Vector graphics** — mathematically defined shapes, not bitmaps
- **Recursive display structures** — hierarchical scene representation

## Legacy

Sketchpad's concepts became foundational:

- **OOP class/instance model** — Kay's Smalltalk design
- **GUI principles** — direct manipulation, live editing
- **CAD industry** — all modern drawing and design tools

## Connections

- **Influenced:** Alan Kay (Smalltalk), GUI industry, CAD systems
- **Related topic:** [HCI](../../topics/hci/) · [Graphics](../../topics/graphics/) (if exists)
- **Author:** [Ivan Sutherland](../../authors/ivan-sutherland.md)

## Further Reading

- [Sketchpad thesis](https://www.cl.cam.ac.uk/techreports/UCAM-CL-TR-574.pdf)
- [ACM Turing Award: Ivan Sutherland](https://amturing.acm.org/award_winners/sutherland_7350186.cfm)
