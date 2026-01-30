package datastructures;

import java.util.Arrays;

/**
 * Eager algorithm - does plenty of work eagerly.
 * This data structure is an implementation for integers.
 * It holds numbers for N - 1 (N is a size in the constructor).
 * The backing array holds the identifier of the component a given integer belongs to.
 * E.g. if data[0] = 5, then it means that a digit 0 belongs to the component 5.
 * <p>
 * initialization O(N)
 * union O(N)
 * find O(1)
 */
public class UnionFind {
    private int count;
    private final int[] data;

    /**
     * Initialises the array with each entry equal to its index.
     * Every number is disconnected at this stage.
     *
     * @param size number of integers to store
     */
    public UnionFind(int size) {
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        count = size;
    }

    /**
     * Connects 2 elements.
     * When they live in 2 different parts, the components are merged.
     *
     * @param p the first element to create a connection from
     * @param q the second element to create a connection to
     */
    public void union(int p, int q) {
        var pCmpId = data[p];
        var qCmpId = data[q];

        for (var i = 0; i < data.length; i++) {
            if (data[i] == pCmpId) {
                data[i] = qCmpId;
            }
        }
        count--;
    }

    /**
     * p and q are connected when they both belong to the same component.
     *
     * @param p the first element
     * @param q the second element
     * @return boolean indicating whether elements p and q are connected
     */
    public boolean isConnected(int p, int q) {
        return data[p] == data[q];
    }

    /**
     * @param p an element to find a component for
     * @return an index of the component the element p belongs to
     */
    public int find(int p) {
        return data[p];
    }

    /**
     * @return a count of components
     */
    public int count() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
