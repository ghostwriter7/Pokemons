package datastructures.graph;

import java.util.*;

public final class GraphSearch {

    public static List<Integer> bfsOnAdjacencyMatrix(int[][] adjMatrix, int source, int target) {
        var n = adjMatrix.length;

        var prev = new int[n];
        Arrays.fill(prev, -1);

        var seen = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        seen[source] = true;

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current == target) return buildPath(prev, target);

            for (var i = 0; i < n; i++) {
                if (!seen[i] && adjMatrix[current][i] != 0) {
                    seen[i] = true;
                    prev[i] = current;
                    queue.add(i);
                }
            }
        }

        return List.of();
}

    private static List<Integer> buildPath(int[] prev, int target) {
        List<Integer> out = new ArrayList<>();

        for (var at = target; at != -1; at = prev[at]) {
            out.add(at);
        }

        Collections.reverse(out);
        return out;
    }

    public static List<Integer> dfsOnAdjacencyList(GraphEdge[][] graph, int source, int target) {
        var n = graph.length;
        var visited = new boolean[n];
        List<Integer> path = new LinkedList<>();

        var found = walk(graph, source, target, path, visited);
        return found ? path : List.of();
    }

    private static boolean walk(GraphEdge[][] graph, int current, int target, List<Integer> path, boolean[] visited) {
        if (visited[current]) return false;

        visited[current] = true;
        path.add(current);

        if (current == target) return true;

        for (var i = 0; i < graph[current].length; i++) {
            var edge = graph[current][i];
            if (walk(graph, edge.to, target, path, visited)) return true;
        }

        path.removeLast();
        return false;
    }

    public record GraphEdge(int to, int weight) {}
}
