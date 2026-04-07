#!/usr/bin/env python3
"""
Sync LeetCode problem images into local README.

Usage:
  python3 sync_leetcode_images.py <problem_readme_path>
  python3 sync_leetcode_images.py --all
"""

from __future__ import annotations

import argparse
import html as html_lib
import json
import re
import sys
import urllib.error
import urllib.parse
import urllib.request
from pathlib import Path

GRAPHQL_URL = "https://leetcode.cn/graphql/"
SECTION_TITLE = "## 题目原图（来自 LeetCode）"


def extract_slug(readme_text: str) -> str | None:
    # Match both leetcode.com and leetcode.cn links in README
    match = re.search(r"https?://leetcode\.(?:com|cn)/problems/([^/]+)/?", readme_text)
    return match.group(1) if match else None


def fetch_question_html(slug: str) -> str:
    query = (
        "query questionData($titleSlug: String!) { "
        "question(titleSlug: $titleSlug) { translatedContent content } }"
    )
    payload = json.dumps({"query": query, "variables": {"titleSlug": slug}}).encode("utf-8")
    req = urllib.request.Request(
        GRAPHQL_URL,
        data=payload,
        headers={"Content-Type": "application/json", "User-Agent": "Mozilla/5.0"},
    )
    with urllib.request.urlopen(req, timeout=20) as resp:
        obj = json.loads(resp.read().decode("utf-8"))
    question = obj.get("data", {}).get("question")
    if not question:
        raise ValueError(f"Cannot fetch question content for slug: {slug}")
    return question.get("translatedContent") or question.get("content") or ""


def extract_image_items(html: str) -> list[dict[str, str]]:
    tags = re.findall(r"<img\b[^>]*>", html, flags=re.IGNORECASE)
    # Keep order and deduplicate by url
    seen = set()
    ordered = []
    for tag in tags:
        src_match = re.search(r"src=['\"]([^'\"]+)['\"]", tag, flags=re.IGNORECASE)
        if not src_match:
            continue
        url = src_match.group(1)
        if url.startswith("//"):
            url = "https:" + url
        if not url.startswith("http"):
            continue
        if url not in seen:
            seen.add(url)
            alt_match = re.search(r"alt=['\"]([^'\"]*)['\"]", tag, flags=re.IGNORECASE)
            alt = html_lib.unescape(alt_match.group(1)).strip() if alt_match else ""
            ordered.append({"url": url, "alt": alt})
    return ordered


def safe_ext_from_url(url: str) -> str:
    path = urllib.parse.urlparse(url).path
    suffix = Path(path).suffix.lower()
    if suffix in {".png", ".jpg", ".jpeg", ".gif", ".webp", ".svg"}:
        return suffix
    return ".png"


def download_images(image_items: list[dict[str, str]], images_dir: Path) -> list[dict[str, str]]:
    images_dir.mkdir(parents=True, exist_ok=True)
    saved = []
    for idx, item in enumerate(image_items, start=1):
        url = item["url"]
        ext = safe_ext_from_url(url)
        filename = f"leetcode-image-{idx:02d}{ext}"
        target = images_dir / filename
        req = urllib.request.Request(url, headers={"User-Agent": "Mozilla/5.0"})
        with urllib.request.urlopen(req, timeout=30) as resp:
            target.write_bytes(resp.read())
        saved.append({"path": str(target), "url": url, "alt": item.get("alt", "")})
    return saved


def build_section(local_images: list[dict[str, str]], readme_dir: Path) -> str:
    lines = [SECTION_TITLE, ""]
    for idx, image_item in enumerate(local_images, start=1):
        image_path = Path(image_item["path"])
        rel = image_path.relative_to(readme_dir).as_posix()
        alt = image_item.get("alt", "").strip() or f"LeetCode 题面配图 {idx}"
        # Use HTML image tag for better compatibility across Markdown renderers.
        lines.append(f'<img src="{rel}" alt="{alt}" />')
        lines.append("")
        lines.append(f"- 本地图：`{rel}`")
        lines.append(f"- 原图地址：`{image_item['url']}`")
        lines.append("")
    return "\n".join(lines)


def inject_or_replace_section(readme_text: str, new_section: str) -> str:
    # Replace existing section if present
    pattern = re.compile(
        rf"{re.escape(SECTION_TITLE)}\n.*?(?=\n## |\Z)",
        flags=re.DOTALL,
    )
    if pattern.search(readme_text):
        return pattern.sub(new_section.rstrip() + "\n", readme_text)

    # Otherwise, insert after "## 题目描述" block if possible
    marker = "## 输入输出示例"
    if marker in readme_text:
        return readme_text.replace(marker, new_section + "\n" + marker, 1)
    return readme_text.rstrip() + "\n\n" + new_section + "\n"


def sync_one(readme_path: Path) -> tuple[bool, str]:
    if not readme_path.exists():
        return False, f"README not found: {readme_path}"

    content = readme_path.read_text(encoding="utf-8")
    slug = extract_slug(content)
    if not slug:
        return False, f"No LeetCode link found in: {readme_path}"

    html = fetch_question_html(slug)
    image_items = extract_image_items(html)
    if not image_items:
        return True, f"No image found for slug '{slug}' ({readme_path})"

    images_dir = readme_path.parent / "images"
    local_images = download_images(image_items, images_dir)
    new_section = build_section(local_images, readme_path.parent)
    updated = inject_or_replace_section(content, new_section)
    readme_path.write_text(updated, encoding="utf-8")
    return True, f"Synced {len(local_images)} image(s) for {readme_path}"


def iter_all_readmes() -> list[Path]:
    return sorted(Path("problems").glob("**/README.md"))


def main() -> int:
    parser = argparse.ArgumentParser(description="Sync LeetCode images into local README.")
    parser.add_argument("readme", nargs="?", help="Path to one problem README.md")
    parser.add_argument("--all", action="store_true", help="Process all problem README files")
    args = parser.parse_args()

    if not args.all and not args.readme:
        parser.error("Provide a README path or use --all")

    targets = iter_all_readmes() if args.all else [Path(args.readme)]
    ok = True
    for path in targets:
        try:
            success, message = sync_one(path)
            print(message)
            ok = ok and success
        except (urllib.error.URLError, ValueError) as exc:
            ok = False
            print(f"Failed: {path} -> {exc}")
        except Exception as exc:  # keep batch mode robust
            ok = False
            print(f"Unexpected error: {path} -> {exc}")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main())
