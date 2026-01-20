package sort

import spock.lang.Specification
import spock.lang.Unroll

class InsertionSortTest extends Specification {

    @Unroll
    def "should sort using an Insertion Sort algorithm"() {
        when:
        InsertionSort.sort(input)

        then:
        input == expected

        where:
        input                  || expected
        [] as int[]              || [] as int[]
        [1] as int[]             || [1] as int[]
        [-5, 10, -6, 2] as int[] || [-6, -5, 2, 10] as int[]
    }
}
