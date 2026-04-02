/**
 * LeetCode 5：最长回文子串（中心扩展，O(n^2)）
 */
public class SolutionLC5 {

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        // 最长回文在 s 中的起始下标与长度
        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // 每个下标既可能是奇数长度回文的中心，也可能是偶数回文中心的左端点
            // 奇数长度：中心为一个字符
            int len1 = expandAroundCenter(s, i, i);
            // 偶数长度：中心为两个字符之间
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                // 回文左端点：中心向左偏移 (len-1)/2
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 从 [left, right] 向两侧扩展，返回回文长度（至少为 right-left+1）。
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 循环退出时多扩了一步，长度为 right - left - 1
        return right - left - 1;
    }

    private static boolean isPalindrome(String t) {
        int l = 0;
        int r = t.length() - 1;
        while (l < r) {
            if (t.charAt(l++) != t.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionLC5 solution = new SolutionLC5();

        String[] inputs = {"babad", "cbbd", "a", "ac", ""};
        int[] expectedLens = {3, 2, 1, 1, 0};
        String[] expectedDesc = {
                "bab 或 aba",
                "bb",
                "a",
                "a 或 c",
                "（空串）"
        };

        for (int i = 0; i < inputs.length; i++) {
            String in = inputs[i];
            String result = solution.longestPalindrome(in);
            boolean ok = result.length() == expectedLens[i]
                    && (expectedLens[i] == 0 ? result.isEmpty() : in.contains(result))
                    && isPalindrome(result);
            System.out.println("调用: longestPalindrome(\"" + in + "\")");
            System.out.println("预期结果: " + expectedDesc[i] + "（长度 " + expectedLens[i] + "）");
            System.out.println("实际结果: " + (result.isEmpty() ? "（空串）" : result));
            System.out.println("校验: " + (ok ? "OK" : "FAIL"));
            System.out.println();
        }
    }
}
