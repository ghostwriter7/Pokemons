package datastructures

import spock.lang.Specification

import java.nio.BufferOverflowException
import java.nio.BufferUnderflowException

class RingBufferTest extends Specification {

    def "should perform all operations correctly"() {
        given: 'an empty RingBuffer'
        def buffer = new RingBuffer<Integer>(4)

        when: 'values are added until it is full'
        (1..4).forEach { buffer.push(it) }

        then:
        verifyAll {
            buffer.size() == 4
            buffer.isFull()
            !buffer.isEmpty()
            buffer.getFirst() == 1
            buffer.getLast() == 4
            buffer.toString() == '[1, 2, 3, 4]'
        }

        when: 'another value is added'
        buffer.push(5)

        then: 'an Overflow exception is thrown'
        thrown(BufferOverflowException)

        when: 'a value is removed'
        def value = buffer.pop()

        then:
        verifyAll {
            value == 1
            !buffer.isEmpty()
            !buffer.isFull()
            buffer.getFirst() == 2
            buffer.size() == 3
        }

        when: 'all values are removed'
        def first = buffer.pop()
        def second = buffer.pop()
        def third = buffer.pop()

        then:
        verifyAll {
            first == 2
            second == 3
            third == 4
            buffer.size() == 0
            buffer.isEmpty()
            !buffer.isFull()
        }

        when: 'a caller attempts to remove a value from an empty buffer'
        buffer.pop()

        then:
        thrown(BufferUnderflowException)
    }

    def "should provide a functional iterator"() {
        given: 'a ring buffer with data'
        def buffer = new RingBuffer<Integer>(5)
        (1..4).forEach { buffer.push(it)}
        def iterator = buffer.iterator()

        expect:
        verifyAll {
            iterator.hasNext()
            iterator.next() == 1
            iterator.hasNext()
            iterator.next() == 2
            iterator.hasNext()
            iterator.next() == 3
            iterator.hasNext()
            iterator.next() == 4
            !iterator.hasNext()
        }
    }
}
