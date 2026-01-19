package datastructures;

import java.util.*;

public class ArrayList<T> implements IList<T>, Iterable<T> {
    private int length = 0;
    private T[] data;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        data = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void prepend(T item) {
        resizeIfNeeded();
        System.arraycopy(data, 0, data, 1, length);
        writeAtIndex(0, item);
    }

    @Override
    public void insertAt(T item, int idx) {
        if (idx < 0 || idx > length) throw new IndexOutOfBoundsException();
        resizeIfNeeded();
        System.arraycopy(data, idx, data, idx + 1, length - idx);
        writeAtIndex(idx, item);
    }

    @Override
    public void append(T item) {
        resizeIfNeeded();
        writeAtIndex(length, item);
    }

    @Override
    public T remove(T item) {
        for (var i = 0; i < length; i++) {
            if (Objects.equals(data[i], item)) {
                return removeAt(i);
            }
        }

        return null;
    }

    @Override
    public T get(int idx) {
        checkIndexForAccess(idx);
        return data[idx];
    }

    @Override
    public T removeAt(int idx) {
        checkIndexForAccess(idx);
        var value = get(idx);
        System.arraycopy(data, idx + 1, data, idx, length - idx - 1);
        data[--length] = null;
        modCount++;

        return value;
    }

    private void checkIndexForAccess(int idx) {
        if (idx < 0 || idx >= length)
            throw new IndexOutOfBoundsException();
    }

    private void writeAtIndex(int idx, T item) {
        data[idx] = item;
        length++;
        modCount++;
    }

    private void resizeIfNeeded() {
        if (data.length == length) {
            var capacity = data.length * 3 / 2 + 1;
            data = Arrays.copyOf(data, capacity);
        }
    }

    @Override
    public ListIterator<T> iterator() {
        return new ArrayListIterator();
    }

    private final class ArrayListIterator implements ListIterator<T> {
        private int currentIndex = 0;
        private final int expectedModificationCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            assertNoConcurrentModification();
            if (!hasNext()) throw new NoSuchElementException();
            return data[currentIndex++];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public T previous() {
            assertNoConcurrentModification();
            if (!hasPrevious()) throw new NoSuchElementException();
            return data[--currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }

        private void assertNoConcurrentModification() {
            if (expectedModificationCount != modCount)
                throw new ConcurrentModificationException();
        }
    }
}
