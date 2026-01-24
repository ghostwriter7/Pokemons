package tree

import spock.lang.Specification

class PostOrderTraversalTest extends Specification {

    def "should traverse a tree"() {
        given:
        def head = new BinaryNode(50,
                new BinaryNode(10,
                        new BinaryNode(30, null, null),
                        new BinaryNode(40, null, null)
                ),
                new BinaryNode(20,
                        new BinaryNode(5, null, null),
                        new BinaryNode(-69, null, null)
                )
        )

        when:
        def result = PostOrderTraversal.traverse(head)

        then:
        result == [30, 40, 10, 5, -69, 20, 50]
    }
}
