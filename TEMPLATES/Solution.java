import java.util.HashMap;
import java.util.Map;

/**
 * 题解模板（Java 版）
 *
 * 使用说明：
 * 1) 通过脚本创建后，类名会自动替换为 SolutionLC<题号>（如 SolutionLC3）。
 * 2) 将方法名改为当前题目对应的方法名。
 * 3) 补充关键注释：思路、边界、复杂度。
 */
public class Solution {

    /**
     * 示例方法：Two Sum
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 返回两个下标
     */
    public int[] twoSum(int[] nums, int target) {
        // key: 数值，value: 该数值对应的下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 目标值减去当前值，得到“需要的配对值”
            int need = target - nums[i];

            // 如果之前已经见过这个配对值，说明找到答案
            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }

            // 记录当前值及其下标，供后续元素配对使用
            map.put(nums[i], i);
        }

        // 根据多数题目约束通常不会走到这里；保底返回空数组
        return new int[0];
    }
}
