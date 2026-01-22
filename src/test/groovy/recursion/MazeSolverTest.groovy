package recursion

import spock.lang.Specification
import spock.lang.Unroll

class MazeSolverTest extends Specification {

    @Unroll
    def "should solve a maze"() {

        expect:
        MazeSolver.solveMaze(maze, startPoint) == expected

        where:
        maze               | startPoint                 || expected
        [
                "#####E#",
                "#     #",
                "##S####"
        ] as String[]      | new MazeSolver.Point(2, 2) || createPoints([[5, 0], [5, 1], [4, 1], [3, 1], [2, 1]])
        [
                "###E#",
                "#   #",
                "#   #",
                "#   #",
                "#S###"
        ] as String[]      | new MazeSolver.Point(1, 4) || createPoints([[3, 0], [3, 1], [2, 1], [1, 1], [1, 2], [1, 3]])
    }

    private static List<MazeSolver.Point> createPoints(List<List<Integer>> points) {
        points.collect { new MazeSolver.Point(it[0], it[1]) }
    }
}
