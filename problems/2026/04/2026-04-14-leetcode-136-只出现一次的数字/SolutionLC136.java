/**
 * LeetCode 136：只出现一次的数字（全员异或，成对抵消）
 */
public class SolutionLC136 {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            // 相同数异或为 0；0 与任何数异或不变
            ans ^= x;
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionLC136 solution = new SolutionLC136();
        System.out.println("示例1 预期 1: " + solution.singleNumber(new int[]{2, 2, 1}));
        System.out.println("示例2 预期 4: " + solution.singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println("示例3 预期 1: " + solution.singleNumber(new int[]{1}));
    }
}
