package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements IStack<T>, Iterable<T> {
    private int length = 0;
    private int modCount = 0;
    private Node<T> head;

    @Override
    public void push(T item) {
        if (length == 0) {
            head = new Node<>(item);
        } else {
            var temp = head;
            head = new Node<>(item);
            head.previous = temp;
        }
        length++;
        modCount++;
    }

    @Override
    public T pop() {
        if (head == null) return null;

        var value = head.value;
        head = head.previous;
        length--;
        modCount++;
        return value;
    }

    @Override
    public T peek() {
        return head != null ? head.value : null;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private final class StackIterator implements Iterator<T> {
        private Node<T> current = head;
        private final int expectedModificationCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModificationCount != modCount)
                throw new ConcurrentModificationException();

            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            var value = current.value;
            current = current.previous;
            return value;
        }
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> previous;

        private Node(T value) {
            this.value = value;
        }
    }
}
