from typing import List, Optional


class ListNode:
    """单链表节点。"""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None):
        self.val = val
        self.next = next


class Solution:
    def merge_two_lists(
        self, list1: Optional[ListNode], list2: Optional[ListNode]
    ) -> Optional[ListNode]:
        """哑结点 + 迭代：O(m+n) 时间，O(1) 额外空间。"""
        dummy = ListNode(0)
        cur = dummy

        while list1 is not None and list2 is not None:
            if list1.val <= list2.val:
                cur.next = list1
                list1 = list1.next
            else:
                cur.next = list2
                list2 = list2.next
            cur = cur.next

        cur.next = list1 if list1 is not None else list2
        return dummy.next


def _from_array(digits: List[int]) -> Optional[ListNode]:
    if not digits:
        return None
    head = ListNode(digits[0])
    cur = head
    for v in digits[1:]:
        cur.next = ListNode(v)
        cur = cur.next
    return head


def _to_array(head: Optional[ListNode]) -> List[int]:
    out = []
    while head is not None:
        out.append(head.val)
        head = head.next
    return out


if __name__ == "__main__":
    solution = Solution()

    list_a = [[1, 2, 4], [], []]
    list_b = [[1, 3, 4], [], [0]]
    expected = [[1, 1, 2, 3, 4, 4], [], [0]]

    for i in range(len(list_a)):
        l1 = _from_array(list_a[i])
        l2 = _from_array(list_b[i])
        merged = solution.merge_two_lists(l1, l2)
        actual = _to_array(merged)
        print(f"调用: merge_two_lists({list_a[i]}, {list_b[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {actual}")
        print()
