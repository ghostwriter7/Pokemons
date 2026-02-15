package datastructures.graph

import datastructures.graph.DijkstraShortestPath.GraphEdge
import spock.lang.Specification

class DijkstraShortestPathTest extends Specification {

    def "should return an empty when there is no path to a target"() {
        given:
        def graph = [
                [edge(1, 5), edge(3, 3)],
                [edge(2, 4)],
                [edge(3, 2), edge(4, 1)],
                [edge(4, 2)],
                [edge(0, 10)],
                []
        ] as GraphEdge[][]

        expect:
        DijkstraShortestPath.find(graph, 0, 5) == []

    }

    def "should return the shortest path to a target"() {
        given:
            def graph = [
                    [edge(1, 5), edge(3, 3)],
                    [edge(2, 4)],
                    [edge(3, 2), edge(4, 1)],
                    [edge(4, 2)],
                    [edge(0, 10)]
            ] as GraphEdge[][]

        expect:
        DijkstraShortestPath.find(graph, 0, 4) == [0, 3, 4]
    }

    private static GraphEdge edge(int to, int weight) {
        new GraphEdge(to, weight)
    }
}
