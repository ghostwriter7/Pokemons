package tree;

public final class BinarySearchTree {
    private BinarySearchTree() {}

    /**
     * Finds a value in a Binary Search Tree using recursion.
     * Returns false if the node is null, indicating the value was not found.
     * Returns true if the node's value matches the needle.
     * Otherwise, recursively searches the left or right subtree based on comparison.
     */
    public static <T extends Comparable<? super T>> boolean find(BinaryNode<T> node, T needle) {
        if (node == null) return false;

        if (node.getValue().equals(needle)) return true;

        return find(isGreaterThanOrEqual(node, needle) ? node.getLeft() : node.getRight(), needle);
    }

    /**
     * Inserts a new node with the given value into a Binary Search Tree.
     * Recursively traverses the tree until finding the appropriate position for insertion.
     */
    public static <T extends Comparable<? super T>> void insert(BinaryNode<T> node, T value) {
        var isGreaterThanOrEqual = isGreaterThanOrEqual(node, value);
        var next = isGreaterThanOrEqual ? node.getLeft() : node.getRight();

        if (next != null) {
            insert(next, value);
        } else {
            var newNode = new BinaryNode<>(value);
            if (isGreaterThanOrEqual) {
                node.setLeft(newNode);
            } else {
                node.setRight(newNode);
            }
        }
    }

    /**
     * Deletes a node from the Binary Search Tree while maintaining tree structure.
     * If the node has two children, replaces it with its in-order successor (smallest node in the right subtree) and deletes the successor.
     * If the node has one or no children, removes it and connects its parent directly to its child.
     */
    public static <T extends Comparable<? super T>> void delete(BinaryNode<T> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            var successor = findMin(node.getRight());
            node.setValue(successor.getValue());
            delete(successor);
        } else {
            var child = node.getLeft() != null ? node.getLeft() : node.getRight();
            var parent = node.getParent();

            if (parent == null) {
                if (child != null) {
                    child.setParent(null);
                }
            } else {
                if (parent.getLeft() == node) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }

            node.setLeft(null);
            node.setRight(null);
            node.setParent(null);
        }
    }

    /**
     * Finds the smallest node in the given subtree by traversing left children.
     * Note: Alternatively, the largest node from the left subtree could be used.
     * This is a simplified approach; ideally, the choice should be based on subtree heights to maintain balance.
     */
    private static <T extends Comparable<? super T>> BinaryNode<T> findMin(BinaryNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private static <T extends Comparable<? super T>> boolean isGreaterThanOrEqual(BinaryNode<T> node, T value) {
        return node.getValue().compareTo(value) >= 0;
    }

    public static <T extends Comparable<? super T>> String print(BinaryNode<T> node) {
        var sb = new StringBuilder("[");
        walk(node, sb);
        sb.append(" ]");
        return sb.toString();
    }

    private static <T extends Comparable<? super T>> void walk(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) return;

        sb.append(" ").append(node.getValue());
        walk(node.getLeft(), sb);
        walk(node.getRight(), sb);
    }

    public static class BinaryNode<T extends Comparable<? super T>> {
        private T value;
        private BinaryNode<T> left;
        private BinaryNode<T> right;
        private BinaryNode<T> parent;

        public BinaryNode(T value) {
            this(value, null, null);
        }
        public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
            this.value = value;
            setLeft(left);
            setRight(right);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public BinaryNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryNode<T> left) {
            this.left = left;
            if (left != null) left.setParent(this);
        }

        public BinaryNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryNode<T> right) {
            this.right = right;
            if (right != null) right.setParent(this);
        }

        public BinaryNode<T> getParent() {
            return parent;
        }

        public void setParent(BinaryNode<T> parent) {
            this.parent = parent;
        }
    }
}