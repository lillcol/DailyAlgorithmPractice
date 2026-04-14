from typing import List


class Solution:
    """LeetCode 136：只出现一次的数字（异或）"""

    def single_number(self, nums: List[int]) -> int:
        ans = 0
        for x in nums:
            ans ^= x
        return ans


if __name__ == "__main__":
    sol = Solution()
    assert sol.single_number([2, 2, 1]) == 1
    assert sol.single_number([4, 1, 2, 1, 2]) == 4
    assert sol.single_number([1]) == 1
    print("示例均通过")
