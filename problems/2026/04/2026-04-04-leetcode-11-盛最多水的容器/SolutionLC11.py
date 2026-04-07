from typing import List


class Solution:
    def max_area(self, height: List[int]) -> int:
        """双指针，每次移动较矮一端；O(n) 时间，O(1) 额外空间。"""
        left, right = 0, len(height) - 1
        ans = 0
        while left < right:
            h = min(height[left], height[right])
            ans = max(ans, h * (right - left))
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
        return ans


if __name__ == "__main__":
    solution = Solution()

    inputs = [
        [1, 8, 6, 2, 5, 4, 8, 3, 7],
        [1, 1],
    ]
    expected = [49, 1]

    for i in range(len(inputs)):
        result = solution.max_area(inputs[i])
        print(f"调用: max_area({inputs[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
