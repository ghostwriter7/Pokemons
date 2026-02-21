package tree

import spock.lang.Specification
import spock.lang.Unroll

class RetrievalTreeTest extends Specification {
    def "should print its content in alphabetical order"() {
        given:
        def trie = new RetrievalTree()
        populateTrie(trie)

        expect:
        trie.toString() == 'angle apple cat clown demon dragon shepard'
    }

    @Unroll
    def "should return #expected when checking whether #input is prefix"() {
        given:
        def trie = new RetrievalTree()
        populateTrie(trie)

        expect:
        trie.isPrefix(input) == expected

        where:
        input   || expected
        'ca'    || true
        'clo'   || true
        'shep'  || true
        'shi'   || false
        'den'   || false
        'apply' || false
    }

    @Unroll
    def "should return #expected when searching for #input"() {
        given:
        def trie = new RetrievalTree()
        populateTrie(trie)

        expect:
        trie.search(input) == expected

        where:
        input          || expected
        'cat'          || true
        'car'          || false
        'application ' || false
        'dragon'       || true
        'drago'        || false
    }

    void populateTrie(RetrievalTree retrievalTree) {
        ['cat', 'clown', 'dragon', 'apple', 'shepard', 'angle', 'demon']
                .forEach { retrievalTree.insert(it) }
    }
}
