package tree

import spock.lang.Specification
import spock.lang.Unroll

class CompareTreesTest extends Specification {

    @Unroll
    def "should compare two trees structurally and by value, and return #expected"() {

        expect:
        CompareTrees.isEqual(tree1, tree2) == expected

        where:
        tree1                                                          | tree2                                                          || expected
        node(1)                                                        | node(1)                                                        || true
        node(1)                                                        | node(2)                                                        || false
        node(1)                                                        | null                                                           || false
        null                                                           | node(2)                                                        || false
        node(1, node(2))                                               | node(1, node(2))                                               || true
        node(1, node(2))                                               | node(1, null, node(2))                                         || false
        node(1, node(2), node(3))                                      | node(1, node(2, node(3)))                                      || false
        node(1, node(2, node(5), node(6)), node(3, node(-3), node(7))) | node(1, node(2, node(5), node(6)), node(3, node(-3), node(7))) || true
    }

    BinaryNode<Integer> node(int value, BinaryNode<Integer> nodeA = null, BinaryNode<Integer> nodeB = null) {
        new BinaryNode<Integer>(value, nodeA, nodeB)
    }
}
