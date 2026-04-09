# LeetCode 101 - 对称二叉树

## 基本信息

- 日期：2026-04-09
- 难度：Easy
- 题型：二叉树
- 题目链接：[https://leetcode.com/problems/symmetric-tree/](https://leetcode.com/problems/symmetric-tree/)

## 题目描述

给你一个二叉树的根节点 `root`，检查它是否轴对称（即这棵树是否与其镜像一致）。

## 题目原图（来自 LeetCode）

<img src="images/leetcode-image-01.png" alt="LeetCode 题面配图 1" />

- 本地图：`images/leetcode-image-01.png`
- 原图地址：`https://pic.leetcode.cn/1698026966-JDYPDU-image.png`

<img src="images/leetcode-image-02.png" alt="LeetCode 题面配图 2" />

- 本地图：`images/leetcode-image-02.png`
- 原图地址：`https://pic.leetcode.cn/1698027008-nPFLbM-image.png`

## 输入输出示例

### 示例 1

- 输入：`root = [1,2,2,3,4,4,3]`
- 输出：`true`

### 示例 2

- 输入：`root = [1,2,2,null,3,null,3]`
- 输出：`false`

## 边界条件

- `root` 可能为 `null`，空树可视为对称，返回 `true`
- 单节点树对称，返回 `true`
- 对称不仅要求「左右子树结构像」，还要求对应位置上的值相等，且左的左与右的右、左的右与右的左成镜像关系

## 多解法

### 一般解法

- 核心思路：**递归定义镜像**。两棵树（或两个子树）镜像，当且仅当：根值相等，且「左的左」与「右的右」镜像、「左的右」与「右的左」镜像。从根出发比较 `root.left` 与 `root.right` 是否互为镜像即可。
- 时间复杂度：`O(n)`（每个节点访问常数次）
- 空间复杂度：`O(h)`（递归栈深度，最坏链状为 `O(n)`）

### 经典解法

- 核心思路：**迭代 + 队列**。每次从队列取出两个待比较的节点 `a`、`b`；若都为空则继续；若只有一个为空或值不等则不对称；否则依次把 `(a.left, b.right)` 和 `(a.right, b.left)` 入队。
- 时间复杂度：`O(n)`
- 空间复杂度：最坏 `O(n)`（队列中节点数）

### 最优解法

- 递归与迭代在渐进意义上同为线性时间；通常递归代码更短，迭代避免栈溢出风险。题解代码采用**递归镜像**写法。

## 解题思路

### 思路概述

对称 = 根不动时，左子树与右子树互为镜像。比较镜像不要用「先序遍历左子树再右子树」的简单对比，而要成对比较「外侧」与「内侧」子树。

### 关键步骤

1. 特判：`root == null` 返回 `true`。
2. 定义函数 `isMirror(p, q)`：判断以 `p`、`q` 为根的子树是否镜像。
3. `isMirror`：双空为真；单空或值不等为假；否则递归 `isMirror(p.left, q.right)` 且 `isMirror(p.right, q.left)`。

## 通俗白话讲解

把树沿根竖一条镜子：左边长什么样，右边要「翻过来」长得一样才算对称。  
所以不能只比「左子树和右子树长得像不像」，要比「左边的左边」和「右边的右边」一对，「左边的右边」和「右边的左边」一对。

## 复杂度分析（对比）

- 递归：时间 `O(n)`，空间 `O(h)`
- 迭代：时间 `O(n)`，空间最坏 `O(n)`

## 代码实现

- Java：`SolutionLC101.java`
- Python：`SolutionLC101.py`
