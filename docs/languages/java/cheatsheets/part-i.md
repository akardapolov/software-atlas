# Java Cheat Sheet — Part I

Foundations of the language: compiling and running programs, variables
and types, operators, strings, conditionals, loops, arrays, methods,
the basics of OOP, and console input/output.

---

## Contents

1. [Compilation and Running](#compilation-and-running)
2. [Variables and Types](#variables-and-types)
3. [Operators](#operators)
4. [Strings](#strings)
5. [Conditionals](#conditionals)
6. [Loops](#loops)
7. [Arrays](#arrays)
8. [Methods](#methods)
9. [OOP Basics](#oop-basics)
10. [Input and Output](#input-and-output)

---

## Compilation and Running

> **Key constructs:** `javac`, `java`, JVM, JDK, JRE, `main()`, classpath, `.class`, `.java`

### `javac`

`javac` is the Java compiler. It reads a source file with the `.java`
extension, checks the syntax, and converts the code into Java bytecode —
a `.class` file. If the program contains a syntax or type error,
compilation stops with an error message.

**Practice:** create `Main.java`, compile it with `javac Main.java`, and
find the resulting `Main.class` file. Then deliberately introduce an
error in the code and examine the compiler message.

### `java`

`java` is the command that runs a Java program through the JVM. It takes
the name of the compiled class without the `.class` extension:
`java Main`. The JVM locates the class bytecode and starts execution
from the `main` method.

**Practice:** compile and run a program that prints a message to the
console. Try running it with a wrong class name and discuss the JVM
message.

### JVM / JDK / JRE

The JVM (Java Virtual Machine) is the virtual machine that executes
`.class` bytecode. The JDK (Java Development Kit) contains the
development tools: the `javac` compiler, the JVM, the standard
libraries, and other utilities. The JRE is the Java Runtime
Environment; in modern Java versions it is not usually installed
separately — the JDK is used instead.

**Practice:** run `java -version` and `javac -version` in a terminal.
Check that both the compiler version and the JVM version are available
on the system.

### `main()`

The `main` method is the entry point of a regular console Java program.
The JVM starts execution with the method whose signature is
`public static void main(String[] args)`. The `args` parameter holds
the arguments passed to the program from the command line.

**Practice:** write a program that prints `Hello, Java!`. Then print all
values from the `args` array by running the program with arguments:
`java Main Anna 20`.

### `classpath`

The classpath is the set of folders and libraries where Java looks for
classes. By default the JVM searches the current folder, but the path
can be set explicitly with the `-cp` or `-classpath` option. This
matters when classes live in different directories or external
libraries are used.

**Practice:** create a class in a separate folder and try to run the
program with an explicit classpath: `java -cp out Main`. Briefly show
what happens when the JVM cannot find the class.

---

## Variables and Types

> **Key constructs:** `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`, `String`, `var`, `final`, type casting, `(int)`, `(double)`

### `byte`, `short`, `int`, `long`

These are the integer types: they store numbers without a fractional
part. They differ in their allowed range and memory footprint:
`byte` — 1 byte, `short` — 2, `int` — 4, `long` — 8. Most study tasks
use `int`, and `long` for large numbers.

**Practice:** declare variables of all four types and print their
values. Demonstrate overflow: assign a very large value to an `int` and
compare that with using `long`.

### `float`, `double`

`float` and `double` are used for fractional numbers. `double` stores
values more precisely and is usually the default choice. A `float`
literal must be written with the `f` suffix: `3.14f`.

**Practice:** compute the average of three grades or temperatures.
Compare the results of `5 / 2` and `5.0 / 2`.

### `char`

`char` stores a single Unicode character: a letter, digit, sign, or
other symbol. A character is written in single quotes: `'A'`, `'7'`,
`'?'`. The `char` type can be compared with other characters and used
in conditions.

**Practice:** read a character or assign it to a variable. Check whether
it is a digit using the condition `symbol >= '0' && symbol <= '9'`.

### `boolean`

`boolean` is the logical data type. It can take only two values: `true`
or `false`. It is most often used in conditions, loops, and check
methods.

**Practice:** create an `isAdult` variable that shows whether the user
is 18 or older. Print different messages depending on its value.

### `String`

`String` is the type for storing text: words, sentences, addresses,
names, and other strings. Strings are written in double quotes:
`"Java"`. Unlike the primitive types, `String` is a reference type.

**Practice:** create the variables `name`, `city`, and `message`.
Assemble a greeting string and print it to the console.

### `var`

`var` lets you omit the explicit variable type — the compiler infers it
from the value on the right. For example, in `var age = 20;` the
inferred type is `int`. `var` can only be used for local variables that
have an initializer right away.

**Practice:** rewrite several declarations with `int`, `String`, and
`double` using `var`. Discuss why you cannot write just `var value;`.

### `final`

`final` makes a variable immutable after the first assignment. This is
useful for constants and values that must not change accidentally. By
convention, constant names are often written in uppercase: `MAX_SIZE`,
`PI`.

**Practice:** declare `final double PI = 3.14159;`. Try to change `PI`
and look at the compilation error.

### Type Casting: `(int)`, `(double)`

Type casting converts a value from one type to another. For example,
`(double) a / b` produces a fractional result when dividing two
integers. Converting from a wider type to a narrower one may lose data.

**Practice:** compute the average of two `int` numbers first without
casting, then with `(double)`. Show that `(int) 5.9` gives `5`, not `6`.

---

## Operators

> **Key constructs:** `+`, `-`, `*`, `/`, `%`, `==`, `!=`, `>`, `<`, `>=`, `<=`, `&&`, `||`, `!`, `?:`, `++`, `--`, `+=`, `-=`, `*=`, `/=`

### Arithmetic Operators: `+`, `-`, `*`, `/`, `%`

Arithmetic operators perform the basic operations on numbers: addition,
subtraction, multiplication, division, and remainder. The `%` operator
is especially useful for checking evenness, extracting the last digit of
a number, and cyclic tasks. The `+` operator is also used to concatenate
strings.

**Practice:** write a program that reads a number and prints its last
digit using `% 10`. Additionally check whether the number is even.

### Comparison Operators: `==`, `!=`, `>`, `<`, `>=`, `<=`

Comparison operators compare two values and return `true` or `false`.
They are used inside `if`, `while`, `for`, and other constructs. For
primitives, `==` compares the values themselves, while strings must be
compared with `.equals()`.

**Practice:** read two numbers and print which one is greater. If the
numbers are equal, print a separate message.

### Logical Operators: `&&`, `||`, `!`

The `&&` operator means "and": both conditions must be true. The `||`
operator means "or": at least one condition must be true. The `!`
operator negates a boolean value.

**Practice:** check whether a number is in the range from 10 to 100
using `&&`. Check whether a character is a vowel using several
conditions with `||`.

### Ternary Operator: `?:`

The ternary operator is a short form of `if-else` that returns one of
two values. Its form is `condition ? valueIfTrue : valueIfFalse`. It is
convenient for simple assignments or choosing a short message.

**Practice:** determine whether a number is even and store the string
`"even"` or `"odd"` in a variable using `?:`.

### Increment and Decrement: `++`, `--`

`++` increases a variable's value by one, and `--` decreases it by one.
They come in prefix (`++x`) and postfix (`x++`) forms: the difference
matters when the operator is used inside an expression. In an ordinary
loop, `i++` is more common.

**Practice:** print the results of expressions with `x++` and `++x`.
Then use `i++` in a `for` loop.

### Compound Assignment: `+=`, `-=`, `*=`, `/=`

These operators shorten operations on a variable. For example,
`sum += number` means `sum = sum + number`. They are often used to
accumulate sums, count items, and update counters.

**Practice:** create an array of numbers and compute the sum of its
elements using `sum += value`. Separately count the positive numbers
with `count += 1`.

---

## Strings

> **Key constructs:** `String`, `length()`, `charAt()`, `substring()`, `indexOf()`, `equals()`, `equalsIgnoreCase()`, `compareTo()`, `trim()`, `toUpperCase()`, `toLowerCase()`, `replace()`, `split()`, concatenation `+`, `StringBuilder`, `append()`

### `String`

Strings in Java are immutable: once a `String` object is created, its
contents cannot be changed. Operations like concatenation, replacement,
or case changes create a new string. Keep this in mind when processing
text frequently in loops.

**Practice:** create a string, "modify" it with `toUpperCase()`, and
show that without assigning a new variable the original string stays
unchanged.

### `length()`

The `length()` method returns the number of characters in a string.
Character indexing starts at zero, so the last valid index is
`length() - 1`. The method is often used when traversing a string with
a loop.

**Practice:** read a word from the user and print its length. Check
whether the string is longer than five characters.

### `charAt()`

The `charAt(index)` method returns the character at the given index.
The first character has index `0`. Passing an index outside the string
bounds causes an exception.

**Practice:** print the first and the last character of an entered word.
Then loop over the string and print each character on a new line.

### `substring()`

The `substring()` method extracts part of a string. The variant
`substring(start, end)` takes characters from `start` inclusive to `end`
exclusive. The one-argument variant takes text from the given index to
the end of the string.

**Practice:** from the email address `student@mail.com`, extract the
username and the domain. Use `indexOf()` to find the position of the `@`
character.

### `indexOf()`

The `indexOf()` method returns the position of the first occurrence of a
character or substring. If the fragment is not found, it returns `-1`.
The method helps find delimiters, words, and characters inside text.

**Practice:** check whether an email contains the `@` character. Find
the first space in the user's full name.

### `equals()`

The `equals()` method compares the contents of two strings. The `==`
operator for strings compares object references, not text, so it must
not be used for user input. The comparison `"yes".equals(answer)` is
also safe when `answer` may be `null`.

**Practice:** read a password or a user command and compare it with the
expected value using `.equals()`. Show why the `==` variant may behave
unpredictably.

### `equalsIgnoreCase()`

`equalsIgnoreCase()` compares strings ignoring character case. For
example, `"java"` and `"JAVA"` are considered equal. This is convenient
for user commands, city names, and yes/no style answers.

**Practice:** read a `yes` / `no` answer from the user and accept the
variants `YES`, `Yes`, `yes`. Use `equalsIgnoreCase()`.

### `compareTo()`

The `compareTo()` method compares strings in lexicographic order. It
returns `0` if the strings are equal, a negative number if the first
string is "less", and a positive one if it is greater. The method is
used when sorting strings and objects.

**Practice:** compare two names and print which comes first
alphabetically. Then sort a small array of strings.

### `trim()`

The `trim()` method removes leading and trailing whitespace — all
characters with code `<= U+0020`, not only spaces. It is useful for
processing user input because users may accidentally add extra spaces.
Whitespace inside the string is preserved; for Unicode-aware trimming
prefer `strip()` (Java 11).

**Practice:** read a string with spaces at both ends, print its length
before and after `trim()`. Check whether the string is empty after
trimming. Try `strip()` on a string containing a non-breaking space.

### `toUpperCase()` and `toLowerCase()`

`toUpperCase()` converts a string to upper case, and `toLowerCase()` to
lower case. Both methods return a new string without changing the
original. They are often used to normalize data before comparison or
search.

**Practice:** read a city or a name and print it in upper case. Check
for a word in text case-insensitively via `toLowerCase()`.

### `replace()`

`replace()` replaces characters or text fragments with other values. It
returns a new string. Unlike regular-expression-based methods, plain
`replace()` performs simple text substitution.

**Practice:** replace all spaces in a string with the `_` character.
For example, turn `"Java Basic Course"` into `"Java_Basic_Course"`.

### `split()`

`split()` breaks a string into parts and returns an array of strings.
The delimiter is a regular expression, so some characters require
escaping. The method is often applied to CSV strings, full names, or
lists of values.

**Practice:** split the string `"Anna,20,Moscow"` by comma and print
each array element. Additionally split a full name by space.

### `StringBuilder` and `append()`

`StringBuilder` is designed for efficiently building long strings.
Unlike `String`, it is mutable: `append()` adds data to the same object.
This is especially useful when a string is assembled in a loop.

**Practice:** build a string of numbers from 1 to 10 first with `+`,
then with `StringBuilder`. Produce a string like `1, 2, 3, ..., 10`.

---

## Conditionals

> **Key constructs:** `if`, `else if`, `else`, `switch`, `case`, `default`, `break`, `->`, `yield`

### `if`, `else if`, `else`

`if` runs code when a condition is true. `else if` adds further checks,
and `else` runs when none of the previous conditions matched. These
constructs suit checking ranges, statuses, and different action
variants.

**Practice:** write a program that maps a score to a grade: for example,
`90–100` — excellent, `70–89` — good, and so on. Add a check for
invalid values.

### `switch`, `case`, `default`

`switch` selects one of the branches depending on the value of an
expression. Each branch is defined with `case`, and `default` runs when
no matching option is found. The construct is convenient when one
variable is compared against several fixed values.

**Practice:** print the weekday name for a number from 1 to 7. For an
unknown number, print a message via `default`.

### `break` in `switch`

In the classic `switch`, the `break` statement ends the current `case`.
Without it, execution "falls through" into the next branch.
Fall-through is sometimes used deliberately, but beginners usually need
`break`.

**Practice:** create a `switch` without `break` and see why several
lines are printed. Then add `break` to every `case`.

### `switch ->` (Arrow Syntax)

The arrow syntax `case value ->` is the modern form of `switch`. It
requires no `break` and does not allow accidental fall-through. This
variant is usually shorter and safer than the classic form.

**Practice:** rewrite the weekday task using `case 1 -> ...`. Compare
the code with the classic `switch`.

### `yield`

`yield` is used inside a modern `switch` block when a branch must return
a value. It allows several statements inside a `case` while still
producing the result of the whole switch expression. Available since
Java 14.

**Practice:** create a `switch` that returns the number of days in a
month. For February or another complex branch, use a block with
`yield`.

---

## Loops

> **Key constructs:** `for`, `while`, `do-while`, for-each (`for (:)`), `break`, `continue`, label (`label:`)

### `for`

The `for` loop is used when the number of repetitions is known or a
counter is needed. It consists of initialization, a continuation
condition, and a step: `for (int i = 0; i < 10; i++)`. It is often used
to traverse arrays by index.

**Practice:** print the numbers from 1 to 10. Then print the
multiplication table for a number entered by the user.

### `while`

`while` checks the condition before executing the loop body. If the
condition is false from the start, the body never runs. The loop suits
tasks where the number of repetitions is unknown in advance.

**Practice:** find the digit sum of a positive number: while
`number > 0`, take the last digit and divide the number by 10. Test it
on `12345`.

### `do-while`

`do-while` executes the loop body first and checks the condition
afterwards. Therefore the code inside such a loop runs at least once.
It is convenient for menus, repeated input, and validating user data.

**Practice:** implement number input that repeats until the user enters
a positive value. Use `do-while`.

### `for-each`

The `for-each` loop sequentially traverses the elements of an array or
collection. It needs no indexes: `for (int number : numbers)`. However,
it is inconvenient for changing array elements by index or tracking
element positions.

**Practice:** create an array of grades and print all its values with
`for-each`. Compute the sum and the average of the elements.

### `break`

`break` immediately terminates the nearest loop or `switch`. It is used
when the needed result is already found and continuing makes no sense —
for example, after finding the first matching array element.

**Practice:** find the first negative number in an array. Once found,
print its index or value and stop the loop with `break`.

### `continue`

`continue` skips the rest of the current iteration and moves to the next
one. It is useful when part of the data must be ignored — for example,
skipping negative numbers when summing positive ones.

**Practice:** walk over an array of numbers and print only the positive
ones. Use `continue` for negative values.

### Labels: `label:`

A label is a name placed before a loop or block that can be used with
`break` or `continue`. It allows exiting several nested loops at once.
Use labels rarely, only when they make the solution clearer.

**Practice:** create a two-dimensional array and find the first `0` in
it. After finding it, exit both nested loops via `break label`.

---

## Arrays

> **Key constructs:** `[]`, `new`, `length`, `Arrays.sort()`, `Arrays.toString()`, `Arrays.fill()`, multidimensional `[][]`

### Declaration: `[]`

An array stores a set of elements of one type and has a fixed size. The
element type is written before `[]`: `int[] numbers`. Indexing starts
at zero.

**Practice:** declare an array of five integers using `{1, 2, 3, 4, 5}`.
Print the first, last, and middle elements.

### Creation: `new`

The `new` operator creates a new array of a given size:
`new int[10]`. After creation, elements get default values: `0` for
numbers, `false` for boolean, `null` for objects. The array size cannot
change after creation.

**Practice:** create an array of 10 numbers. Fill it with values from 1
to 10 in a `for` loop.

### `length`

`length` is the array field holding its size. For arrays it is a field,
not a method, so there are no parentheses: `numbers.length`. Usually
`length` serves as the loop bound.

**Practice:** find the maximum value in an array, traversing it from `0`
to `array.length - 1`. Separately print the number of elements.

### `Arrays.sort()`

`Arrays.sort()` sorts an array in ascending order. For strings, the sort
is alphabetical. The method modifies the original array instead of
creating a new one.

**Practice:** create an array of random or predefined numbers, print it
before and after `Arrays.sort()`. Find the minimum and maximum element
after sorting.

### `Arrays.toString()`

`Arrays.toString()` converts a one-dimensional array into a readable
string like `[1, 2, 3]`. Without it, printing an array with
`System.out.println(array)` usually shows the object's internal
representation. Using it requires importing `java.util.Arrays`.

**Practice:** print the same array directly and via
`Arrays.toString()`. Discuss the difference in the output.

### `Arrays.fill()`

`Arrays.fill()` fills all array elements with one value. You can also
fill only part of the array by specifying an index range. The method is
useful for initial setup or resetting data.

**Practice:** create an array of 10 elements and fill it with `-1`. Then
fill only part of the array with zeros.

### Multidimensional Arrays: `[][]`

A two-dimensional array is an array of arrays. It is often used for
tables, matrices, schedules, game boards, or the grades of several
students. An element is accessed with two indexes:
`matrix[row][column]`.

**Practice:** create a `3 x 3` matrix and fill it with numbers. Find the
sum of the main diagonal elements: `[0][0]`, `[1][1]`, `[2][2]`.

---

## Methods

> **Key constructs:** `public`, `private`, `static`, `void`, `return`, parameters, overloading, `main(String[] args)`

### `public`, `private`, `static`

`public` means the method is accessible from other classes. `private`
restricts access to the current class only. `static` means the method
belongs to the class itself rather than to a specific object.

**Practice:** create a class with a `public static` method
`printHello()`. Call it from `main`, then make the method `private` and
discuss where it can be called from.

### `void`

`void` indicates that a method returns no value. Such a method usually
performs an action: prints text, mutates an object, or processes data.
After it finishes, control simply returns to the call site.

**Practice:** write a method `printGreeting(String name)` that prints a
greeting. Call it for several names.

### `return`

`return` ends the method's execution and may return a result. The return
type must match the type written before the method name. For example, an
`int` method can return a number via `return result`.

**Practice:** write a method `sum(int a, int b)` that returns the sum.
Use the method's result in `System.out.println()`.

### Method Parameters

Parameters are a method's input data, written in parentheses in its
declaration. They let the same method work with different values. When
calling the method, arguments are passed.

**Practice:** write a method `isEven(int number)` that returns `true`
when the number is even. Call it for several numbers.

### Method Overloading

Overloading lets you create several methods with the same name but
different parameters. The number of parameters, their types, or their
order may differ. The return type alone is not enough for overloading.

**Practice:** create two `sum` methods: one for two numbers, the other
for three. Call both versions and check how Java picks the right one.

### `main(String[] args)`

`main` is the static method the JVM calls when the program starts.
`String[] args` holds the command-line arguments as text. Even if the
arguments are not used yet, the parameter must be present in the
standard entry point.

**Practice:** write a program that takes a name from `args[0]` and
prints a greeting. Run it with an argument: `java Main Anna`.

---

## OOP Basics

> **Key constructs:** `class`, object, `new`, constructor, `this`, fields, encapsulation, `get` / `set`, `package`

### `class`

A class is a description of a new data type. Inside a class there can be
fields, constructors, and methods. A class defines the common structure
of objects — for example, students, products, books, or cars.

**Practice:** create a `Student` class with the fields `name` and `age`.
Do not add methods yet — only describe the object structure.

### Object and `new`

An object is a concrete instance of a class. The `new` operator
allocates memory for the object and calls its constructor. One class can
have many objects with different field values.

**Practice:** create two `Student` objects: one named Anna, the other
named Ivan. Print their field values.

### Constructor

A constructor is called when an object is created with `new`. Its name
matches the class name, and it has no return type. Constructors are
usually used to set initial field values.

**Practice:** add a constructor `Student(String name, int age)` to
`Student`. Create students by passing the data directly into
`new Student(...)`.

### `this`

`this` is a reference to the current object. It is most often used in a
constructor or method when a parameter name matches a field name:
`this.name = name`. It makes it explicit that the left side refers to
the object's field.

**Practice:** in the `Student` constructor, use `this.name = name` and
`this.age = age`. Explain the difference between the parameter and the
field.

### Class Fields

Fields are variables declared inside a class but outside methods. They
store the object's state: a student's name, a product's price, a car's
speed, and so on. Each object usually has its own values of its regular
fields.

**Practice:** add a `grade` or `group` field to the `Student` class.
Create several objects with different values.

### Encapsulation

Encapsulation is the OOP principle of protecting an object's internal
data from direct, uncontrolled modification. Usually the fields are made
`private`, and access is provided through methods. This allows
validating data before changing it.

**Practice:** make the `age` field private. Try to access it from
another class and examine the access error.

### Getters and Setters: `get` / `set`

A getter is a method for reading a field value, for example `getName()`.
A setter is a method for changing it, for example `setAge(int age)`. A
setter can include a check — for example, forbidding a negative age.

**Practice:** add `getName()` and `setAge(int age)` to the `Student`
class. Disallow values below zero in `setAge`.

### `package`

A package is a namespace and a way to organize classes in a project.
Usually a package corresponds to a folder: `package model;` for
data-model classes. Packages help avoid name conflicts and split the
project into logical parts.

**Practice:** create a `model` package for `Student` and a separate
`Main` class in another package. Import `Student` with `import`.

---

## Input and Output

> **Key constructs:** `Scanner`, `System.out.println()`, `print()`, `printf()`, `System.in`, `nextInt()`, `next()`, `nextLine()`

### `Scanner`

`Scanner` is a class for reading data from the console, files, and other
sources. For keyboard input, create the object with
`new Scanner(System.in)`. Using it requires importing
`java.util.Scanner`.

**Practice:** ask the user for a name and an age, then print a greeting
with the data received. Create the `Scanner` once in `main`.

### `System.out.println()`

`System.out.println()` prints a value to the console and moves the
cursor to a new line. It suits regular step-by-step output. Java
automatically converts numbers, booleans, and other values into text.

**Practice:** print the user's name, age, and city — each value on a
separate line. Add text labels to the values.

### `System.out.print()`

`System.out.print()` prints a value without moving to a new line. The
next output continues right after the previous text. It is convenient
for printing a prompt before input or elements on one line.

**Practice:** print the numbers from 1 to 10 on one line with `print()`.
Add a space after each number.

### `System.out.printf()`

`System.out.printf()` prints formatted text. Templates can be used: `%s`
for a string, `%d` for an integer, `%f` for a floating-point number. For example,
`%.2f` prints a fractional number with two digits after the decimal
point.

**Practice:** print a product name, quantity, and price with `printf()`.
Use a format with two decimal places for the price.

### `System.in`

`System.in` is the standard input stream, usually connected to the
keyboard. It is what gets passed to `Scanner` when console input is
needed. At the beginner level, `System.in` itself is rarely used
directly.

**Practice:** analyze the line
`Scanner scanner = new Scanner(System.in);`: where the Scanner object is
created and what the data source is.

### `nextInt()`

`nextInt()` reads the next integer from the input. If the user enters a
non-numeric value, an input error occurs. After `nextInt()`, a
line-break character often remains in the stream.

**Practice:** read two integers and print their sum. Then call
`nextLine()` after `nextInt()` and demonstrate the leftover line-break
problem.

### `next()`

`next()` reads a single word up to the first space. It suits short
values: a login, a command, a one-word city. If the user enters a phrase
of several words, only the first word is read.

**Practice:** ask for the user's login with `next()`. Enter a name with
a space and show that only the first part is read.

### `nextLine()`

`nextLine()` reads the whole line, including spaces between words. It is
convenient for full names, addresses, messages, or sentences. When mixed
with `nextInt()`, an extra `nextLine()` call is sometimes needed to
consume the leftover line break.

**Practice:** read the user's full name and a favorite quote. Then
implement reading an age with `nextInt()` and a name with `nextLine()`,
handling the line break correctly.

---

*Continue with [Part II](part-ii.md) — inheritance, polymorphism and abstraction, encapsulation, exceptions, collections, generics, lambdas and the Stream API, functional interfaces, files and I/O, multithreading, and useful extras.*
