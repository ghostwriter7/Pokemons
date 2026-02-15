package datastructures;

import java.util.HashMap;
import java.util.Map;

public class LeastRecentlyUsedCache<K, V> {
    private final int maxSize;
    private Node head;
    private Node tail;
    private final Map<K, Node> lookup = new HashMap<>();
    private int size;

    public LeastRecentlyUsedCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.maxSize = capacity;
    }

    public void put(K key, V value) {
        var node = lookup.get(key);

        if (node != null) {
            node.value = value;
            detach(node);
            prepend(node);
        } else {
            if (isFull()) evict();
            prepend(key, value);
            size++;
        }
    }

    public V get(K key) {
        var node = lookup.get(key);
        if (node == null) return null;

        detach(node);
        prepend(node);

        return node.value;
    }

    private void detach(Node node) {
        var prev = node.prev;
        var next = node.next;

        if (prev != null) prev.next = next;
        if (next != null) next.prev = prev;

        if (tail == node) tail = prev;
        if (head == node) head = next;

        node.prev = node.next = null;
    }

    private void prepend(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void prepend(K key, V value) {
        var node = new Node(key, value);
        lookup.put(key, node);
        prepend(node);
    }

    private void evict() {
        var key = tail.key;
        detach(tail);
        lookup.remove(key);
        size--;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private boolean isFull() { return size == maxSize; }

    private class Node {
        private V value;
        private Node next;
        private Node prev;
        private final K key;

        private Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
    }
}
