from typing import Optional


class ListNode:
    def __init__(self, val: int = 0, next: Optional["ListNode"] = None):
        self.val = val
        self.next = next


class Solution:
    """
    LeetCode 141：环形链表（快慢指针，O(1) 额外空间）
    """

    def has_cycle(self, head: Optional[ListNode]) -> bool:
        if head is None or head.next is None:
            return False
        slow = head
        fast = head
        while fast is not None and fast.next is not None:
            slow = slow.next
            fast = fast.next.next
            if slow is fast:
                return True
        return False


if __name__ == "__main__":
    sol = Solution()

    n1 = ListNode(1, ListNode(2, ListNode(3)))
    print("无环:", sol.has_cycle(n1))

    c = ListNode(1)
    c.next = ListNode(2)
    c.next.next = ListNode(3)
    c.next.next.next = c.next
    print("有环:", sol.has_cycle(c))
