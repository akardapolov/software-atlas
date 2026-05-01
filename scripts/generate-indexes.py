#!/usr/bin/env python3
"""
Generate index tables for Software Atlas documentation.

This script scans the documentation directory and generates
index pages with tables for languages, authors, and works.
"""

import os
import re
from pathlib import Path
from typing import Dict, List

DOCS_DIR = Path(__file__).parent.parent / "docs"


def extract_frontmatter(content: str) -> Dict[str, str]:
    """Extract YAML frontmatter from markdown content."""
    match = re.match(r'^---\n(.*?)\n---', content, re.DOTALL)
    if match:
        frontmatter = {}
        for line in match.group(1).split('\n'):
            if ':' in line:
                key, value = line.split(':', 1)
                frontmatter[key.strip()] = value.strip()
        return frontmatter
    return {}


def get_language_info(lang_dir: Path) -> Dict:
    """Extract information from a language index.md."""
    index_file = lang_dir / "index.md"
    if not index_file.exists():
        return {}

    content = index_file.read_text()
    frontmatter = extract_frontmatter(content)

    # Extract basic info if no frontmatter
    if not frontmatter:
        # Try to extract from content
        year_match = re.search(r'Year:\s*(\d{4})', content)
        creator_match = re.search(r'Creator[s]?:\s*(.+)', content)

        return {
            'year': year_match.group(1) if year_match else '',
            'creator': creator_match.group(1).strip() if creator_match else '',
            'paradigm': '',
            'typing': '',
        }

    return frontmatter


def generate_languages_index():
    """Generate languages index table."""
    languages_dir = DOCS_DIR / "languages"
    if not languages_dir.exists():
        print("Languages directory not found")
        return

    languages = []
    for lang_dir in sorted(languages_dir.iterdir()):
        if lang_dir.is_dir() and (lang_dir / "index.md").exists():
            name = lang_dir.name
            info = get_language_info(lang_dir)
            languages.append({
                'name': name.capitalize(),
                'year': info.get('year', ''),
                'creator': info.get('creator', ''),
                'paradigm': info.get('paradigm', ''),
                'typing': info.get('typing', ''),
            })

    print(f"Found {len(languages)} languages")
    for lang in languages:
        print(f"  - {lang['name']}: {lang['year']}, {lang['creator']}")


def main():
    """Main entry point."""
    print("Software Atlas Index Generator")
    print("=" * 40)

    generate_languages_index()

    print("\nIndex generation complete!")


if __name__ == "__main__":
    main()
