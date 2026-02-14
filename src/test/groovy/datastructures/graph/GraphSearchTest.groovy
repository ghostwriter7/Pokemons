package datastructures.graph

import datastructures.graph.GraphSearch.GraphEdge
import spock.lang.Specification

class GraphSearchTest extends Specification {

    /**
     * 0 → 1 → 3 → 4
     *  \→ 2 ───────┘
     */
    def "should return a path to a target (BFS on Adjacency Matrix)"() {
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

    def "should return [] when there is no path to a target (BFS on Adjacency Matrix)"() {
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

    def "should return a path to a target (DFS on Adjacency List)"() {
        given:
            def adjList = [
                    [edge(1), edge(3)],
                    [edge(2), edge(4)],
                    [],
                    [edge(1), edge(4)],
                    [edge(5)],
                    []
            ] as GraphEdge[][]

        when:
            def result = GraphSearch.dfsOnAdjacencyList(adjList, 0, 5)

        then:
            result == [0, 1, 4, 5]
    }

    def "should return [] when there is no path to a target (DFS on Adjacency List)"() {
        given:
        def adjList = [
                [edge(1), edge(3)],
                [edge(2), edge(4)],
                [edge(4), edge(5)],
                [edge(1), edge(4)],
                [edge(5)],
                []
        ] as GraphEdge[][]

        when:
        def result = GraphSearch.dfsOnAdjacencyList(adjList, 1, 3)

        then:
        result == []
    }

    private static GraphEdge edge(int to) {
        new GraphEdge(to, 5)
    }
}
