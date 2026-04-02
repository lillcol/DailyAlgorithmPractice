class Solution:
    def longest_palindrome(self, s: str) -> str:
        """中心扩展：O(n^2) 时间，O(1) 额外空间（不计返回串）。"""
        if not s:
            return ""

        def expand(left: int, right: int) -> int:
            # 向两侧扩展，退出时多走一步，长度为 right - left - 1
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return right - left - 1

        start = 0
        max_len = 0
        for i in range(len(s)):
            # 奇数中心 (i,i)、偶数中心 (i,i+1) 各扩一次
            len1 = expand(i, i)
            len2 = expand(i, i + 1)
            cur = max(len1, len2)
            if cur > max_len:
                max_len = cur
                start = i - (cur - 1) // 2
        return s[start : start + max_len]


def _is_palindrome(t: str) -> bool:
    return t == t[::-1]


if __name__ == "__main__":
    solution = Solution()

    inputs = ["babad", "cbbd", "a", "ac", ""]
    expected_lens = [3, 2, 1, 1, 0]
    expected_desc = ["bab 或 aba", "bb", "a", "a 或 c", "（空串）"]

    for i, inp in enumerate(inputs):
        result = solution.longest_palindrome(inp)
        ok = (
            len(result) == expected_lens[i]
            and (expected_lens[i] == 0 or result in inp)
            and _is_palindrome(result)
        )
        print(f'调用: longest_palindrome("{inp}")')
        print(f"预期结果: {expected_desc[i]}（长度 {expected_lens[i]}）")
        display = "（空串）" if result == "" else result
        print(f"实际结果: {display}")
        print(f"校验: {'OK' if ok else 'FAIL'}")
        print()
