package datastructures;

public interface IStack<T> {
    void push(T item);
    T pop();
    T peek();
}
