package datastructures;

import java.util.Arrays;

/**
 * A Lazy sibling of UnionFind.
 * This time the underlying data structure (integer array) stores trees, a forest so to say.
 * if data[i] == i, then it means that a value is unlinked, it is its own root.
 * if data[i] points to something else, it means that the something else is its parent
 * In this way we can navigate it until we find a root item.
 *
 */
public class QuickUnion {
    private final int[] data;
    private int count;

    public QuickUnion(int size) {
        data = new int[size];
        for (int i = 0; i < size; i++) data[i] = i;
        count = size;
    }

    public void union(int p, int q) {
        var rootOfQ = findRoot(q);
        var rootOfP = findRoot(p);

        if (rootOfQ == rootOfP) return;

        data[rootOfP] = rootOfQ;
        count--;
    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public int find(int p) {
        return findRoot(p);
    }

    public int count() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    private int findRoot(int p) {
        if (data[p] == p) return p;

        return findRoot(data[p]);
    }
}
