/**
 * LeetCode 70：爬楼梯（滚动 DP / 斐波那契思想，O(n) 时间 O(1) 空间）
 */
public class SolutionLC70 {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        // 分别表示到达 i-2、i-1 阶的方案数，逐轮向后滚动
        int prev2 = 1;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        SolutionLC70 solution = new SolutionLC70();

        int[] inputs = {1, 2, 3, 5, 8};
        int[] expected = {1, 2, 3, 8, 34};

        for (int i = 0; i < inputs.length; i++) {
            int result = solution.climbStairs(inputs[i]);
            System.out.println("调用: climbStairs(" + inputs[i] + ")");
            System.out.println("预期结果: " + expected[i]);
            System.out.println("实际结果: " + result);
            System.out.println();
        }
    }
}
