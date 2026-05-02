# Диаграмма эффектов: пример построения

## Overview

A detailed walkthrough of creating an **Effect Diagram** for a real commercial project (X5 Group service for updating Pyaterochka store information in Yandex Maps and 2GIS).

## Key Points

### What Tech Leads Struggle With

1. How to estimate task effort?
2. How to ensure low coupling?
3. What order to implement components for parallel work?
4. What actually needs to be done?

### Observable Behaviour

The identity of an information system is defined by how it interacts with the external world:
- What, where, when it stores and requests
- What, where, when it sends

This is called **observable behaviour**. End users judge correctness based on observable behaviour — even for complex calculations, the display mechanism must work correctly first.

### Effect Diagrams

Zhidkov invented Effect Diagrams as a tool for:
- Designing and visualising observable behaviour
- Answering the key questions above
- Planning implementation order for parallel work

### The Project Example

The article details the process of creating an Effect Diagram for a small service handling information updates for tens of thousands of Pyaterochka stores across Russia.

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [Architecture & Modularity](../../topics/architecture/index.md)
- [Ergonomic Approach](https://ergowiki.azhidkov.pro/docs/effect-diagram/)
