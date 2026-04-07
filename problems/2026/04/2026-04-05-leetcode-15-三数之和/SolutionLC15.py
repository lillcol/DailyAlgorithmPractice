from typing import List, Set


class Solution:
    def three_sum(self, nums: List[int]) -> List[List[int]]:
        """排序 + 双指针；O(n^2) 时间，O(1) 额外空间（不计结果与排序）。"""
        nums.sort()
        res: List[List[int]] = []
        n = len(nums)
        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            left, right = i + 1, n - 1
            while left < right:
                s = nums[i] + nums[left] + nums[right]
                if s == 0:
                    res.append([nums[i], nums[left], nums[right]])
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    left += 1
                    right -= 1
                elif s < 0:
                    left += 1
                else:
                    right -= 1
        return res


def _triplet_key_set(lists: List[List[int]]) -> Set[str]:
    keys = set()
    for t in lists:
        a, b, c = sorted(t)
        keys.add(f"{a},{b},{c}")
    return keys


if __name__ == "__main__":
    solution = Solution()

    inputs = [
        [-1, 0, 1, 2, -1, -4],
        [0, 1, 1],
        [0, 0, 0],
    ]
    expected_keys = [
        {"-1,-1,2", "-1,0,1"},
        set(),
        {"0,0,0"},
    ]

    for i, arr in enumerate(inputs):
        # three_sum 会原地排序，用副本避免打乱上面定义的用例列表
        result = solution.three_sum(list(arr))
        got = _triplet_key_set(result)
        ok = got == expected_keys[i]
        print(f"调用: three_sum({arr})")
        print(f"预期结果: {expected_keys[i]}")
        print(f"实际结果: {got}")
        print(f"校验: {'OK' if ok else 'FAIL'}")
        print()
