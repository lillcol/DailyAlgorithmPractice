import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56：合并区间（按左端点排序后线性合并）
 */
public class SolutionLC56 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        // 按区间左端点排序，保证只需与「结果中最后一个区间」比较
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] cur : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < cur[0]) {
                merged.add(new int[]{cur[0], cur[1]});
            } else {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], cur[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    private static boolean deepEquals(int[][] a, int[][] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length || a[i][0] != b[i][0] || a[i][1] != b[i][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SolutionLC56 solution = new SolutionLC56();

        int[][] in1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] out1 = {{1, 6}, {8, 10}, {15, 18}};
        System.out.println("示例1: " + deepEquals(solution.merge(in1), out1));

        int[][] in2 = {{1, 4}, {4, 5}};
        int[][] out2 = {{1, 5}};
        System.out.println("示例2: " + deepEquals(solution.merge(in2), out2));
    }
}
