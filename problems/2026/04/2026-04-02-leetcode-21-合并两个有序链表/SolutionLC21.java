import java.util.Arrays;

/**
 * LeetCode 21：合并两个有序链表（哑结点 + 迭代）
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

public class SolutionLC21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 哑结点：避免单独处理「结果头结点」是谁
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            // 取较小头接在结果链末尾；相等时先接 list1，保证稳定与题意一致
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 至少一条链还有剩余，整体接上即可（剩余部分已有序）
        cur.next = list1 != null ? list1 : list2;

        return dummy.next;
    }

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
        SolutionLC21 solution = new SolutionLC21();

        int[][] a = {
                {1, 2, 4},
                {},
                {}
        };
        int[][] b = {
                {1, 3, 4},
                {},
                {0}
        };
        int[][] expected = {
                {1, 1, 2, 3, 4, 4},
                {},
                {0}
        };

        for (int i = 0; i < a.length; i++) {
            ListNode l1 = fromArray(a[i]);
            ListNode l2 = fromArray(b[i]);
            ListNode merged = solution.mergeTwoLists(l1, l2);
            int[] actual = toArray(merged);
            System.out.println("调用: mergeTwoLists(" + Arrays.toString(a[i]) + ", " + Arrays.toString(b[i]) + ")");
            System.out.println("预期结果: " + Arrays.toString(expected[i]));
            System.out.println("实际结果: " + Arrays.toString(actual));
            System.out.println();
        }
    }
}
