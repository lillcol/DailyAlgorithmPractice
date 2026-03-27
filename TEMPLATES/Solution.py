from typing import List


class Solution:
    """
    题解模板（Python 版）

    使用说明：
    1) 类名保持为 Solution，便于统一管理。
    2) 方法名改为当前题目对应的方法名。
    3) 补充关键注释：思路、边界、复杂度。
    """

    def two_sum(self, nums: List[int], target: int) -> List[int]:
        # key: 数值，value: 下标
        seen = {}

        for i, num in enumerate(nums):
            # 计算当前数字需要的“配对值”
            need = target - num

            # 如果这个配对值之前出现过，直接返回答案
            if need in seen:
                return [seen[need], i]

            # 记录当前值，供后面的元素做配对
            seen[num] = i

        # 保底返回，避免无解时抛错（具体可按题目要求调整）
        return []
