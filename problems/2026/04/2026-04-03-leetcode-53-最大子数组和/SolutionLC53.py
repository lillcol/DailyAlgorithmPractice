from typing import List


class Solution:
    def max_sub_array(self, nums: List[int]) -> int:
        """Kadane：O(n) 时间，O(1) 额外空间。"""
        cur = nums[0]
        ans = nums[0]
        for x in nums[1:]:
            # 要么接上上一段，要么从 x 重新开始（上一段和为负则不如丢弃）
            cur = max(x, cur + x)
            ans = max(ans, cur)
        return ans


if __name__ == "__main__":
    solution = Solution()

    inputs = [
        [-2, 1, -3, 4, -1, 2, 1, -5, 4],
        [1],
        [5, 4, -1, 7, 8],
    ]
    expected = [6, 1, 23]

    for i in range(len(inputs)):
        result = solution.max_sub_array(inputs[i])
        print(f"调用: max_sub_array({inputs[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
