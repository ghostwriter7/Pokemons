package datastructures;

public interface IQueue<T> {
    void offer(T item);
    T peek();
    T poll();
    int size();
    boolean isEmpty();
}
