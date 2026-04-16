from __future__ import annotations

from typing import List, Optional


class ListNode:
    def __init__(self, val: int = 0, next: Optional["ListNode"] = None):
        self.val = val
        self.next = next


class Solution:
    """LeetCode 206：反转链表（迭代三指针）"""

    def reverse_list(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev: Optional[ListNode] = None
        curr = head
        while curr is not None:
            nxt = curr.next
            curr.next = prev
            prev = curr
            curr = nxt
        return prev


def _from_vals(vals: List[int]) -> Optional[ListNode]:
    if not vals:
        return None
    head = ListNode(vals[0])
    p = head
    for x in vals[1:]:
        p.next = ListNode(x)
        p = p.next
    return head


def _to_vals(head: Optional[ListNode]) -> List[int]:
    out: List[int] = []
    while head is not None:
        out.append(head.val)
        head = head.next
    return out


if __name__ == "__main__":
    sol = Solution()
    assert _to_vals(sol.reverse_list(_from_vals([1, 2, 3, 4, 5]))) == [5, 4, 3, 2, 1]
    assert _to_vals(sol.reverse_list(_from_vals([1, 2]))) == [2, 1]
    assert _to_vals(sol.reverse_list(None)) == []
    print("示例均通过")
