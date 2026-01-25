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
        root.toString() == expected

        where:
        root                                              | value || expected
        mutNode(5)                                        | 10    || '[ 5 10 ]'
        mutNode(10, mutNode(8), mutNode(12, mutNode(11))) | 13    || '[ 10 8 12 11 13 ]'
    }

    @Unroll
    def "should remove a node"() {
        given:
        def toBeDeleted = mutNode(18, mutNode(17, mutNode(20)))
        def root = mutNode(
                10, mutNode(7, mutNode(5), mutNode(6)),
                mutNode(15, mutNode(12), toBeDeleted))

        when:
        BinarySearchTree.delete(toBeDeleted)

        then:
        root.toString() == '[ 10 7 5 6 15 12 17 20 ]'
    }


    BinaryNode<Integer> node(int value, BinaryNode<Integer> left = null, BinaryNode<Integer> right = null) {
        new BinaryNode<Integer>(value, left, right)
    }

    MutableBinaryNode<Integer> mutNode(int value, MutableBinaryNode<Integer> left = null, MutableBinaryNode<Integer> right = null) {
        new MutableBinaryNode<Integer>(value, left, right)
    }
}
