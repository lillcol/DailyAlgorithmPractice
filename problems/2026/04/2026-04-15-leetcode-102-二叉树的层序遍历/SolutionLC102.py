from __future__ import annotations

from collections import deque
from typing import List, Optional


class TreeNode:
    def __init__(
        self,
        val: int = 0,
        left: Optional["TreeNode"] = None,
        right: Optional["TreeNode"] = None,
    ):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    """LeetCode 102：二叉树的层序遍历（BFS）"""

    def level_order(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root is None:
            return []
        res: List[List[int]] = []
        q: deque[TreeNode] = deque([root])
        while q:
            sz = len(q)
            level: List[int] = []
            for _ in range(sz):
                node = q.popleft()
                level.append(node.val)
                if node.left is not None:
                    q.append(node.left)
                if node.right is not None:
                    q.append(node.right)
            res.append(level)
        return res


if __name__ == "__main__":
    sol = Solution()
    tree = TreeNode(
        3,
        TreeNode(9),
        TreeNode(20, TreeNode(15), TreeNode(7)),
    )
    assert sol.level_order(tree) == [[3], [9, 20], [15, 7]]
    assert sol.level_order(TreeNode(1)) == [[1]]
    assert sol.level_order(None) == []
    print("示例均通过")
