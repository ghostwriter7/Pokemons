package tree;

public class BinarySearchTree {

    public static boolean find(BinaryNode<Integer> node, int needle) {
        if (node == null) return false;

        if (node.value() == needle) return true;

        return node.value() > needle ? find(node.left(), needle) : find(node.right(), needle);
    }

    public static void insert(MutableBinaryNode<Integer> node, int value) {
        var currentValue = node.getValue();
        var next = currentValue >= value ? node.getLeft() : node.getRight();

        if (next != null) {
            insert(next, value);
        } else {
            var newNode = new MutableBinaryNode<>(value);
            if (currentValue >= value) node.setLeft(newNode);
            else node.setRight(newNode);
        }

    }

    /**
     * 1. Node has zero children, then go to parent and remove the link to the node
     * 2. Node has one child, then go to parent and replace the link to the node with a link to its child
     * 3. Node has 2 children, then need to reorganize the tree:
     * - either go to the left, find the max item (the largest item on the right) and replace the to-be-deleted node with it
     * - or go to the right, find the min item, and replace the to-be-deleted node with it
     */
    public static void delete(MutableBinaryNode<Integer> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            var successor = findMin(node.getRight());
            node.setValue(successor.getValue());
            delete(successor);
        } else {
            var child = node.getLeft() != null ? node.getLeft() : node.getRight();
            var parent = node.getParent();

            if (parent == null) {
                // Deleting root...
                if (child != null) child.setParent(null);
            } else {
                if (parent.getLeft() == node) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }

            node.setParent(null);
            node.setLeft(null);
            node.setRight(null);
        }
    }

    private static MutableBinaryNode<Integer> findMin(MutableBinaryNode<Integer> node) {
        while (node.getLeft() != null) {
            if (node.getLeft() != null) node = node.getLeft();
        }

        return node;
    }

}
