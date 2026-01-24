package tree;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {

    public static List<Integer> traverse(BinaryNode<Integer> node) {
        return walk(node, new ArrayList<>());
    }

    private static List<Integer> walk(BinaryNode<Integer> node, List<Integer> path) {
        if (node == null) return path;

        walk(node.left(), path);
        path.add(node.value());
        walk(node.right(), path);

        return path;
    }
}
