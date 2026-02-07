package union;

public class QuickUnionWithPathCompression extends QuickUnion {
    public QuickUnionWithPathCompression(int n) {
        super(n);
    }

    /**
     * The path compression is done when searching for a root.
     * At each iteration, if the parent is not the root, then we assign the current component
     * to its parent and so on.
     */
    protected int root(int i) {
        while (i != data[i]) {
            data[i] = data[data[i]];
            i = data[i];
        }

        return i;
    }
}
