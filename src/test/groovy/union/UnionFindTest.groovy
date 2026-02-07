package union

import spock.lang.Specification

class UnionFindTest extends Specification {

    def "should build a UnionFind properly"() {
        given:
        def uf = new UnionFind(10)

        when: 'populated with initial data'
        [
                [4, 3],
                [3, 8],
                [6, 5],
                [9, 4],
                [2, 1],
                [8, 9],
                [5, 0],
                [7, 2],
                [6, 1]
        ]
                .forEach {
                    if (!uf.isConnected(it[0], it[1])) uf.union(it[0], it[1])
                }

        then: 'items are correctly grouped'
        verifyAll {
            uf.toString() == [1, 1, 1, 8, 8, 1, 1, 1, 8, 8].toString()
            uf.count() == 2
        }
    }
}
