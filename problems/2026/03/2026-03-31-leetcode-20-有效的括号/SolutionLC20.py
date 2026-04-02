class Solution:
    def is_valid(self, s: str) -> bool:
        # 栈里存“期望匹配的右括号”，避免后续再做映射判断
        stack = []

        for ch in s:
            # 左括号入栈时，直接压入它对应的右括号
            if ch == "(":
                stack.append(")")
            elif ch == "[":
                stack.append("]")
            elif ch == "{":
                stack.append("}")
            else:
                # 右括号出现时：栈空（无可匹配左括号）或与栈顶不一致都无效
                if not stack or stack.pop() != ch:
                    return False
        # 全部遍历后栈为空，才表示所有括号都完成匹配
        return len(stack) == 0


if __name__ == "__main__":
    solution = Solution()

    inputs = ["()", "()[]{}", "(]", "([)]", "{[]}", ""]
    expected = [True, True, False, False, True, True]

    for i in range(len(inputs)):
        result = solution.is_valid(inputs[i])
        # 统一输出格式：调用 / 预期结果 / 实际结果
        print(f'调用: is_valid("{inputs[i]}")')
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {result}")
        print()
