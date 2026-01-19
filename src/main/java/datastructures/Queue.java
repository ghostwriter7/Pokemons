package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements IQueue<T>, Iterable<T> {
    private int length = 0;
    private Node<T> head;
    private Node<T> tail;
    private int modCount = 0;

    @Override
    public void offer(T item) {
        if (isEmpty()) {
            head = tail = new Node<>(item, null);
        } else {
            tail.next = new Node<>(item, null);
            tail = tail.next;
        }
        modCount++;
        length++;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : head.value;
    }

    @Override
    public T poll() {
        if (isEmpty()) return null;

        var value = head.value;

        var temp = head;
        head = temp.next;
        length--;
        modCount++;

        if (head == null) tail = null;

        return value;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private final class QueueIterator implements Iterator<T> {
        private Node<T> current = head;
        private final int expectedModificationCount = modCount;

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            var value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public boolean hasNext() {
            if (expectedModificationCount != modCount)
                throw new ConcurrentModificationException();
            return current != null;
        }
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
