package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;
    private int modCount;

    public int size() { return size; }

    public void prepend(T value) {
        if (size == 0) {
            head = tail = new Node<>(value);
        } else {
            var temp = head;
            head = new Node<>(value, temp);
        }
        size++;
        modCount++;
    }

    public void append(T value) {
        if (size == 0) {
            head = tail = new Node<>(value);
        } else {
            var temp = tail;
            tail = new Node<>(value);
            temp.next = tail;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        return getNodeAt(index).value;
    }

    public void insertAt(int index, T value) {
        assertIndexForInsert(index);

        if (index == 0) {
            prepend(value);
        } else if (index == size) {
            append(value);
        } else {
            var previousNode = getNodeAt(index - 1);
            previousNode.next = new Node<>(value, previousNode.next);
            size++;
            modCount++;
        }
    }

    public T remove(T value) {
        if (size == 0) return null;

        if (isEqual(head.value, value)) {
            var temp = head.value;
            head = head.next;
            size--;
            updateTailIfNecessary();
            modCount++;
            return temp;
        }

        var node = head;

        while (node.next != null) {
            var next = node.next;
            if (isEqual(next.value, value)) {
                node.next = next.next;
                if (next == tail) tail = node;
                modCount++;
                size--;
                return next.value;
            }

            node = next;
        }

        return null;
    }

    public T removeAt(int index) {
        assertIndexForAccess(index);

        if (index == 0) {
            var temp = head.value;
            size--;
            modCount++;
            head = head.next;
            updateTailIfNecessary();
            return temp;
        }

        var previousNode = getNodeAt(index - 1);
        var nodeToRemove = previousNode.next;
        previousNode.next = nodeToRemove.next;
        size--;
        modCount++;

        if (nodeToRemove == tail) tail = previousNode;

        return nodeToRemove.value;
    }

    @Override
    public Iterator<T> iterator() { return new SLLIterator(); }

    private void updateTailIfNecessary() {
        if (size == 0) {
            head = tail = null;
        } else if (size == 1) {
            tail = head;
        }
    }

    private boolean isEqual(T valueA, T valueB) {
        return Objects.equals(valueA, valueB);
    }

    private Node<T> getNodeAt(int index) {
        assertIndexForAccess(index);

        if (index == 0) return head;

        var node = head;

        for (var i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    private void assertIndexForAccess(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private void assertIndexForInsert(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }

    private static class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this(value, null);
        }

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private final class SLLIterator implements Iterator<T> {
        private Node<T> current = head;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            var temp = current.value;
            current = current.next;
            return temp;
        }
    }
}