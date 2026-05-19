# Hello World in C#

## Overview

A simple Hello World program in C# using top-level statements (C# 9.0+).

## Code

```csharp
Console.WriteLine("Hello, World!");
Console.WriteLine($"Today is {DateTime.Now:yyyy-MM-dd}");
```

## How to Run

```bash
dotnet run
```

## Key Concepts

- **Top-level statements** — no `class` or `Main()` needed (C# 9.0+)
- `Console.WriteLine()` — Print to standard output
- String interpolation (`$"..."`) — embed expressions in strings (C# 6.0)
- Static typing with type inference

## Historical Context

C# was created by Anders Hejlsberg at Microsoft, first released in 2002 as part of the .NET Framework. Key influences:
- Java/C++ syntax
- Delphi (Hejlsberg's previous language) — properties, events
- Managed memory with GC

For more on C#, see [docs/languages/csharp/](../../../docs/languages/csharp/)
