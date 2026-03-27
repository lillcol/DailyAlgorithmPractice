import java.util.HashMap;
import java.util.Map;

public class SolutionLC3 {

    public int lengthOfLongestSubstring(String s) {
        // 记录字符最近一次出现的位置：字符 -> 下标
        Map<Character, Integer> lastSeen = new HashMap<>();

        // left 表示当前滑动窗口左边界，maxLen 记录历史最大长度
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // 如果当前字符在窗口中已经出现过，需要收缩左边界
            if (lastSeen.containsKey(current) && lastSeen.get(current) >= left) {
                // 跳到重复字符上次出现位置的后一位，保证窗口内无重复
                left = lastSeen.get(current) + 1;
            }

            // 更新当前字符最新出现位置
            lastSeen.put(current, right);

            // 用当前窗口长度更新答案
            int currentLen = right - left + 1;
            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        SolutionLC3 solution = new SolutionLC3();

        String[] inputs = {"abcabcbb", "bbbbb", "pwwkew", "", "dvdf"};
        int[] expected = {3, 1, 3, 0, 3};

        for (int i = 0; i < inputs.length; i++) {
            int result = solution.lengthOfLongestSubstring(inputs[i]);
            System.out.println("调用: lengthOfLongestSubstring(\"" + inputs[i] + "\")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
