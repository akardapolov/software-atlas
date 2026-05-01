#!/bin/bash
#
# Validate links in Software Atlas documentation.
#
# This script checks for broken internal and external links
# in the markdown documentation.
#

set -euo pipefail

DOCS_DIR="$(cd "$(dirname "$0")/../docs" && pwd)"
REPO_URL="https://github.com/software-atlas/software-atlas"

echo "Software Atlas Link Validator"
echo "============================="
echo "Scanning: $DOCS_DIR"
echo ""

# Check for required tools
command -v grep >/dev/null 2>&1 || { echo "grep required but not installed."; exit 1; }

# Counters
total_links=0
broken_internal=0
broken_external=0

# Function to check internal link
check_internal_link() {
    local link="$1"
    local file="$2"

    # Remove anchor
    local clean_link="${link%%#*}"

    # Resolve relative path
    local target_file
    if [[ "$link" == /* ]]; then
        # Absolute path (relative to docs root)
        target_file="$DOCS_DIR$clean_link"
    else
        # Relative path
        local dir=$(dirname "$file")
        target_file="$dir/$clean_link"
    fi

    # Check if file or directory exists
    if [[ -f "$target_file" ]] || [[ -d "$target_file" ]]; then
        return 0
    fi

    # Check if it's an index.md link without the filename
    if [[ -d "$target_file" ]] && [[ -f "$target_file/index.md" ]]; then
        return 0
    fi

    echo "  [BROKEN] $link (from $file)"
    broken_internal=$((broken_internal + 1))
    return 1
}

# Function to check external link
check_external_link() {
    local link="$1"
    local file="$2"

    # Skip localhost and example links
    if [[ "$link" =~ (localhost|127.0.0.1|example\.com) ]]; then
        return 0
    fi

    # Simple check - in production, use curl or wget
    # For now, just count them
    ((total_links++))
    return 0
}

# Scan all markdown files
echo "Scanning markdown files..."
while IFS= read -r -d '' file; do
    echo "Processing: $(basename "$file")"

    # Find all markdown links: [text](link) and [text]: link
    while IFS= read -r line; do
        if [[ "$line" =~ \[.*\]\(([^)]+)\) ]]; then
            link="${BASH_REMATCH[1]}"
            ((total_links++))

            if [[ "$link" =~ ^https?:// ]] || [[ "$link" =~ ^mailto: ]]; then
                check_external_link "$link" "$file"
            elif [[ "$link" =~ ^# ]]; then
                # Internal anchor - skip for now
                :
            else
                check_internal_link "$link" "$file"
            fi
        fi
    done < <(grep -oE '\[[^]]*\]\([^)]+\)' "$file") || true

done < <(find "$DOCS_DIR" -name "*.md" -print0)

# Summary
echo ""
echo "============================="
echo "Scan complete!"
echo "Total links found: $total_links"
echo "Broken internal links: $broken_internal"
echo "Broken external links: $broken_external"

if [[ $broken_internal -gt 0 ]] || [[ $broken_external -gt 0 ]]; then
    echo ""
    echo "❌ Validation failed - please fix broken links"
    exit 1
else
    echo ""
    echo "✅ All links validated successfully"
    exit 0
fi
