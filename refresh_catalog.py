#!/usr/bin/env python3
import re
from pathlib import Path


ROOT = Path(__file__).resolve().parent
PROBLEMS_DIR = ROOT / "problems"
OUTPUT_FILE = ROOT / "CATALOG.md"

DIR_PATTERN = re.compile(r"^\d{4}-\d{2}-\d{2}-leetcode-(\d+)-(.+)$")


def parse_readme_info(readme_path: Path) -> tuple[str, str]:
    difficulty = "未知"
    category = "未知"
    try:
        content = readme_path.read_text(encoding="utf-8")
    except Exception:
        return difficulty, category

    for line in content.splitlines():
        if line.startswith("- 难度："):
            difficulty = line.split("：", 1)[1].strip()
        elif line.startswith("- 题型："):
            category = line.split("：", 1)[1].strip()
    return difficulty, category


def build_rows() -> list[dict]:
    rows = []
    if not PROBLEMS_DIR.exists():
        return rows

    for year_dir in sorted([p for p in PROBLEMS_DIR.iterdir() if p.is_dir()]):
        for month_dir in sorted([p for p in year_dir.iterdir() if p.is_dir()]):
            for problem_dir in sorted([p for p in month_dir.iterdir() if p.is_dir()]):
                match = DIR_PATTERN.match(problem_dir.name)
                if not match:
                    continue

                lc_no = match.group(1)
                title = match.group(2)
                date = problem_dir.name[:10]
                readme_path = problem_dir / "README.md"
                java_path = problem_dir / f"SolutionLC{lc_no}.java"
                python_path = problem_dir / f"SolutionLC{lc_no}.py"
                difficulty, category = parse_readme_info(readme_path)

                rows.append(
                    {
                        "date": date,
                        "lc_no": lc_no,
                        "title": title,
                        "difficulty": difficulty,
                        "category": category,
                        "java": "Y" if java_path.exists() else "N",
                        "python": "Y" if python_path.exists() else "N",
                        "readme": "Y" if readme_path.exists() else "N",
                        "dir": problem_dir.relative_to(ROOT).as_posix(),
                    }
                )

    rows.sort(key=lambda r: (r["date"], int(r["lc_no"])), reverse=True)
    return rows


def render_markdown(rows: list[dict]) -> str:
    total = len(rows)
    easy = sum(1 for r in rows if r["difficulty"] == "Easy")
    medium = sum(1 for r in rows if r["difficulty"] == "Medium")
    hard = sum(1 for r in rows if r["difficulty"] == "Hard")

    lines = [
        "# 题目总览（Catalog）",
        "",
        "> 该文件由 `refresh_catalog.py` 自动生成。新增或修改题目后请重新执行脚本。",
        "",
        "## 快速统计",
        "",
        f"- 总题数：`{total}`",
        f"- 难度分布：`Easy={easy}`，`Medium={medium}`，`Hard={hard}`",
        "",
        "## 全量目录（按日期倒序）",
        "",
        "| 日期 | LC 题号 | 题目短名 | 难度 | 题型 | README | Java | Python | 目录 |",
        "|---|---:|---|---|---|---|---|---|---|",
    ]

    for r in rows:
        lines.append(
            f"| {r['date']} | {r['lc_no']} | {r['title']} | {r['difficulty']} | "
            f"{r['category']} | {r['readme']} | {r['java']} | {r['python']} | "
            f"`{r['dir']}` |"
        )

    lines.extend(
        [
            "",
            "## 使用方式",
            "",
            "```bash",
            "python3 refresh_catalog.py",
            "```",
            "",
        ]
    )

    return "\n".join(lines)


def main() -> None:
    rows = build_rows()
    OUTPUT_FILE.write_text(render_markdown(rows), encoding="utf-8")
    print(f"Updated {OUTPUT_FILE.relative_to(ROOT)} with {len(rows)} problem(s).")


if __name__ == "__main__":
    main()
