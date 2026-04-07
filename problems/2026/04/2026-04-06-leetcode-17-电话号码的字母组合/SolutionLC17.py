from typing import List, Set


class Solution:
    KEYS = ("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

    def letter_combinations(self, digits: str) -> List[str]:
        """回溯；时间 O(4^k * k)，递归栈 O(k)。"""
        if not digits:
            return []
        res: List[str] = []
        path: List[str] = []

        def dfs(index: int) -> None:
            if index == len(digits):
                res.append("".join(path))
                return
            for ch in self.KEYS[int(digits[index])]:
                path.append(ch)
                dfs(index + 1)
                path.pop()

        dfs(0)
        return res


if __name__ == "__main__":
    solution = Solution()

    inputs = ["", "2", "23"]
    expected: List[Set[str]] = [
        set(),
        {"a", "b", "c"},
        {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"},
    ]

    for i, s in enumerate(inputs):
        result = solution.letter_combinations(s)
        got = set(result)
        ok = got == expected[i] and len(result) == len(got)
        print(f'调用: letter_combinations("{s}")')
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {got}")
        print(f"校验: {'OK' if ok else 'FAIL'}")
        print()
