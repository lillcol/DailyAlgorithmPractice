import java.util.Arrays;

/**
 * LeetCode 2：两数相加（链表逆序存数位）
 *
 * <p>链表从表头到表尾表示从低位到高位，与竖式加法顺序一致，因此可以一边遍历一边进位，
 * 无需先把链表转成数字（大数会溢出，也不必要）。
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SolutionLC2 {

    /**
     * 模拟竖式加法：同步移动 l1、l2，维护进位 carry。
     *
     * @param l1 加数链表（低位在头）
     * @param l2 加数链表（低位在头）
     * @return 和的链表（低位在头）
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哑结点：统一在 cur.next 上挂新节点，避免单独处理「结果头结点」为空的情况
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 上一轮相加向高位进的位；可能为 0～1（两数字位各 ≤9，故最多 9+9+1=19）
        int carry = 0;

        // 条件里带上 carry：两条链都走完但若仍有进位，还要再补一位（例如 5+5=10）
        while (l1 != null || l2 != null || carry != 0) {
            // 某一侧已走完时，该位按 0 处理，这样长短不一的两条链无需先算长度
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummy.next;
    }

    /** 本地测试用：数组按「低位在前」顺序建链，与题目约定一致。 */
    private static ListNode fromArray(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        ListNode head = new ListNode(digits[0]);
        ListNode cur = head;
        for (int i = 1; i < digits.length; i++) {
            cur.next = new ListNode(digits[i]);
            cur = cur.next;
        }
        return head;
    }

    /** 本地测试用：把结果链表还原成数组，便于和预期输出对比。 */
    private static int[] toArray(ListNode head) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) {
            len++;
        }
        int[] out = new int[len];
        int i = 0;
        for (ListNode p = head; p != null; p = p.next) {
            out[i++] = p.val;
        }
        return out;
    }

    public static void main(String[] args) {
        SolutionLC2 solution = new SolutionLC2();

        int[][] list1 = {
                {2, 4, 3},
                {0},
                {9, 9, 9, 9, 9, 9, 9}
        };
        int[][] list2 = {
                {5, 6, 4},
                {0},
                {9, 9, 9, 9}
        };
        int[][] expected = {
                {7, 0, 8},
                {0},
                {8, 9, 9, 9, 0, 0, 0, 1}
        };

        for (int i = 0; i < list1.length; i++) {
            ListNode a = fromArray(list1[i]);
            ListNode b = fromArray(list2[i]);
            ListNode result = solution.addTwoNumbers(a, b);
            int[] actual = toArray(result);
            System.out.println("调用: addTwoNumbers(" + Arrays.toString(list1[i]) + ", " + Arrays.toString(list2[i]) + ")");
            System.out.println("预期结果: " + Arrays.toString(expected[i]));
            System.out.println("实际结果: " + Arrays.toString(actual));
            System.out.println();
        }
    }
}
