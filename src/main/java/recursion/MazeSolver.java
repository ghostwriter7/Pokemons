package recursion;

import java.util.*;

/**
 * Find the path from S to E
 * Example input:
 *  [
 *      "######E##",
 *      "#       #",
 *      "#S#######",
 *  ]
 *
 *  Base cases:
 *  1. It is a wall (#)
 *  2. It is off the graph
 *  3. It is a finish point (E)
 *  4. We've seen it already
 */
public class MazeSolver {

    private enum Direction {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final Direction[] DIRECTIONS = Direction.values();

    public record Point(int x, int y) {}

    public static Deque<Point> solveMaze(String[] maze, Point startPoint) {
        Deque<Point> path = new LinkedList<>();
        Set<Point> seen = new HashSet<>();

        walk(maze, startPoint, path, seen);

        return path;
    }

    private static boolean walk(String[] maze, Point current, Deque<Point> path, Set<Point> seen) {
        var x = current.x;
        var y = current.y;

        if (y < 0 || y >= maze.length || x < 0 || x >= maze[0].length()) {
            return false;
        }

        if (seen.contains(current)) return false;

        var charAtCurrentPoint = maze[y].charAt(x);

        if (charAtCurrentPoint == '#') {
            return false;
        }

        if (charAtCurrentPoint == 'E') {
            return true;
        }

        seen.add(current);

        for (var direction : DIRECTIONS) {
            var nextPoint = new Point(x + direction.x, y + direction.y);

            path.push(nextPoint);

            if (walk(maze, nextPoint, path, seen)) {
                return true;
            } else {
                path.pop();
            }
        }

        return false;
    }

}
