package sort

import spock.lang.Specification

class BottomUpMergeSortTest extends Specification {

    def "should sort using bottom-up merge sort"() {
        given:
        def input = (40..1) as Comparable[]

        when:
        BottomUpMergeSort.sort(input)

        then:
        input == (1..40) as Comparable[]
    }
}
