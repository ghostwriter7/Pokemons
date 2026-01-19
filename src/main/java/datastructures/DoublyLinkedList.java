package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> implements IList<T>, Iterable<T> {
    private int length = 0;
    private Node<T> head;
    private Node<T> tail;
    private int modCount = 0;

    @Override
    public int size() {
        return length;
    }

    @Override
    public void prepend(T item) {
        if (length == 0) {
            this.tail = this.head = new Node<>(item, null, null);
        } else {
            var temp = this.head;
            this.head = new Node<>(item, null, temp);
            temp.previous = this.head;
        }
        length++;
        modCount++;
    }

    @Override
    public void append(T item) {
        if (length == 0) {
            this.tail = this.head = new Node<>(item, null, null);
        } else {
            var temp = this.tail;
            this.tail = new Node<>(item, temp, null);
            temp.next = this.tail;
        }
        length++;
        modCount++;
    }

    @Override
    public void insertAt(T item, int idx) {
        assertIndexForInsert(idx);

        if (idx == 0) {
            prepend(item);
        } else if (idx == length) {
            append(item);
        } else {
            var previous = getNode(idx - 1);
            var next = previous.next;
            var node = new Node<>(item, previous, next);
            previous.next = node;

            if (next != null) {
                next.previous = node;
            }
            length++;
            modCount++;
        }
    }

    @Override
    public T remove(T item) {
        if (head.value == item) {
            return removeHead();
        }

        var node = head.next;
        while (node != null) {
            if (Objects.equals(node.value, item)) break;
            node = node.next;
        }

        if (node == null) return null;

        if (node == tail) return removeTail();

        var prev = node.previous;
        var next = node.next;
        prev.next = next;
        if (next != null) {
            next.previous = prev;
        }
        length--;
        modCount++;
        return node.value;
    }

    @Override
    public T removeAt(int idx) {
        assertIndexForAccess(idx);

        if (idx == 0) {
            return removeHead();
        } else if (idx == (length - 1)) {
            return removeTail();
        } else {
            var node = getNode(idx);
            var next = node.next;
            var prev = node.previous;

            prev.next = next;

            if (next != null) {
                next.previous = prev;
            }

            length--;
            modCount++;
            return node.value;
        }
    }

    @Override
    public T get(int idx) {
        return getNode(idx).value;
    }

    private T removeTail() {
        var temp = tail;

        if (tail.previous == null) {
            tail = head = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }

        modCount++;
        length--;
        return temp.value;
    }

    private T removeHead() {
        var temp = head;

        if (temp.next == null) {
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }

        length--;
        modCount++;

        return temp.value;
    }

    private Node<T> getNode(int idx) {
        assertIndexForAccess(idx);

        if (idx == 0) {
            return head;
        } else if (idx == (length - 1)) {
            return tail;
        } else {

            if (idx > length / 2) {
                var node = tail;
                for (var i = length - 1; i > idx; i--) {
                    node = node.previous;
                }
                return node;
            } else {
                var node = head;
                for (var i = 0; i < idx; i++) {
                    node = node.next;
                }
                return node;
            }

        }
    }

    private void assertIndexForInsert(int idx) {
        if (idx < 0 || idx > length) {
            throw new IndexOutOfBoundsException("Index %d is out of bounds".formatted(idx));
        }
    }

    private void assertIndexForAccess(int idx) {
        if (idx < 0 || idx >= length) {
            throw new IndexOutOfBoundsException("Index %d is out of bounds".formatted(idx));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    private final class DLLIterator implements Iterator<T> {
        private Node<T> current = head;
        private final int expectedModificationCount = modCount;

        @Override
        public boolean hasNext() {
            assertNoConcurrentModification();
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            var value = current.value;
            current = current.next;
            return value;
        }

        private void assertNoConcurrentModification() {
            if (expectedModificationCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> previous;
        private Node<T> next;

        public Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
