package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * In post order traversal, you firstly go all the way down the left branch
 * and process a node when no more children are left.
 */
public class PostOrderTraversal {

    public static List<Integer> traverse(BinaryNode<Integer> node) {
        return walk(node, new ArrayList<>());
    }

    private static List<Integer> walk(BinaryNode<Integer> node, List<Integer> path) {
        if (node == null) return path;

        walk(node.left(), path);
        walk(node.right(), path);

        path.add(node.value());

        return path;
    }
}
