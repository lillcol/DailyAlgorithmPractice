from typing import List, Optional


class ListNode:
    """单链表节点；题目约定数位逆序存储，表头是个位。"""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None):
        self.val = val
        self.next = next


class Solution:
    def add_two_numbers(
        self, l1: Optional[ListNode], l2: Optional[ListNode]
    ) -> Optional[ListNode]:
        """同步遍历两链并维护进位，时间 O(max(m,n))，额外指针 O(1)。"""
        # 哑结点：始终在 cur.next 接新节点，结果头结点也用同一套逻辑生成
        dummy = ListNode(0)
        cur = dummy
        carry = 0

        # 必须带上 carry：两链都遍历完后仍可能多出一位进位
        while l1 is not None or l2 is not None or carry != 0:
            x = l1.val if l1 is not None else 0
            y = l2.val if l2 is not None else 0
            total = x + y + carry
            carry = total // 10
            cur.next = ListNode(total % 10)
            cur = cur.next
            if l1 is not None:
                l1 = l1.next
            if l2 is not None:
                l2 = l2.next

        return dummy.next


def _from_array(digits: List[int]) -> Optional[ListNode]:
    """测试辅助：低位在前的数组转成链表。"""
    if not digits:
        return None
    head = ListNode(digits[0])
    cur = head
    for v in digits[1:]:
        cur.next = ListNode(v)
        cur = cur.next
    return head


def _to_array(head: Optional[ListNode]) -> List[int]:
    """测试辅助：链表转数组，便于打印比对。"""
    out = []
    while head is not None:
        out.append(head.val)
        head = head.next
    return out


if __name__ == "__main__":
    solution = Solution()

    list1 = [
        [2, 4, 3],
        [0],
        [9, 9, 9, 9, 9, 9, 9],
    ]
    list2 = [
        [5, 6, 4],
        [0],
        [9, 9, 9, 9],
    ]
    expected = [
        [7, 0, 8],
        [0],
        [8, 9, 9, 9, 0, 0, 0, 1],
    ]

    for i in range(len(list1)):
        a = _from_array(list1[i])
        b = _from_array(list2[i])
        result = solution.add_two_numbers(a, b)
        actual = _to_array(result)
        print(f"调用: add_two_numbers({list1[i]}, {list2[i]})")
        print(f"预期结果: {expected[i]}")
        print(f"实际结果: {actual}")
        print()
