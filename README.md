# DailyAlgorithmPractice

每日算法题打卡项目，目标是长期稳定维护（365+ 天），并保持结构清晰、可检索、可复用。

## 项目目标

- 每天新增一道算法题（支持手动新增，预留自动化接口）。
- 每道题都包含题目描述、示例、边界条件、思路与通俗讲解。
- 每道题固定包含两份代码：`Java` 在前、`Python` 在后，且都带详细注释。
- 支持按日期、难度、题型进行检索。
- 目录结构固定，不会随着题目增多而混乱。

## 目录结构

```text
DailyAlgorithmPractice/
├── README.md
├── INDEX.md
├── MAINTENANCE.md
├── STYLE_GUIDE.md
├── TEMPLATES/
│   ├── README.template.md
│   ├── Solution.java
│   └── Solution.py
└── problems/
    ├── .gitkeep
    └── YYYY/
        └── MM/
            └── YYYY-MM-DD-leetcode-编号-题目短名/
                ├── README.md
                ├── SolutionLC<编号>.java
                └── SolutionLC<编号>.py
```

## 快速开始（每天新增一道题）

1. 按日期创建目录：`problems/YYYY/MM/YYYY-MM-DD-leetcode-编号-题目短名/`
2. 从 `TEMPLATES` 复制 3 个模板文件到该目录：
   - `README.template.md` -> `README.md`
   - `Solution.java`（脚本会重命名为 `SolutionLC<编号>.java`）
   - `Solution.py`（脚本会重命名为 `SolutionLC<编号>.py`）
3. 填写题目内容（题目、示例、边界、思路、讲解、代码）。
4. 更新根目录 `INDEX.md`，登记日期、题名、难度、题型、路径。

## 一键新增（推荐）

项目内置零依赖脚本：`new_problem.sh`

```bash
./new_problem.sh <leetcode_no> <title_short> [difficulty] [category]
```

示例：

```bash
./new_problem.sh 3 无重复字符最长子串 Medium 滑动窗口
./new_problem.sh 704 二分查找 Easy 二分查找
```

执行后会自动：

- 按当天日期创建目录：`problems/YYYY/MM/YYYY-MM-DD-leetcode-编号-题目短名/`
- 自动复制 3 个模板文件
- 自动把 `README.md` 里的日期、难度、题型、标题占位符替换掉

随后你只需要填写题目内容并更新 `INDEX.md`。

## 检索方式（无需任何依赖）

- 按日期：直接按目录进入 `problems/YYYY/MM/`
- 按难度：在 `INDEX.md` 中筛选 `Easy/Medium/Hard`
- 按题型：在 `INDEX.md` 的「题型」列筛选（双指针、二分、DP 等）
- 按关键词：本地搜索 `README.md` 中关键词

## 约定

- 同一天可多题，按编号和题目短名区分目录。
- 每题目录文件名固定为：`README.md`、`SolutionLC<编号>.java`、`SolutionLC<编号>.py`。
- Java 代码优先，Python 代码随后，注释都要解释“为什么这样做”。
- 目录命名固定：`YYYY-MM-DD-leetcode-编号-题目短名`（题目短名可中文，建议简短）。
