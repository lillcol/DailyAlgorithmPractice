import java.util.Arrays;

/**
 * LeetCode 11：盛最多水的容器（双指针，O(n)）
 *
 * <p>面积 = 底 × 高，高由左右两端较矮的垂线决定。每次向内收窄底边时，只有换掉较矮一侧才可能提高「有效高度」，
 * 故双指针每次移动高度较小的一端；相等时移动任一侧均可。
 */
public class SolutionLC11 {

    /**
     * @param height 垂线高度，长度至少为 2
     * @return 能容纳的最大水量（面积）
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        while (left < right) {
            // 水量由短板决定；宽度为 right - left
            int h = Math.min(height[left], height[right]);
            ans = Math.max(ans, h * (right - left));

            // 移动较矮一侧：保留可能更高的边界，尝试更大面积
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SolutionLC11 solution = new SolutionLC11();

        int[][] inputs = {
                {1, 8, 6, 2, 5, 4, 8, 3, 7},
                {1, 1}
        };
        int[] expected = {49, 1};

        for (int i = 0; i < inputs.length; i++) {
            int result = solution.maxArea(inputs[i]);
            System.out.println("调用: maxArea(" + Arrays.toString(inputs[i]) + ")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
