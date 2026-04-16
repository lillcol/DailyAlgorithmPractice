# LeetCode 206 - 反转链表

## 基本信息

- 日期：2026-04-16
- 难度：Easy
- 题型：链表
- 题目链接：[https://leetcode.com/problems/reverse-linked-list/](https://leetcode.com/problems/reverse-linked-list/)

## 题目描述

给你单链表的头节点 `head`，请你反转链表，并返回反转后的链表。

## 题目原图（来自 LeetCode）

<img src="images/leetcode-image-01.jpg" alt="LeetCode 题面配图 1" />

- 本地图：`images/leetcode-image-01.jpg`
- 原图地址：`https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg`

<img src="images/leetcode-image-02.jpg" alt="LeetCode 题面配图 2" />

- 本地图：`images/leetcode-image-02.jpg`
- 原图地址：`https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg`

## 输入输出示例

### 示例 1

- 输入：`head = [1,2,3,4,5]`
- 输出：`[5,4,3,2,1]`

### 示例 2

- 输入：`head = [1,2]`
- 输出：`[2,1]`

### 示例 3

- 输入：`head = []`
- 输出：`[]`

## 边界条件

- 链表节点数目范围 `[0, 5000]`
- 节点值 `-5000 <= Node.val <= 5000`
- 空链表直接返回 `null` / `None`

## 多解法

### 一般解法

- 核心思路：遍历链表把值依次入**栈**，再依次弹出建一条新链（或先收集到数组再头插）。
- 时间复杂度：`O(n)`
- 空间复杂度：`O(n)`（栈或数组）

### 经典解法

- 核心思路：**递归**。先反转 `head.next` 之后的子链表，再把 `head.next.next = head`、`head.next = null` 接回去。
- 时间复杂度：`O(n)`
- 空间复杂度：`O(n)`（递归栈深度）

### 最优解法

- 核心思路：**迭代三指针**（`prev`、`curr`、`next`）。每步把 `curr.next` 指回 `prev`，再整体右移一格。
- 时间复杂度：`O(n)`
- 空间复杂度：`O(1)`（题解代码实现此版本）

## 解题思路

### 思路概述

反转链表就是把每条边的方向掉个头。迭代时维护「已反转部分」的头 `prev` 和「待处理」的当前节点 `curr`，每次把当前节点的 `next` 改指向 `prev`，相当于从原链表头开始逐个「头插」到新世界。

### 关键步骤（迭代）

1. `prev = null`，`curr = head`。
2. 当 `curr` 非空：保存 `curr.next` 为 `nxt`；`curr.next = prev`；`prev = curr`；`curr = nxt`。
3. 结束时 `prev` 即为新头，返回 `prev`。

## 通俗白话讲解

想象一队人手拉手朝前走，你要让整个队伍调头面对面排成另一队。

每个人只改「我拉着谁」：松开右手原来那个人，改去拉已经调过头的那一段的排头。排头一开始是空气（`null`），每处理一个人，排头就换成刚调头的那个人。整条链走一遍，最后站在排头的就是原来的队尾。

## 复杂度分析（对比）

- 栈 / 数组：时间 `O(n)`，空间 `O(n)`
- 递归：时间 `O(n)`，空间 `O(n)`（栈）
- 迭代三指针：时间 `O(n)`，空间 `O(1)`

## 代码实现

- Java：`SolutionLC206.java`
- Python：`SolutionLC206.py`
