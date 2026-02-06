package datastructures.heap


import spock.lang.Specification

class MinHeapTest extends Specification {

    def "should perform heap operations correctly"() {
        given:
        def heap = new MinHeap<Integer>(10)

        when:
        heap.insert(100)
        heap.insert(50)
        heap.insert(-33)

        then:
        verifyAll(heap) {
            size() == 3
            peek() == -33
        }

        when:
        def value = heap.pop()

        then:
        verifyAll(heap) {
            size() == 2
            value == -33
            peek() == 50
        }

        when:
        value = heap.pop()
        def value2 = heap.pop()

        then:
        verifyAll(heap) {
            value == 50
            value2 == 100
            size() == 0
        }
    }
}
