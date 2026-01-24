package tree

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchTreeTest extends Specification {

    @Unroll
    def "should return #expected when searching for #needle in a binary search tree"() {

        expect:
        BinarySearchTree.find(tree, needle) == expected

        where:
        tree                                                            | needle || expected
        node(5)                                                         | 5      || true
        node(5, node(3), node(6))                                       | 6      || true
        node(5, node(3), node(6))                                       | 7      || false
        node(5, node(2, node(-2), node(4)), node(9, node(7), node(11))) | 11     || true
        node(5, node(2, node(-2), node(4)), node(9, node(7), node(11))) | 13     || false
    }

    BinaryNode<Integer> node(int value, BinaryNode<Integer> left = null, BinaryNode<Integer> right = null) {
        new BinaryNode<Integer>(value, left, right)
    }
}
