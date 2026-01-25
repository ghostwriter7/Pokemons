package datastructures;

import java.util.ArrayList;
import java.util.List;

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
public abstract sealed class PriorityQueue<T extends Comparable<? super T>> permits PriorityQueue.MinHeap, PriorityQueue.MaxHeap {

    protected final List<T> list = new ArrayList<>();

    public T pop() {
        if (list.isEmpty()) return null;

        if (list.size() == 1) {
            return list.removeFirst();
        }

        var value = list.getFirst();
        list.set(0, list.removeLast());
        heapifyDown(0);
        return value;
    }

    public void insert(T value) {
        list.add(value);

        if (list.size() != 1) heapifyUp(list.size() - 1);
    }

    private void heapifyDown(int index) {
        var left = leftChild(index);
        var right = rightChild(index);

        if (index >= list.size() || left >= list.size()) return;

        var value = list.get(index);

        var targetChildIdx = choose(left, right);
        var childValue = list.get(targetChildIdx);

        if (shouldSwap(value, childValue)) {
            list.set(index, childValue);
            list.set(targetChildIdx, value);
            heapifyDown(targetChildIdx);
        }
    }

    private void heapifyUp(int index) {
        if (index == 0) return;

        var value = list.get(index);
        var parentIndex = parent(index);
        var parentValue = list.get(parentIndex);

        if (shouldSwap(value, parentValue)) {
            list.set(parentIndex, value);
            list.set(index, parentValue);
            heapifyUp(parentIndex);
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    protected abstract boolean shouldSwap(T value, T parentValue);

    protected abstract int choose(int left, int right);

    static final class MinHeap<T extends Comparable<? super T>> extends PriorityQueue<T> {

        @Override
        protected boolean shouldSwap(T value, T parentValue) {
            return value.compareTo(parentValue) < 0;
        }

        @Override
        protected int choose(int left, int right) {
            var leftValue = list.get(left);
            var rightValue = right >= list.size() ? null : list.get(right);

            if (rightValue == null) return left;

            return leftValue.compareTo(rightValue) < 0 ? left : right;
        }
    }

    static final class MaxHeap<T extends Comparable<? super T>> extends PriorityQueue<T> {


        @Override
        protected int choose(int left, int right) {
            var leftValue = list.get(left);
            var rightValue = right >= list.size() ? null : list.get(right);

            if (rightValue == null) return left;

            return leftValue.compareTo(rightValue) > 0 ? left : right;
        }

        @Override
        protected boolean shouldSwap(T value, T parentValue) {
            return value.compareTo(parentValue) > 0;
        }
    }
}
