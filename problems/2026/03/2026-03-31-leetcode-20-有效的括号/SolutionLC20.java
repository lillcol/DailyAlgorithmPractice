import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionLC20 {
    public boolean isValid(String s) {
        // 栈里存“期待匹配的右括号”，而不是左括号，后续比较更直接
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 遇到左括号就压入对应的“目标右括号”
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                // 遇到右括号时：栈空说明没有可匹配左括号；或与栈顶不一致都无效
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        // 所有字符处理完后，栈必须为空才表示全部匹配完成
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        SolutionLC20 solution = new SolutionLC20();

        String[] inputs = {
                "()",
                "()[]{}",
                "(]",
                "([)]",
                "{[]}",
                ""
        };
        boolean[] expected = {
                true,
                true,
                false,
                false,
                true,
                true
        };

        for (int i = 0; i < inputs.length; i++) {
            boolean result = solution.isValid(inputs[i]);
            // 统一输出格式：调用 / 预期结果 / 实际结果
            System.out.println("调用: isValid(\"" + inputs[i] + "\")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
