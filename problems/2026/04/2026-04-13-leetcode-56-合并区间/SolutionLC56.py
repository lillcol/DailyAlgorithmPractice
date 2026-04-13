from typing import List


class Solution:
    """LeetCode 56：合并区间（排序 + 一次扫描）"""

    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals:
            return []
        intervals.sort(key=lambda x: x[0])
        res = [[intervals[0][0], intervals[0][1]]]
        for start, end in intervals[1:]:
            if res[-1][1] < start:
                res.append([start, end])
            else:
                res[-1][1] = max(res[-1][1], end)
        return res


if __name__ == "__main__":
    sol = Solution()
    assert sol.merge([[1, 3], [2, 6], [8, 10], [15, 18]]) == [[1, 6], [8, 10], [15, 18]]
    assert sol.merge([[1, 4], [4, 5]]) == [[1, 5]]
    print("示例1、示例2 通过")
