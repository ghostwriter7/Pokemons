package search

import spock.lang.Specification

class LinearSearchTest extends Specification {

    def "should search for a #needle and return #expectedIndex"() {
        expect:
        LinearSearch.search(haystack, needle) == expectedIndex

        where:
        haystack                     | needle || expectedIndex
        [] as int[]                  | 5      || -1
        [1] as int[]                 | 1      || 0
        [5] as int[]                 | 1      || -1
        [1, 10, -9, 20, 25] as int[] | 20     || 3
        [1, 10, -9, 20, 25] as int[] | 21     || -1
    }
}
