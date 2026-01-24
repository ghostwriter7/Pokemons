package tree

import spock.lang.Specification

class PreOrderTraversalTest extends Specification {

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
        def result = PreOrderTraversal.traverse(head)

        then:
        result == [50, 10, 30, 40, 20, 5, -69]
    }
}
