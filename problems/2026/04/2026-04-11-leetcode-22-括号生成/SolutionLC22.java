import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 22：括号生成（回溯；保证任意前缀左括号不少于右括号）
 */
public class SolutionLC22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        dfs(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(int n, int open, int close, StringBuilder path, List<String> res) {
        if (path.length() == 2 * n) {
            res.add(path.toString());
            return;
        }
        // 左括号未满，可以追加 '('
        if (open < n) {
            path.append('(');
            dfs(n, open + 1, close, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        // 右括号少于左括号时才能追加 ')'，否则前缀不合法
        if (close < open) {
            path.append(')');
            dfs(n, open, close + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    private static boolean sameAsExpected(List<String> got, Set<String> expected) {
        return got.size() == expected.size() && new HashSet<>(got).equals(expected);
    }

    public static void main(String[] args) {
        SolutionLC22 solution = new SolutionLC22();

        List<String> r3 = solution.generateParenthesis(3);
        Set<String> e3 = new HashSet<>(Arrays.asList(
                "((()))", "(()())", "(())()", "()(())", "()()()"));
        System.out.println("n=3 与官方集合一致: " + sameAsExpected(r3, e3));

        List<String> r1 = solution.generateParenthesis(1);
        System.out.println("n=1: " + r1);
    }
}
