import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 17：电话号码的字母组合（回溯 / DFS）
 */
public class SolutionLC17 {

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path, res);
        return res;
    }

    private void dfs(String digits, int index, StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        int d = digits.charAt(index) - '0';
        String letters = KEYS[d];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, index + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /** 与预期做集合比对（题目允许任意顺序）。 */
    private static boolean sameAsExpected(List<String> got, Set<String> expected) {
        return got.size() == expected.size() && new HashSet<>(got).equals(expected);
    }

    public static void main(String[] args) {
        SolutionLC17 solution = new SolutionLC17();

        String[] inputs = {"", "2", "23"};
        List<Set<String>> expected = Arrays.asList(
                new HashSet<>(),
                new HashSet<>(Arrays.asList("a", "b", "c")),
                new HashSet<>(Arrays.asList(
                        "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
        );

        for (int i = 0; i < inputs.length; i++) {
            List<String> result = solution.letterCombinations(inputs[i]);
            boolean ok = sameAsExpected(result, expected.get(i));
            System.out.println("调用: letterCombinations(\"" + inputs[i] + "\")");
            System.out.println("预期结果: " + expected.get(i));
            System.out.println("实际结果: " + new HashSet<>(result));
            System.out.println("校验: " + (ok ? "OK" : "FAIL"));
            System.out.println();
        }
    }
}
