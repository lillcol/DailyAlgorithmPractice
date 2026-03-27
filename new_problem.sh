#!/usr/bin/env sh

set -eu

# Usage:
#   ./new_problem.sh 3 无重复字符最长子串
#   ./new_problem.sh 704 二分查找 Medium 二分查找
#
# Params:
#   $1 lc_no      (required, LeetCode 编号, e.g. 3)
#   $2 title      (required, 题目短名，可中文，建议不超过 12 字)
#   $3 difficulty (optional, default: Easy)
#   $4 category   (optional, default: 数组)

if [ "${1:-}" = "" ] || [ "${2:-}" = "" ]; then
  echo "Usage: ./new_problem.sh <leetcode_no> <title_short> [difficulty] [category]"
  echo "Example: ./new_problem.sh 3 无重复字符最长子串 Medium 滑动窗口"
  exit 1
fi

LC_NO="$1"
TITLE="$2"
DIFFICULTY="${3:-Easy}"
CATEGORY="${4:-数组}"

# 将题目短名中的空格替换为连字符，避免目录名出现空格
TITLE_DIR="$(printf "%s" "$TITLE" | tr ' ' '-')"

TODAY="$(date +%Y-%m-%d)"
YEAR="$(date +%Y)"
MONTH="$(date +%m)"

BASE_DIR="problems/$YEAR/$MONTH/${TODAY}-leetcode-${LC_NO}-${TITLE_DIR}"
JAVA_FILE="SolutionLC${LC_NO}.java"
PYTHON_FILE="SolutionLC${LC_NO}.py"

if [ -d "$BASE_DIR" ]; then
  echo "Directory already exists: $BASE_DIR"
  echo "If you want another problem today, try a slightly different short title."
  exit 1
fi

mkdir -p "$BASE_DIR"

cp "TEMPLATES/README.template.md" "$BASE_DIR/README.md"
cp "TEMPLATES/Solution.java" "$BASE_DIR/$JAVA_FILE"
cp "TEMPLATES/Solution.py" "$BASE_DIR/$PYTHON_FILE"

escape_sed_replacement() {
  printf '%s' "$1" | sed 's/[\/&]/\\&/g'
}

TITLE_ESCAPED="$(escape_sed_replacement "$TITLE")"
LC_NO_ESCAPED="$(escape_sed_replacement "$LC_NO")"
JAVA_FILE_ESCAPED="$(escape_sed_replacement "$JAVA_FILE")"
PYTHON_FILE_ESCAPED="$(escape_sed_replacement "$PYTHON_FILE")"

# Replace placeholders in README (BSD/macOS sed compatible)
sed -i '' "s/YYYY-MM-DD/$TODAY/g" "$BASE_DIR/README.md"
sed -i '' "s/Easy \/ Medium \/ Hard/$DIFFICULTY/g" "$BASE_DIR/README.md"
sed -i '' "s/数组 \/ 哈希表 \/ 双指针 \/ 动态规划 \/ .../$CATEGORY/g" "$BASE_DIR/README.md"
sed -i '' "s/题目标题（例如：Two Sum）/LeetCode ${LC_NO_ESCAPED} - ${TITLE_ESCAPED}/g" "$BASE_DIR/README.md"
sed -i '' "s/题目链接：（可选）/题目链接：https:\/\/leetcode.com\/problems\/\//g" "$BASE_DIR/README.md"
sed -i '' "s/Solution.java/${JAVA_FILE_ESCAPED}/g" "$BASE_DIR/README.md"
sed -i '' "s/Solution.py/${PYTHON_FILE_ESCAPED}/g" "$BASE_DIR/README.md"
sed -i '' "s/public class Solution/public class SolutionLC${LC_NO_ESCAPED}/g" "$BASE_DIR/$JAVA_FILE"

echo "Created: $BASE_DIR"
echo ""
echo "Next steps:"
echo "1) Fill problem details in: $BASE_DIR/README.md"
echo "2) Implement Java in:       $BASE_DIR/$JAVA_FILE"
echo "3) Implement Python in:     $BASE_DIR/$PYTHON_FILE"
echo "4) Append one row to INDEX.md"
