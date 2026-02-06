package datastructures.heap;

import java.util.Arrays;

/**
 * Also known as Heap
 * minHeap - value at index 0 is the smallest in the heap
 * maxHeap - value at index 0 is the largest in the heap
 * For every later level of the heap, the rule is applicable
 * <p>
 * Formulas:
 * getLeftChildIndex() -> 2 * index + 1
 * getRightChildIndex() -> 2 * index + 2
 * <p>
 * getParentIndex() -> (i - 1) / 2 !important, it needs to return an integer, not a floating point number
 * <p>
 * getNewSlotForInsert() -> array.length - 1
 */
public abstract sealed class PriorityQueue<T extends Comparable<? super T>> permits MinHeap, MaxHeap {
    protected T[] elements;
    protected int size;

    @SuppressWarnings("unchecked")
    protected PriorityQueue(int initialCapacity) {
        elements = (T[]) new Comparable[initialCapacity];
    }

    public T peek() {
        return isEmpty() ? null : elements[0];
    }

    public T pop() {
        if (isEmpty()) return null;

        var value = elements[0];
        size--;

        if (size == 0) {
            elements[0] = null;
            return value;
        }

        elements[0] = elements[size];
        elements[size] = null;

        heapifyDown(0);

        return value;
    }

    public void insert(T element) {
        resizeIfNecessary();
        elements[size++] = element;

        if (size != 1) heapifyUp(size - 1);
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    /**
     * @param value Value of an element
     * @param parent Value of the parent element
     * @return boolean Indicator whether this element should be swapped with its parent
     */
    protected abstract boolean shouldBubbleUp(T value, T parent);

    /**
     * @param value Value of an element
     * @param child Value of the child element
     * @return boolean Indicator whether this element should be swapped with its child
     */
    protected abstract boolean shouldBubbleDown(T value, T child);

    /**
     * @param left Index of a left child
     * @param right Index of a right child
     * @return Index of a child that has a higher priority
     */
    protected abstract int chooseSibling(int left, int right);

    private void heapifyUp(int index) {
        if (index == 0) return;

        var parentIndex = getParentIndex(index);
        var parent = elements[parentIndex];
        var value = elements[index];

        if (shouldBubbleUp(value, parent)) {
            swap(parentIndex, index);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        var leftChildIndex = getLeftChildIndex(index);

        if (index >= size || leftChildIndex >= size) return;

        var value = elements[index];
        var childIndex = chooseSibling(leftChildIndex, getRightChildIndex(index));

        if (shouldBubbleDown(value, elements[childIndex])) {
            swap(index, childIndex);
            heapifyDown(childIndex);
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private void swap(int index1, int index2) {
        var temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    private void resizeIfNecessary() {
        if (elements.length == size) {
            var capacity = elements.length * 3 / 2 + 1;
            elements = Arrays.copyOf(elements, capacity);
        }
    }
}
