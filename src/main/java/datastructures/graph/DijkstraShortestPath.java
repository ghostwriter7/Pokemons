package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DijkstraShortestPath {
    public static List<Integer> find(GraphEdge[][] graph, int source, int target) {
        var n = graph.length;
        var visited = new boolean[n];

        var distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        var prev = new int[n];
        Arrays.fill(prev, -1);

        while (hasUnvisited(visited, distances)) {
            var current = getLowestUnvisited(visited, distances);
            visited[current] = true;

            for (var edge : graph[current]) {
                if (!visited[edge.to]) {
                    var dist = distances[current] + edge.weight;
                    if (dist < distances[edge.to]) {
                        distances[edge.to] = dist;
                        prev[edge.to] = current;
                    }
                }
            }
        }

        if (prev[target] == -1) return List.of();

        List<Integer> out = new ArrayList<>();

        for (var at = target; at != -1; at = prev[at]) {
            out.add(at);
        }

        Collections.reverse(out);
        return out;
    }

    private static boolean hasUnvisited(boolean[] visited, int[] distances) {
        return IntStream.range(0, visited.length)
                .anyMatch(i -> !visited[i] && distances[i] < Integer.MAX_VALUE);
    }

    public static int getLowestUnvisited(boolean[] visited, int[] distances) {
        var idx = -1;
        var lowestDist = Integer.MAX_VALUE;

        for (var i = 0; i < visited.length; i++) {
            if (!visited[i] && distances[i] < lowestDist) {
                idx = i;
                lowestDist = distances[i];
            }
        }

        return idx;
    }

    public record GraphEdge(int to, int weight) {}
}
