import java.util.Arrays;

/**
 * LeetCode 53：最大子数组和
 *
 * <p>使用 Kadane 算法（也可看成「前缀和 + 最小前缀」或一维 DP）。
 * 核心观察：以位置 i 结尾的最优连续子数组，
 * 要么只含 nums[i]，要么在「以 i-1 结尾的最优子数组」后面接上 nums[i]；若前者和已经是负的，接上只会更差，
 * 不如从 nums[i] 重新开一段。
 *
 * <p>时间复杂度 O(n)，额外空间 O(1)。题目保证至少一个元素，故无需处理空数组。
 */
public class SolutionLC53 {

    /**
     * 求连续子数组的最大和。
     *
     * <p>状态定义：{@code cur} 表示「必须以当前下标元素结尾」的连续子数组的最大和。
     * 转移：{@code cur = max(nums[i], cur + nums[i])} —— 新开一段或延续上一段。
     * 答案：遍历过程中所有 {@code cur} 的最大值（因为最优子数组必有某个结尾下标）。
     *
     * @param nums 非空整数数组
     * @return 连续子数组元素和的最大值
     */
    public int maxSubArray(int[] nums) {
        // 第一个元素：此时「以该位置结尾」的唯一子数组就是 nums[0] 本身
        int cur = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 若 cur 为负，cur + nums[i] < nums[i]，会自然选 nums[i]，等价于丢弃前面的包袱
            cur = Math.max(nums[i], cur + nums[i]);
            // 全局最优可能出现在任意结尾位置，不能只盯最后一个 cur
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionLC53 solution = new SolutionLC53();

        int[][] inputs = {
                {-2, 1, -3, 4, -1, 2, 1, -5, 4},
                {1},
                {5, 4, -1, 7, 8}
        };
        int[] expected = {6, 1, 23};

        for (int i = 0; i < inputs.length; i++) {
            int result = solution.maxSubArray(inputs[i]);
            // 与仓库内其他题一致：调用 / 预期结果 / 实际结果
            System.out.println("调用: maxSubArray(" + Arrays.toString(inputs[i]) + ")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
