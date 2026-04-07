import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 15：三数之和（排序 + 双指针，O(n^2)）
 *
 * <p>固定最小下标 i，在右侧用双指针找两数之和为 -nums[i]；排序后可通过跳过相邻相同值避免重复三元组。
 */
public class SolutionLC15 {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // 同一数值作「第一个数」只处理一次
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 收缩并跳过与当前 left/right 相同的值，防止重复三元组
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    /** 将三元组集合规范成有序字符串集合，便于与预期做内容比对（忽略三元组顺序）。 */
    private static Set<String> tripletKeySet(List<List<Integer>> lists) {
        Set<String> keys = new HashSet<>();
        for (List<Integer> t : lists) {
            int[] arr = {t.get(0), t.get(1), t.get(2)};
            Arrays.sort(arr);
            keys.add(arr[0] + "," + arr[1] + "," + arr[2]);
        }
        return keys;
    }

    public static void main(String[] args) {
        SolutionLC15 solution = new SolutionLC15();

        int[][] inputs = {
                {-1, 0, 1, 2, -1, -4},
                {0, 1, 1},
                {0, 0, 0}
        };
        List<Set<String>> expectedKeys = Arrays.asList(
                new HashSet<>(Arrays.asList("-1,-1,2", "-1,0,1")),
                new HashSet<>(),
                new HashSet<>(Arrays.asList("0,0,0"))
        );

        for (int i = 0; i < inputs.length; i++) {
            // threeSum 内部会排序，传入副本以免打乱 main 里的原始用例展示
            int[] copy = Arrays.copyOf(inputs[i], inputs[i].length);
            List<List<Integer>> result = solution.threeSum(copy);
            Set<String> got = tripletKeySet(result);
            boolean ok = got.equals(expectedKeys.get(i));
            System.out.println("调用: threeSum(" + Arrays.toString(inputs[i]) + ")");
            System.out.println("预期结果: " + expectedKeys.get(i));
            System.out.println("实际结果: " + got);
            System.out.println("校验: " + (ok ? "OK" : "FAIL"));
            System.out.println();
        }
    }
}
