package sort

import spock.lang.Specification
import spock.lang.Unroll

class BubbleSortTest extends Specification {

    @Unroll
    def "should sort the input array using a BubbleSort algorithm"() {
        when:
        BubbleSort.sort(input)

        then:
        input == expected

        where:
        input                                  || expected
        [] as int[]                            || [] as int[]
        [1] as int[]                           || [1] as int[]
        [1, 2, 3] as int[]                     || [1, 2, 3] as int[]
        [3, 2, 1] as int[]                     || [1, 2, 3] as int[]
        [7, 4, 2, 6, 11, -9, -5, 213] as int[] || [-9, -5, 2, 4, 6, 7, 11, 213] as int[]
    }
}
