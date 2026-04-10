from typing import List


class Solution:
    """LeetCode 121：买卖股票的最佳时机（一次扫描）"""

    def max_profit(self, prices: List[int]) -> int:
        if not prices:
            return 0
        min_price = prices[0]
        best = 0
        for price in prices[1:]:
            best = max(best, price - min_price)
            min_price = min(min_price, price)
        return best


if __name__ == "__main__":
    sol = Solution()
    print("示例1:", sol.max_profit([7, 1, 5, 3, 6, 4]))
    print("示例2:", sol.max_profit([7, 6, 4, 3, 1]))
    print("额外:", sol.max_profit([2, 4, 1]))
