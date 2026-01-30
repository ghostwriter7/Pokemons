package datastructures

import spock.lang.Specification

class QuickUnionTest extends Specification {
    def "should build a QuickUnion properly"() {
        given:
        def uf = new QuickUnion(10)

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
                [6, 1],
                [7, 3]
        ]
                .forEach {
                    if (!uf.isConnected(it[0], it[1])) uf.union(it[0], it[1])
                }

        then: 'items are correctly grouped'
        verifyAll {
            uf.toString() == [1, 8, 1, 8, 3, 0, 5, 1, 8, 8].toString()
            uf.count() == 1
        }
    }
}
