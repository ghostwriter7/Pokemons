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
        input                           || expected
        [] as Comparable[]              || [] as Comparable[]
        [1] as Comparable[]             || [1] as Comparable[]
        [-5, 10, -6, 2] as Comparable[] || [-6, -5, 2, 10] as Comparable[]
    }
}
