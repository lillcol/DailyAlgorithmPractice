class Solution:
    def is_valid(self, s: str) -> bool:
        stack = []

        for ch in s:
            if ch == "(":
                stack.append(")")
            elif ch == "[":
                stack.append("]")
            elif ch == "{":
                stack.append("}")
            else:
                if not stack or stack.pop() != ch:
                    return False
        return len(stack) == 0


if __name__ == "__main__":
    solution = Solution()

    inputs = ["()", "()[]{}", "(]", "([)]", "{[]}", ""]
    expected = [True, True, False, False, True, True]

    for i in range(len(inputs)):
        result = solution.is_valid(inputs[i])
        print(f'调用: is_valid("{inputs[i]}")')
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
