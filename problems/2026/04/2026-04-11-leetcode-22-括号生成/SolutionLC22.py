from typing import List, Set


class Solution:
    """LeetCode 22：括号生成（回溯）"""

    def generate_parenthesis(self, n: int) -> List[str]:
        if n <= 0:
            return []
        res: List[str] = []
        path: List[str] = []

        def dfs(open_count: int, close_count: int) -> None:
            if len(path) == 2 * n:
                res.append("".join(path))
                return
            if open_count < n:
                path.append("(")
                dfs(open_count + 1, close_count)
                path.pop()
            if close_count < open_count:
                path.append(")")
                dfs(open_count, close_count + 1)
                path.pop()

        dfs(0, 0)
        return res


if __name__ == "__main__":
    sol = Solution()
    got3 = set(sol.generate_parenthesis(3))
    exp3: Set[str] = {"((()))", "(()())", "(())()", "()(())", "()()()"}
    print("n=3 与官方集合一致:", got3 == exp3)
    print("n=1:", sol.generate_parenthesis(1))
