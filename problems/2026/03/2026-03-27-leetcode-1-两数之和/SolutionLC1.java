import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;

public class SolutionLC1 {

    public int[] twoSum(int[] nums, int target) {
        // 哈希表：记录已经遍历过的数字和它的下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 目标值减当前值，得到当前数字想要的“搭档”
            int need = target - nums[i];

            // 如果搭档已经出现过，直接返回搭档下标 + 当前下标
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }

            // 否则先记录当前数字，给后续元素做搭档
            map.put(nums[i], i);
        }

        // 题目通常保证有解，这里是保底返回
        return new int[0];
    }

    public static void main(String[] args) {
        SolutionLC1 solution = new SolutionLC1();

        int[][] inputs = {
                {2, 7, 11, 15},
                {3, 2, 4},
                {3, 3}
        };
        int[] targets = {9, 6, 6};
        int[][] expected = {
                {0, 1},
                {1, 2},
                {0, 1}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] result = solution.twoSum(inputs[i], targets[i]);
            System.out.println("调用: twoSum(" + Arrays.toString(inputs[i]) + ", " + targets[i] + ")");
            System.out.println("预期结果: " + Arrays.toString(expected[i]));
            System.out.println("实际结果: " + Arrays.toString(result));
            System.out.println();
        }
    }
}
