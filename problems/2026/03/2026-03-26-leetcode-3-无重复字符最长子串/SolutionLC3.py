class Solution:
    def length_of_longest_substring(self, s: str) -> int:
        # 记录字符最近一次出现的位置：字符 -> 下标
        last_seen = {}

        # left 是窗口左边界，max_len 是最终答案
        left = 0
        max_len = 0

        for right, ch in enumerate(s):
            # 如果字符在当前窗口内重复出现，需要移动左边界
            if ch in last_seen and last_seen[ch] >= left:
                # 直接跳到重复字符上次位置后一位
                left = last_seen[ch] + 1

            # 更新当前字符的最新位置
            last_seen[ch] = right

            # 计算当前无重复窗口长度并更新答案
            current_len = right - left + 1
            if current_len > max_len:
                max_len = current_len

        return max_len


if __name__ == "__main__":
    solution = Solution()

    inputs = ["abcabcbb", "bbbbb", "pwwkew", "", "dvdf"]
    expected = [3, 1, 3, 0, 3]

    for i in range(len(inputs)):
        result = solution.length_of_longest_substring(inputs[i])
        print(f'调用: length_of_longest_substring("{inputs[i]}")')
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
