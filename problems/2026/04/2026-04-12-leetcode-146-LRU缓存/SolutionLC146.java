import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146：LRU 缓存（哈希表 + 双向链表，get/put 均 O(1)）
 * <p>
 * 提交 LeetCode 时可将内部类 {@link LRUCache} 单独复制为顶层类使用。
 */
public class SolutionLC146 {

    static class LRUCache {

        private static class Node {
            int key;
            int val;
            Node prev;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> map = new HashMap<>();
        private final Node head = new Node(0, 0);
        private final Node tail = new Node(0, 0);

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            remove(node);
            addToTail(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node existing = map.get(key);
            if (existing != null) {
                existing.val = value;
                remove(existing);
                addToTail(existing);
                return;
            }
            if (map.size() == capacity) {
                Node lru = head.next;
                remove(lru);
                map.remove(lru.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addToTail(node);
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        /** 接到尾哑元前，表示最近使用 */
        private void addToTail(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1) 预期 1: " + cache.get(1));
        cache.put(3, 3);
        System.out.println("get(2) 预期 -1: " + cache.get(2));
        cache.put(4, 4);
        System.out.println("get(1) 预期 -1: " + cache.get(1));
        System.out.println("get(3) 预期 3: " + cache.get(3));
        System.out.println("get(4) 预期 4: " + cache.get(4));
    }
}
