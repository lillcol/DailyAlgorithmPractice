from typing import Dict, Optional


class _Node:
    __slots__ = ("key", "val", "prev", "next")

    def __init__(self, key: int = 0, val: int = 0):
        self.key = key
        self.val = val
        self.prev: Optional["_Node"] = None
        self.next: Optional["_Node"] = None


class LRUCache:
    """LeetCode 146：哈希表 + 双向链表，O(1) get / put"""

    def __init__(self, capacity: int):
        self._cap = capacity
        self._map: Dict[int, _Node] = {}
        self._head = _Node()
        self._tail = _Node()
        self._head.next = self._tail
        self._tail.prev = self._head

    def get(self, key: int) -> int:
        if key not in self._map:
            return -1
        node = self._map[key]
        self._remove(node)
        self._add_to_tail(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self._map:
            node = self._map[key]
            node.val = value
            self._remove(node)
            self._add_to_tail(node)
            return
        if len(self._map) == self._cap:
            lru = self._head.next
            assert lru is not None
            self._remove(lru)
            del self._map[lru.key]
        node = _Node(key, value)
        self._map[key] = node
        self._add_to_tail(node)

    def _remove(self, node: _Node) -> None:
        assert node.prev is not None and node.next is not None
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add_to_tail(self, node: _Node) -> None:
        assert self._tail.prev is not None
        p = self._tail.prev
        p.next = node
        node.prev = p
        node.next = self._tail
        self._tail.prev = node


if __name__ == "__main__":
    c = LRUCache(2)
    c.put(1, 1)
    c.put(2, 2)
    print("get(1):", c.get(1))
    c.put(3, 3)
    print("get(2):", c.get(2))
    c.put(4, 4)
    print("get(1):", c.get(1))
    print("get(3):", c.get(3))
    print("get(4):", c.get(4))
