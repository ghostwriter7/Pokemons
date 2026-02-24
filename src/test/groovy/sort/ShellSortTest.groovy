package sort

import spock.lang.Specification
import spock.lang.Unroll

class ShellSortTest extends Specification {

    @Unroll
    def "should shell-sort the array"() {
        when:
        ShellSort.sort(input)

        then:
        input == expected

        where:
        input                            || expected
        (40..1) as Comparable<Integer>[] || (1..40) as Comparable<Integer>[]
    }
}
