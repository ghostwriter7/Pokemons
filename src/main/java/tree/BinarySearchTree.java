package tree;

public class BinarySearchTree {

    public static boolean find(BinaryNode<Integer> node, int needle) {
        if (node == null) return false;

        if (node.value() == needle) return true;

        return node.value() > needle ? find(node.left(), needle) : find(node.right(), needle);
    }
}
