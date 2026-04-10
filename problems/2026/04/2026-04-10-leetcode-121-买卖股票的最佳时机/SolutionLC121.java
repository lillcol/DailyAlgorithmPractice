/**
 * LeetCode 121：买卖股票的最佳时机（一次扫描，维护历史最低买入价）
 */
public class SolutionLC121 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 到目前为止的最低买入价；利润 = 当日价 - 历史最低买入价
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        SolutionLC121 solution = new SolutionLC121();

        int[] a = {7, 1, 5, 3, 6, 4};
        System.out.println("示例1 预期 5, 实际 " + solution.maxProfit(a));

        int[] b = {7, 6, 4, 3, 1};
        System.out.println("示例2 预期 0, 实际 " + solution.maxProfit(b));

        int[] c = {2, 4, 1};
        System.out.println("预期 2, 实际 " + solution.maxProfit(c));
    }
}
