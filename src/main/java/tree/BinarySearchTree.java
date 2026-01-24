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
}
