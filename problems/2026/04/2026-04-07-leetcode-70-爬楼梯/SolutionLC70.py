class Solution:
    def climb_stairs(self, n: int) -> int:
        """滚动 DP：到第 i 阶 = 到 i-1 与到 i-2 之和。"""
        if n <= 2:
            return n
        prev2, prev1 = 1, 1
        for _ in range(2, n + 1):
            cur = prev1 + prev2
            prev2, prev1 = prev1, cur
        return prev1


if __name__ == "__main__":
    solution = Solution()

    inputs = [1, 2, 3, 5, 8]
    expected = [1, 2, 3, 8, 34]

    for i in range(len(inputs)):
        result = solution.climb_stairs(inputs[i])
        print(f"调用: climb_stairs({inputs[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
