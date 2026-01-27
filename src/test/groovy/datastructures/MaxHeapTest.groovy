package datastructures

import spock.lang.Specification

class MaxHeapTest extends Specification {
    def "should perform heap operations correctly"() {
        given:
        def heap = new PriorityQueue.MaxHeap<Integer>()

        when:
        heap.insert(5)
        heap.insert(10)
        heap.insert(3)
        heap.insert(100)

        then:
        verifyAll(heap) {
            size() == 4
            peek() == 100
        }

        when:
        def value = heap.pop()

        then:
        verifyAll(heap) {
            size() == 3
            peek() == 10
            value == 100
        }

        when:
        def one = heap.pop()
        def two = heap.pop()
        def three = heap.pop()

        then:
        verifyAll(heap) {
            size() == 0
            one == 10
            two == 5
            three == 3
        }
    }
}
