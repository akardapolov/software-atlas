"""
Control Flow in Python
======================
Demonstrates if/elif/else, for/while, break/continue, comprehensions,
and match-case (Python 3.10+).
"""

def classify(n: int) -> str:
    if n < 0:
        return "negative"
    elif n == 0:
        return "zero"
    else:
        return "positive"

print("--- if/elif/else ---")
for n in [-3, 0, 7]:
    print(n, "→", classify(n))


print("\n--- for loop (iterables) ---")
total = 0
for i in range(1, 6):
    total += i
print("sum 1..5 =", total)


print("\n--- while loop ---")
x = 1
while x < 20:
    x *= 2
print("first power of two >= 20:", x)


print("\n--- break / continue ---")
for i in range(1, 10):
    if i % 2 == 1:
        continue
    if i > 6:
        break
    print("even <= 6:", i)


print("\n--- comprehensions (expression-style loops) ---")
squares = [i * i for i in range(1, 11)]
evens = [i for i in range(1, 21) if i % 2 == 0]
print("squares:", squares)
print("evens:", evens)


print("\n--- match/case (3.10+) ---")
def describe(value):
    match value:
        case 0:
            return "zero"
        case int() as n if n < 0:
            return f"negative int {n}"
        case int() as n:
            return f"positive int {n}"
        case str() as s:
            return f"string({len(s)})"
        case [a, b]:
            return f"2-list: {a}, {b}"
        case _:
            return "something else"

for v in [0, -5, 7, "hello", [1, 2], {"a": 1}]:
    print(v, "→", describe(v))


# ── Summary ───────────────────────────────────

print("\n--- Summary ---")
print("Python control flow:")
print("  - if/elif/else for conditionals")
print("  - for loop over iterables (lists, strings, ranges)")
print("  - while loop")
print("  - break / continue for loop control")
print("  - list comprehensions for concise loops")
print("  - match/case for pattern matching (3.10+)")
