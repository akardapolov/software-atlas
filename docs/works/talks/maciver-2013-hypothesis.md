# Hypothesis: A Python Library for Property-Based Testing

| | |
|---|---|
| **Author** | David MacIver |
| **Year** | 2013 |
| **Topic(s)** | Property-based testing, Python |
| **URL** | https://hypothesis.readthedocs.io/ |

## Summary

Hypothesis is a modern Python library for property-based testing, inspired by
QuickCheck but designed with lessons learned from a decade of
property-based testing experience. It brings property-based testing to
Python with a focus on usability, powerful shrinking, and good error messages.

## Key Ideas

### 1. Strategies for Generating Data

Hypothesis provides **strategies** for generating test data:

```python
from hypothesis import given, strategies as st

@given(st.integers())
def test_reverse_twice(xs):
    assert list(reversed(list(reversed(xs)))) == xs

@given(st.lists(st.integers()))
def test_sort_length(xs):
    assert len(sorted(xs)) == len(xs)

@given(st.tuples(st.integers(), st.integers()))
def test_addition(t):
    a, b = t
    assert a + b == b + a
```

### 2. Advanced Shrinking

Hypothesis's shrinking algorithm is a key innovation over QuickCheck.
Instead of naive shrinking, it uses a **database of shrinking strategies**
and tries to reduce complexity across multiple dimensions simultaneously:

```python
# Failing example found: [1000, 50, 3, -7, 0, 42]
# After shrinking: [-7]
```

The shrinking algorithm:
- Shrinks integers toward zero
- Shrinks list lengths toward zero
- Shrinks tuple elements independently
- Shrinks strings toward empty strings

### 3. Hypothesis for Stateful Testing

Hypothesis provides **stateful testing** for testing complex systems:

```python
from hypothesis.stateful import RuleBasedStateMachine, rule

class DatabaseMachine(RuleBasedStateMachine):
    def __init__(self):
        super().__init__()
        self.db = Database()
        self.values = []

    @rule(key=st.text(), value=st.integers())
    def insert(self, key, value):
        self.db.insert(key, value)
        self.values.append((key, value))

    @rule(key=st.text())
    def get(self, key):
        self.db.get(key)  # Should not raise

    @rule()
    def teardown(self):
        self.db.clear()

TestDatabase = DatabaseMachine.TestCase
```

### 4. Hypothesis Finds Real Bugs

Hypothesis has found bugs in:
- Python standard library
- Popular Python packages
- Production code in industry

Example bug found:
```python
@given(st.integers(), st.integers())
def test_division(a, b):
    assert (a / b) * b == a  # Fails for b=0
```

### 5. Composability

Hypothesis strategies are composable:

```python
# Custom strategy for email addresses
email = st.builds(
    lambda name, domain: f"{name}@{domain}",
    st.from_regex(r"[a-z]+", fullmatch=True),
    st.sampled_from(["gmail.com", "yahoo.com", "example.com"])
)

@given(email)
def test_email_validation(e):
    assert validate_email(e) is True
```

## Differences from QuickCheck

| Feature | QuickCheck | Hypothesis |
|---------|------------|------------|
| **Language** | Haskell | Python |
| **Generation** | Typeclass-based | Strategy-based (explicit) |
| **Shrinking** | Simple shrinking | Advanced database of shrinking strategies |
| **Error messages** | Basic counterexample | Minimal counterexample + reproduction code |
| **Stateful testing** | Limited | Built-in RuleBasedStateMachine |

## Impact and Legacy

### Python Community Adoption

Hypothesis is the de facto standard for property-based testing in Python:
- Used by pytest, Django projects, Flask projects
- Integrated into many testing pipelines
- Part of the Python testing ecosystem

### Influence on Other Libraries

Hypothesis has influenced property-based testing libraries in other languages:
- **fast-check** (TypeScript/JavaScript) — inspired by Hypothesis's shrinking
- **proptest** (Rust) — learned from Hypothesis's stateful testing

### Community and Education

David MacIver has been instrumental in:
- Teaching property-based testing through talks and writing
- Contributing to the testing community
- Documenting best practices for property-based testing

## Usage in Practice

### Testing Algorithms

```python
from hypothesis import given, strategies as st

@given(st.lists(st.integers()))
def test_merge_sort(xs):
    result = merge_sort(xs)
    assert sorted(result) == result  # Sorted
    assert sorted(xs) == result       # Permutation
    assert result == result[::-1][::-1]  # Reverse twice
```

### Testing APIs

```python
@given(st.integers(min_value=1, max_value=100))
def test_api_get_user(user_id):
    response = api.get_user(user_id)
    assert response.status_code == 200
    assert response.json()["id"] == user_id
```

### Testing Edge Cases

```python
@given(st.integers())
def test_square_root(n):
    if n < 0:
        with pytest.raises(ValueError):
            math.sqrt(n)
    else:
        result = math.sqrt(n)
        assert result * result == n
```

## Historical Context

### Origins

Hypothesis was created by David MacIver in 2013, inspired by:
- **QuickCheck** (Claessen & Hughes, 2000) — the original property-based testing library
- **ScalaCheck** — QuickCheck port to Scala
- **FsCheck** — QuickCheck port to F#

MacIver wanted a property-based testing library for Python that was:
- Easy to use for Python developers
- Powerful enough for industrial use
- Had good error messages

### Evolution

Hypothesis evolved through multiple versions:
- **2013** — Initial release
- **2015** — Stateful testing support
- **2016** — Shrinking improvements
- **2017** — Database of shrinking strategies
- **2019** — Integration with pytest fixtures
- **2020+** — Ongoing improvements and bug fixes

## Criticism and Limitations

### Learning Curve

Property-based testing requires learning:
- How to write good properties
- How to design custom strategies
- How to interpret counterexamples

### Not for Everything

Property-based testing is not ideal for:
- Highly stateful systems (though Hypothesis has stateful testing)
- Systems with complex external dependencies
- Testing UI interactions

### Slow for Complex Operations

Generating thousands of inputs for complex operations can be slow.
Careful strategy design is important.

## Connections

- **Builds on:** QuickCheck (Claessen & Hughes, 2000) · Property-based testing principles
- **Complements:** TDD · Example-based testing
- **Author:** [David MacIver](../../authors/david-maciver.md)
- **Related work:** [QuickCheck](../papers/hughes-claessen-2000-quickcheck.md)
- **Related topic:** [Testing](../../topics/process/index.md#property-based-testing) · [Python](../../languages/python/index.md)
