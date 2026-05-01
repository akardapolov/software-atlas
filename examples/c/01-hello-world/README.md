# Hello World in C

## Overview

A simple Hello World program in C, one of the most influential programming languages.

## Code

```c
#include <stdio.h>

int main() {
    printf("Hello, World!\n");
    return 0;
}
```

## How to Run

```bash
# Compile
gcc main.c -o hello

# Run
./hello
```

## Key Concepts

- `#include <stdio.h>` - Include the standard input/output library
- `int main()` - Entry point of the program, returns an integer
- `printf()` - Function to print formatted output
- `return 0` - Return 0 to indicate successful execution

## Historical Context

C was created by Dennis Ritchie at Bell Labs in 1972. It became the foundation for:
- Unix operating system
- Many later languages (C++, Java, C#, Go)
- Systems programming and embedded systems

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
