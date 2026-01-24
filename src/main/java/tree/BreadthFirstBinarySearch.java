package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In Breadth-First traversal we use a Queue (as opposed to a Stack in Depth-First)
 */
public class BreadthFirstBinarySearch {

    public static boolean search(BinaryNode<Integer> head, int needle) {
        Queue<BinaryNode<Integer>> queue = new LinkedList<>();

        queue.add(head);

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.value() == needle) return true;

            if (current.left() != null) queue.add(current.left());
            if (current.right() != null) queue.add(current.right());
        }

        return false;
    }
}
