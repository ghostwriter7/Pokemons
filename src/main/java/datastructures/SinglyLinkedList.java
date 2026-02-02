package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> implements IList<T>, Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(T item) {
        if (size == 0) {
            head = tail = new Node<>(item, null);
        } else {
            var temp = head;
            head = new Node<>(item, temp);
        }
        size++;
    }

    @Override
    public void append(T item) {
        if (size == 0) {
            head = tail = new Node<>(item, null);
        } else {
            var temp = tail;
            tail = new Node<>(item, null);
            temp.next = tail;
        }
        this.size++;
    }

    @Override
    public T get(int idx) {
        return getNode(idx).value;
    }

    @Override
    public void insertAt(T item, int idx) {
        assertIndexForInsert(idx);

        if (idx == 0) {
            prepend(item);
        } else if (idx == size) {
            append(item);
        } else {
            var previousNode = getNode(idx - 1);
            var next = previousNode.next;
            previousNode.next = new Node<>(item, next);
            size++;
        }
    }

    @Override
    public T remove(T item) {
        if (size == 0) return null;

        if (isEqual(head.value, item)) {
            var value = head.value;
            head = head.next;
            size--;

            updateTailIfNecessary();

            return value;
        }

        var node = head;

        while (node.next != null) {
            var next = node.next;
            if (isEqual(next.value, item)) {
                node.next = next.next;

                if (next == tail) {
                    tail = node;
                }

                size--;
                return next.value;
            }
            node = next;
        }

        return null;
    }

    @Override
    public T removeAt(int idx) {
        assertIndexForAccess(idx);

        if (idx == 0) {
            var value = head.value;
            head = head.next;
            size--;
            updateTailIfNecessary();
            return value;
        }

        var previousNode = getNode(idx - 1);
        var nodeForRemoval = previousNode.next;
        previousNode.next = nodeForRemoval.next;

        if (tail == nodeForRemoval) {
            tail = previousNode;
        }

        size--;
        return nodeForRemoval.value;

    }

    @Override
    public Iterator<T> iterator() {
        return new SLLIterator();
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private void assertIndexForAccess(int idx) {
        if (idx >= size || idx < 0) {
            throw new IndexOutOfBoundsException("Index %d is out of bounds".formatted(idx));
        }
    }

    private void assertIndexForInsert(int idx) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException("Index %d is invalid for insertion".formatted(idx));
        }
    }

    private Node<T> getNode(int idx) {
        assertIndexForAccess(idx);

        if (idx == 0) {
            return head;
        }

        var node = head;

        for (int i = 0; i < idx; i++) {
            node = node.next;
        }

        return node;
    }

    private void updateTailIfNecessary() {
        if (size == 1) {
            tail = head;
        } else if (size == 0) {
            tail = head = null;
        }
    }

    private boolean isEqual(T value, T item) {
        return Objects.equals(value, item);
    }

    private final class SLLIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            var value = current.value;
            current = current.next;
            return value;
        }
    }
}
