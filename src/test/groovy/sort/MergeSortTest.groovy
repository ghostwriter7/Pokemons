package sort

import spock.lang.Specification
import spock.lang.Unroll

class MergeSortTest extends Specification {

    @Unroll
    def "should sort an array"() {

        when:
        MergeSort.sort(input)

        then:
        input == expected

        where:
        input                                       || expected
        (40..1) as Comparable<Integer>[]            || (1..40) as Comparable<Integer>[]
        [5, -4, 2, -11, 7] as Comparable<Integer>[] || [-11, -4, 2, 5, 7] as Comparable<Integer>[]
    }
}
