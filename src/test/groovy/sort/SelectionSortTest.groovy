package sort

import spock.lang.Specification

class SelectionSortTest extends Specification {
    def "should sort an array using Selection Sort algorithm"() {
        when:
        SelectionSort.sort(input)

        then:
        input == expected

        where:
        input || expected
        [] as Comparable[] || [] as Comparable[]
        [1] as Comparable[] || [1] as Comparable[]
        [-9, 2, 11, 4, 6, -3, -1] as Comparable[] || [-9, -3, -1, 2, 4, 6, 11] as Comparable[]
    }
}
