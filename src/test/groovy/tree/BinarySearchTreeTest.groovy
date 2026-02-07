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

    @Unroll
    def "should insert #value into a binary search tree"() {
        when:
        BinarySearchTree.insert(root, value)

        then:
        BinarySearchTree.print(root) == expected

        where:
        root                                              | value || expected
        node(5)                                        | 10    || '[ 5 10 ]'
        node(10, node(8), node(12, node(11))) | 13    || '[ 10 8 12 11 13 ]'
    }

    @Unroll
    def "should remove a node"() {
        given:
        def toBeDeleted = node(18, node(17, node(20)))
        def root = node(
                10, node(7, node(5), node(6)),
                node(15, node(12), toBeDeleted))

        when:
        BinarySearchTree.delete(toBeDeleted)

        then:
        BinarySearchTree.print(root) == '[ 10 7 5 6 15 12 17 20 ]'
    }

    BinarySearchTree.BinaryNode<Integer> node(int value, BinarySearchTree.BinaryNode<Integer> left = null, BinarySearchTree.BinaryNode<Integer> right = null) {
        new BinarySearchTree.BinaryNode<Integer>(value, left, right)
    }
}
