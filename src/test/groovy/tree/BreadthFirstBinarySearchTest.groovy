package tree

import spock.lang.Specification
import spock.lang.Unroll

class BreadthFirstBinarySearchTest extends Specification {

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

    @Unroll
    def "should return #expected when searching for #needle"() {
        expect:
        BreadthFirstBinarySearch.search(head, needle) == expected

        where:
        needle || expected
        5      || true
        75     || false
        50     || true
        20     || true
    }
}
