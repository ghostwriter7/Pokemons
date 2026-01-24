package tree;

public class CompareTrees {

    /**
     * To perform a comparison by value & structure, use the Depth-First algorithm.
     * Depth-First preserves the structure, Breadth-First does not do it.
     */
    public static boolean isEqual(BinaryNode<Integer> nodeA, BinaryNode<Integer> nodeB) {
        if (nodeA == null && nodeB == null) return true;
        if ((nodeA == null || nodeB == null) || !nodeA.value().equals(nodeB.value())) return false;

        return isEqual(nodeA.left(), nodeB.left()) && isEqual(nodeA.right(), nodeB.right());
    }
}
