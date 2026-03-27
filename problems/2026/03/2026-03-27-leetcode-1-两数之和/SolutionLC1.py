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


if __name__ == "__main__":
    solution = Solution()

    inputs = [
        [2, 7, 11, 15],
        [3, 2, 4],
        [3, 3],
    ]
    targets = [9, 6, 6]
    expected = [
        [0, 1],
        [1, 2],
        [0, 1],
    ]

    for i in range(len(inputs)):
        result = solution.two_sum(inputs[i], targets[i])
        print(f"调用: two_sum({inputs[i]}, {targets[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
