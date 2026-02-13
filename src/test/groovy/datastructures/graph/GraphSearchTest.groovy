package datastructures.graph

import spock.lang.Specification

class GraphSearchTest extends Specification {

    /**
     * 0 → 1 → 3 → 4
     *  \→ 2 ───────┘
     */
    def "should return a path to a target"() {
        given:
        def adjMatrix = [
                [0, 1, 1, 0, 0],  // 0 -> 1,2
                [0, 0, 0, 1, 0],  // 1 -> 3
                [0, 0, 0, 0, 1],  // 2 -> 4
                [0, 0, 0, 0, 1],  // 3 -> 4
                [0, 0, 0, 0, 0]   // 4
        ] as int[][]

        when:
        def path = GraphSearch.bfsOnAdjacencyMatrix(adjMatrix, 0, 4)

        then:
        path == [0, 2, 4]
    }

    def "should return [] when there is no path to a target"() {
        given: """Component 1: 0 → 1 → 2
                  Component 2: 3 → 4"""
        def adjMatrix = [
                [0, 1, 0, 0, 0],  // 0 -> 1
                [0, 0, 1, 0, 0],  // 1 -> 2
                [0, 0, 0, 0, 0],  // 2
                [0, 0, 0, 0, 1],  // 3 -> 4
                [0, 0, 0, 0, 0]   // 4
        ] as int[][]

        when:
        def path = GraphSearch.bfsOnAdjacencyMatrix(adjMatrix, 0, 4)

        then:
        path == []
    }
}
