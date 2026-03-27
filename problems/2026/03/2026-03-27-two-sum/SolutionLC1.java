import java.util.HashMap;
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
}
