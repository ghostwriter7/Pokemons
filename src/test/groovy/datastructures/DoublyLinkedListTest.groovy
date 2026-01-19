package datastructures

import spock.lang.Specification

class DoublyLinkedListTest extends Specification {

    def "complex scenario: insert, prepend, append, remove, and get"() {
        given: "An empty list"
        IList<Integer> list = new DoublyLinkedList<>()

        expect: "size is initially 0"
        list.size() == 0

        when: "we prepend and append elements"
        list.prepend(10)      // [10]
        list.append(20)            // [10, 20]
        list.prepend(5)       // [5, 10, 20]
        list.append(30)            // [5, 10, 20, 30]

        then: "size reflects number of elements"
        list.size() == 4

        and: "elements are in correct order"
        list.get(0) == 5
        list.get(1) == 10
        list.get(2) == 20
        list.get(3) == 30

        when: "we insert at middle and end"
        list.insertAt(15, 2)       // [5, 10, 15, 20, 30]
        list.insertAt(35, list.size()) // [5, 10, 15, 20, 30, 35]

        then: "size and order are correct"
        list.size() == 6
        list.get(2) == 15
        list.get(5) == 35

        when: "we remove by value"
        def removed20 = list.remove(20)   // [5, 10, 15, 30, 35]
        def removed99 = list.remove(99)   // null

        then: "removed values are returned correctly"
        removed20 == 20
        removed99 == null
        list.size() == 5
        list.get(3) == 30

        when: "we remove by index"
        def removedFirst = list.removeAt(0) // [10, 15, 30, 35]
        def removedLast = list.removeAt(list.size() - 1) // [10, 15, 30]

        then: "removed elements match expected"
        removedFirst == 5
        removedLast == 35
        list.size() == 3
        list.get(0) == 10
        list.get(2) == 30

        when: "we test invalid operations"
        list.removeAt(10)
        list.get(10)

        then: "IndexOutOfBoundsException is thrown"
        thrown(IndexOutOfBoundsException)

        when: "we insert at size (append via insertAt)"
        list.insertAt(40, list.size()) // [10, 15, 30, 40]

        then:
        list.size() == 4
        list.get(3) == 40

        and: "full list matches expected sequence"
        def expected = [10, 15, 30, 40]
        (0..<list.size()).every { idx -> list.get(idx) == expected[idx] }
    }
}
