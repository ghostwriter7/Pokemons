package union;

import java.util.Arrays;

/**
 * A Lazy sibling of UnionFind.
 * This time the underlying data structure (integer array) stores trees, a forest so to say.
 * if data[i] == i, then it means that a value is unlinked, it is its own root.
 * if data[i] points to something else, it means that the something else is its parent
 * In this way we can navigate it until we find a root item.
 *
 * The defect is that the find() operation can be too expensive.
 * The trees can be too tall, and in the end find() could be O(N)
 * The remedy is to use weighting and always move the shortest tree to the tallest tree, not the other way around.
 * This way we maintain a compact tree with faster find().
 */
public class QuickUnion {
    protected final int[] data;
    private final int[] sizes;
    private int count;

    public QuickUnion(int size) {
        data = new int[size];
        sizes = new int[size];
        for (int i = 0; i < size; i++){
            data[i] = i;
            sizes[i] = 1;
        }
        count = size;
    }

    public void union(int p, int q) {
        var rootOfQ = findRoot(q);
        var rootOfP = findRoot(p);

        if (rootOfQ == rootOfP) return;

        if (sizes[rootOfQ] > sizes[rootOfP]) {
            data[rootOfP] =  rootOfQ;
            sizes[rootOfQ] += sizes[rootOfP];
        } else {
            data[rootOfQ] = rootOfP;
            sizes[rootOfP] += sizes[rootOfQ];
        }

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

    protected int findRoot(int p) {
        while (p != data[p]) {
            p = data[p];
        }
        return p;
    }
}
