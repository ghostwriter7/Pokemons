package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * In pre-order traversal you firstly visit a node,
 * Then you recurse the left branch (convention), and then the right branch.
 */
public class PreOrderTraversal {

    public static List<Integer> traverse(BinaryNode<Integer> node) {
        return walk(node, new ArrayList<>());
    }

    private static List<Integer> walk(BinaryNode<Integer> node, List<Integer> path) {
        if (node == null) {
            return path;
        }

        path.add(node.value());

        walk(node.left(), path);
        walk(node.right(), path);

        return path;
    }
}
