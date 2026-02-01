package sort

import spock.lang.Specification
import spock.lang.Unroll

class QuickSortTest extends Specification {

    @Unroll
    def "should sort using a Quick Sort algorithm"() {
        when:
        QuickSort.quickSort(input, 0, input.length - 1, strategy)

        then:
        input == expected

        where:
        strategy                                | input                          || expected
        QuickSort.PivotStrategy.LAST            | [] as int[]                    || [] as int[]
        QuickSort.PivotStrategy.RANDOM          | [] as int[]                    || [] as int[]
        QuickSort.PivotStrategy.MEDIAN_OF_THREE | [] as int[]                    || [] as int[]
        QuickSort.PivotStrategy.LAST            | [1] as int[]                   || [1] as int[]
        QuickSort.PivotStrategy.RANDOM          | [1] as int[]                   || [1] as int[]
        QuickSort.PivotStrategy.MEDIAN_OF_THREE | [1] as int[]                   || [1] as int[]
        QuickSort.PivotStrategy.LAST            | [9, 8, 7, 6, 5] as int[]       || [5, 6, 7, 8, 9] as int[]
        QuickSort.PivotStrategy.RANDOM          | [9, 8, 7, 6, 5] as int[]       || [5, 6, 7, 8, 9] as int[]
        QuickSort.PivotStrategy.MEDIAN_OF_THREE | [9, 8, 7, 6, 5] as int[]       || [5, 6, 7, 8, 9] as int[]
        QuickSort.PivotStrategy.LAST            | [-10, 2, 5, 99] as int[]       || [-10, 2, 5, 99] as int[]
        QuickSort.PivotStrategy.RANDOM          | [-10, 2, 5, 99] as int[]       || [-10, 2, 5, 99] as int[]
        QuickSort.PivotStrategy.MEDIAN_OF_THREE | [-10, 2, 5, 99] as int[]       || [-10, 2, 5, 99] as int[]
        QuickSort.PivotStrategy.LAST            | [100, -10, 25, 3, -5] as int[] || [-10, -5, 3, 25, 100] as int[]
        QuickSort.PivotStrategy.RANDOM          | [100, -10, 25, 3, -5] as int[] || [-10, -5, 3, 25, 100] as int[]
        QuickSort.PivotStrategy.MEDIAN_OF_THREE | [100, -10, 25, 3, -5] as int[] || [-10, -5, 3, 25, 100] as int[]
    }
}
