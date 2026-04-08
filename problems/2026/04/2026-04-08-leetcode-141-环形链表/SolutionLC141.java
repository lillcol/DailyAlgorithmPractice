/**
 * LeetCode 141：环形链表（快慢指针判环，O(1) 额外空间）
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class SolutionLC141 {

    public boolean hasCycle(ListNode head) {
        // 空表或单节点无 next，不可能成环
        if (head == null || head.next == null) {
            return false;
        }
        // slow 每次 1 步，fast 每次 2 步；有环必在环内相遇
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        // fast 能走到 null，说明链表有尽头，无环
        return false;
    }

    public static void main(String[] args) {
        SolutionLC141 solution = new SolutionLC141();

        // 无环：1 -> 2 -> 3 -> null
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(3);
        System.out.println("无环链表: " + solution.hasCycle(a));

        // 有环：1 -> 2 -> 3 -> 回 2
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = new ListNode(3);
        b.next.next.next = b.next;
        System.out.println("有环链表: " + solution.hasCycle(b));
    }
}
