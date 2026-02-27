package select

import spock.lang.Specification

class QuickSelectTest extends Specification {
    def "should find kth-element recursively"() {
        given:
        def elements = [-5, 10, -3, 12, 200, 100, 35] as Integer[]

        expect:
        QuickSelect.kthSmallest(elements, 4, 0, elements.length - 1) == 35
    }

    def "should find kth-element without recursion"() {
        given:
        def elements = [-5, 10, -3, 12, 200, 100, 35] as Integer[]

        expect:
        QuickSelect.kthSmallest(elements, 4) == 35
    }
}
