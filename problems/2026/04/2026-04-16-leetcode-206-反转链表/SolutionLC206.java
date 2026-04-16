/**
 * LeetCode 206：反转链表（迭代三指针，O(1) 额外空间）
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class SolutionLC206 {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }

    private static ListNode build(int... vals) {
        if (vals.length == 0) {
            return null;
        }
        ListNode head = new ListNode(vals[0]);
        ListNode p = head;
        for (int i = 1; i < vals.length; i++) {
            p.next = new ListNode(vals[i]);
            p = p.next;
        }
        return head;
    }

    private static String toStr(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode p = head;
        while (p != null) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(p.val);
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SolutionLC206 solution = new SolutionLC206();
        ListNode a = build(1, 2, 3, 4, 5);
        System.out.println(toStr(solution.reverseList(a)));

        ListNode b = build(1, 2);
        System.out.println(toStr(solution.reverseList(b)));

        System.out.println(toStr(solution.reverseList(null)));
    }
}
