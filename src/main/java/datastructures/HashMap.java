package datastructures;

import java.util.*;
import java.util.ArrayList;

public class HashMap<K, V> implements Iterable<HashMap.Entry<K, V>> {
    private int capacity;
    private int size;
    private int modCount;
    private LinkedList<Entry<K, V>>[] buckets;

    public HashMap(int capacity) {
        this.capacity = capacity;
        initEmptyBuckets();
    }

    public V get(K key) {
        assertNotNullKey(key);
        for (Entry<K, V> entry : getBucket(key)) {
            if (entry.key.equals(key)) return entry.value;
        }

        return null;
    }

    public void put(K key, V value) {
        assertNotNullKey(key);
        if (shouldResize()) resize();

        var bucket = getBucket(key);
        var iter = bucket.listIterator();
        modCount++;
        size++;

        while (iter.hasNext()) {
            var next = iter.next();
            if (next.key.equals(key)) {
                next.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
    }

    public V remove(K key) {
        assertNotNullKey(key);
        if (isEmpty()) return null;

        var bucket = getBucket(key);
        var iter = bucket.listIterator();
        while (iter.hasNext()) {
            var next = iter.next();
            if (next.key.equals(key)) {
                iter.remove();
                size--;
                modCount++;
                return next.value;
            }
        }
        return null;
    }

    public void clear() {
        initEmptyBuckets();
        size = 0;
        modCount++;
    }

    public Set<K> keySet() {
        if (isEmpty()) return Set.of();

        Set<K> keySet = new HashSet<>();

        for (var bucket : buckets) {
            for (var node : bucket) keySet.add(node.key);
        }

        return keySet;
    }

    public Collection<V> values() {
        if (isEmpty()) return List.of();

        List<V> values = new ArrayList<>(size);
        for (var bucket : buckets) {
            for (var node : bucket) values.add(node.value);
        }
        return values;
    }

    public Collection<Entry<K, V>> entries() {
        if (isEmpty()) return List.of();

        List<Entry<K, V>> entries = new ArrayList<>(size);
        for (var bucket : buckets) entries.addAll(bucket);
        return entries;
    }

    public boolean containsKey(K key) {
        assertNotNullKey(key);
        if (isEmpty()) return false;

        var bucket = getBucket(key);
        for (var entry : bucket) {
            if (entry.key.equals(key)) return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        if (isEmpty()) return false;

        for (var bucket : buckets) {
            for (var node : bucket) {
                if (Objects.equals(node.value, value)) return true;
            }
        }
        return false;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    @Override
    public String toString() {
        var sb = new StringBuilder("[ ");
        for (var entry : entries()) {
            sb.append(entry.key).append("=").append(entry.value).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() { return new HashMapIterator(); }

    private double getLoadFactor() {
        return (double) size / capacity;
    }

    private void resize() {
        capacity = capacity * 2;
        var oldBuckets = buckets;

        initEmptyBuckets();
        size = 0;

        for (var bucket : oldBuckets) {
            for (var node : bucket) {
                put(node.key, node.value);
            }
        }
    }

    private List<Entry<K, V>> getBucket(K key) {
        var hash = key.hashCode();
        var bucketIndex = Math.floorMod(hash, capacity);
        return buckets[bucketIndex];
    }

    private boolean shouldResize() {
        return getLoadFactor() > 0.7;
    }

    private void assertNotNullKey(K key) {
        if (key == null) throw new NullPointerException("Key is null");
    }

    @SuppressWarnings("unchecked")
    private void initEmptyBuckets() {
        buckets = new LinkedList[capacity];
        for (var i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private final class HashMapIterator implements Iterator<Entry<K, V>> {
        private final int expectedModCount = modCount;
        private int currentBucket;
        private Iterator<Entry<K, V>> bucketIterator;

        private HashMapIterator() {
            currentBucket = findNextNonEmptyBucketIndex(0);
            if (currentBucket != -1)
                bucketIterator = buckets[currentBucket].iterator();
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();

            if (bucketIterator == null) return false;

            if (bucketIterator.hasNext()) return true;

            var nextBucket = findNextNonEmptyBucketIndex(currentBucket + 1);

            if (nextBucket == -1) return false;

            currentBucket = nextBucket;
            bucketIterator = buckets[currentBucket].iterator();

            return bucketIterator.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            return bucketIterator.next();
        }

        private int findNextNonEmptyBucketIndex(int currentIndex) {
            for (var i = currentIndex; i < buckets.length; i++) {
                if (!buckets[i].isEmpty()) return i;
            }
            return -1;
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
