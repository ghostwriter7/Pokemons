package datastructures;

import java.util.*;
import java.util.stream.Collectors;

class HashMap<K, V> implements Iterable<HashMap.Entry<K, V>> {
    private int capacity;
    private int size;
    private List<Entry<K, V>>[] buckets;
    private int modCount;

    public HashMap(int capacity) {
        this.capacity = capacity;
        initEmptyBuckets();
    }

    public void put(K key, V value) {
        assertNotNullKey(key);
        resizeIfNecessary();

        var bucket = getBucket(key);
        modCount++;

        for (var entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        size++;
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        assertNotNullKey(key);

        for (var entry : getBucket(key)) {
            if (entry.key.equals(key)) return entry.value;
        }

        return null;
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

        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .map(entry -> entry.key)
                .collect(Collectors.toSet());
    }

    public Collection<V> values() {
        if (isEmpty()) return List.of();

        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .map(entry -> entry.value)
                .toList();
    }

    public Collection<Entry<K, V>> entries() {
        if (isEmpty()) return List.of();

        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .toList();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        assertNotNullKey(key);
        if (isEmpty()) return false;

        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .anyMatch(entry -> entry.key.equals(key));
    }

    public boolean containsValue(V value) {
        if (isEmpty()) return false;

        return Arrays.stream(buckets)
                .flatMap(List::stream)
                .anyMatch(entry -> Objects.equals(entry.value, value));
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[ ");
        for (var entry : entries()) {
            sb.append(entry.key).append("=").append(entry.value).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void assertNotNullKey(K key) {
        if (key == null) throw new NullPointerException("Key must not be null");
    }

    private List<Entry<K, V>> getBucket(K key) {
        var hash = key.hashCode();
        var bucketIndex = Math.floorMod(hash, capacity);
        return buckets[bucketIndex];
    }

    private void resizeIfNecessary() {
        var loadFactor = (double) size / capacity;

        if (loadFactor > 0.7) {
            capacity *= 2;
            var currentBuckets = buckets;
            initEmptyBuckets();
            size = 0;

            for (var bucket : currentBuckets) {
                for (var entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initEmptyBuckets() {
        buckets = new LinkedList[capacity];
        for (var i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
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

    private final class HashMapIterator implements Iterator<Entry<K, V>> {
        private final int expectedModCount = modCount;
        private int bucketIndex;
        private Iterator<Entry<K, V>> bucketIterator;

        public HashMapIterator() {
            bucketIndex = findNextNonEmptyBucketIndex(0);
            bucketIterator = bucketIndex != -1 ? buckets[bucketIndex].iterator() : null;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();

            if (bucketIterator == null) return false;

            if (bucketIterator.hasNext()) return true;

            var nextBucket = findNextNonEmptyBucketIndex(bucketIndex + 1);

            if (nextBucket == -1) return false;

            bucketIndex = nextBucket;
            bucketIterator = buckets[bucketIndex].iterator();

            return bucketIterator.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            return bucketIterator.next();
        }


        private int findNextNonEmptyBucketIndex(int index) {
            for (var i = index; i < buckets.length; i++) {
                if (!buckets[i].isEmpty()) return i;
            }
            return -1;
        }
    }
}