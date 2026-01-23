package sort

import spock.lang.Specification
import spock.lang.Unroll

class QuickSortTest extends Specification {

    @Unroll
    def "should sort using a Quick Sort algorithm"() {
        when:
        QuickSort.quickSort(input, 0, input.length - 1)

        then:
        input == expected

        where:
        input                          || expected
        [] as int[]                    || [] as int[]
        [1] as int[]                   || [1] as int[]
        [9, 8, 7, 6, 5] as int[]       || [5, 6, 7, 8, 9] as int[]
        [-10, 2, 5, 99] as int[]       || [-10, 2, 5, 99] as int[]
        [100, -10, 25, 3, -5] as int[] || [-10, -5, 3, 25, 100] as int[]
    }
}
