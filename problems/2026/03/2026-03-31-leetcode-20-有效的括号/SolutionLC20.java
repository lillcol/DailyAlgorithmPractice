import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionLC20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
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
            System.out.println("调用: isValid(\"" + inputs[i] + "\")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
