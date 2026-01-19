package datastructures;

public interface IList<T> {
    int size();
    void prepend(T item);
    void insertAt(T item, int idx);
    void append(T item);
    T remove(T item);
    T get(int idx);
    T removeAt(int idx);
}
