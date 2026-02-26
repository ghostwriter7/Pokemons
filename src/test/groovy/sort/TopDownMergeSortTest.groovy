package sort

import spock.lang.Specification
import spock.lang.Unroll

class TopDownMergeSortTest extends Specification {

    @Unroll
    def "should sort an array"() {

        when:
        TopDownMergeSort.sort(input)

        then:
        input == expected

        where:
        input                                       || expected
        (40..1) as Integer[]            || (1..40) as Integer[]
        [5, -4, 2, -11, 7] as Integer[] || [-11, -4, 2, 5, 7] as Integer[]
    }
}
