# Testing in Python

## Overview

Python includes **unittest** in stdlib, with **pytest** as de facto standard.
**Mocking** built into \`unittest.mock\`. **doctest** tests examples in
docstrings. Coverage tools integrate with testing frameworks.

## Code

See \`main.py\` in this directory.

## How to Run

\`\`\`bash
python -m unittest
# Or with pytest
pytest
pytest -v  # verbose
pytest --cov=mymodule --cov-report=html
\`\`\`

## Key Concepts

- **unittest** — \`TestCase\`, \`assertEqual\`, \`assertRaises\`
- **pytest** — fixture-based, no boilerplate needed
- **Asserts** — \`assert\`, \`pytest.raises()\`
- **Fixtures** — \`@pytest.fixture()\` for shared test data
- **Parametrized** — \`@pytest.mark.parametrize()\`
- **Mocking** — \`unittest.mock.Mock\`, \`@patch()\`
- **doctest** — test docstring examples: \`python -m doctest file.py\`
- **Coverage** — \`coverage.py\`, \`pytest-cov\`

## Historical Context

Python (1991) added \`unittest\` in Python 2.1 (2001), influenced
by JUnit. pytest (2004) emerged as simpler alternative. doctest
was part of early Python for documentation testing. Mocking was added to
stdlib in Python 3.3 (2012).

For more on Python, see [docs/languages/python/](../../../docs/languages/python/)
