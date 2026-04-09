from __future__ import annotations

from typing import Optional


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
    """LeetCode 101：对称二叉树（递归镜像）"""

    def is_symmetric(self, root: Optional[TreeNode]) -> bool:
        if root is None:
            return True
        return self._is_mirror(root.left, root.right)

    def _is_mirror(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if p is None and q is None:
            return True
        if p is None or q is None:
            return False
        if p.val != q.val:
            return False
        return self._is_mirror(p.left, q.right) and self._is_mirror(p.right, q.left)


if __name__ == "__main__":
    sol = Solution()
    sym = TreeNode(
        1,
        TreeNode(2, TreeNode(3), TreeNode(4)),
        TreeNode(2, TreeNode(4), TreeNode(3)),
    )
    print("对称:", sol.is_symmetric(sym))
    asym = TreeNode(
        1,
        TreeNode(2, None, TreeNode(3)),
        TreeNode(2, None, TreeNode(3)),
    )
    print("非对称:", sol.is_symmetric(asym))
