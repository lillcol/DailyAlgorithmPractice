from typing import List


class Solution:
    def two_sum(self, nums: List[int], target: int) -> List[int]:
        # 哈希表：记录“数字 -> 下标”
        seen = {}

        for i, num in enumerate(nums):
            # 当前数字需要的搭档
            need = target - num

            # 找到搭档则直接返回答案
            if need in seen:
                return [seen[need], i]

            # 记录当前数字，供后续匹配
            seen[num] = i

        # 保底返回（按题意通常不会触发）
        return []
