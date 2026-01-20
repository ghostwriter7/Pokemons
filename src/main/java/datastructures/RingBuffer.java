package datastructures;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBuffer<T> implements Iterable<T> {
    private final T[] buffer;
    private int length = 0;
    private final int capacity;
    private int head = 0;
    private int tail = 0;
    private int modCount = 0;

    private static final int DEFAULT_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public RingBuffer(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0, but got %d".formatted(capacity));
        this.capacity = capacity + 1;
        buffer = (T[]) new Object[this.capacity];
    }

    public RingBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T item) {
        if (isFull()) throw new BufferOverflowException();
        buffer[tail] = item;
        length++;
        modCount++;
        tail = (tail + 1) % capacity;
    }

    public T pop() {
        if (isEmpty()) throw new BufferUnderflowException();
        var value = buffer[head];
        buffer[head] = null;
        length--;
        modCount++;
        head = (head + 1) % capacity;
        return value;
    }

    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return buffer[head];
    }

    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail == 0 ? buffer[capacity - 1] : buffer[tail - 1];
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == (capacity - 1);
    }

    public int size() {
        return length;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        var sb = new StringBuilder("[");
        var idx = head;

        while (idx != tail) {
            sb.append(buffer[idx]);
            idx = (idx + 1) % capacity;
            if (idx != tail) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    private final class RingBufferIterator implements Iterator<T> {
        private int current = head;
        private int remaining = length;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();

            return remaining > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            var value = buffer[current];
            current = (current + 1) % capacity;
            remaining--;
            return value;
        }
    }
}
