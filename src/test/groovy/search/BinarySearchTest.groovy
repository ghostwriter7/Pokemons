package search

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchTest extends Specification {

    @Unroll
    def "should search for a #needle and return #expectedIndex"() {
        expect:
        BinarySearch.search(haystack, needle) == expectedIndex

        where:
        haystack                                 | needle || expectedIndex
        [] as int[]                              | 5      || -1
        [1] as int[]                             | 1      || 0
        [2] as int[]                             | 1      || -1
        [1, 2, 3, 4] as int[]                    | 3      || 2
        [5, 10, 15, 20, 25, 30, 35, 40] as int[] | 30     || 5
        [5, 10, 15, 20, 25, 30, 35, 40] as int[] | 31     || -1
    }
}
