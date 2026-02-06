package datastructures.heap;

public final class MinHeap<T extends Comparable<? super T>> extends PriorityQueue<T> {
    public MinHeap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    protected boolean shouldBubbleUp(T value, T parent) {
        return value.compareTo(parent) < 0;
    }

    @Override
    protected boolean shouldBubbleDown(T value, T child) {
        return value.compareTo(child) > 0;
    }

    @Override
    protected int chooseSibling(int left, int right) {
        if (right >= size) return left;

        var rightValue = elements[right];
        var leftValue = elements[left];

        return leftValue.compareTo(rightValue) < 0 ? left : right;
    }
}
