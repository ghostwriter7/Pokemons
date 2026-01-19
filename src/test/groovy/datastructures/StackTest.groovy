package datastructures

import spock.lang.Specification

class StackTest extends Specification {
    def "should perform stack operations properly"() {
        given: 'a stack is created'
        IStack<Character> stack = new Stack<>()

        when: 'values are added'
        ('A'..'C').forEach { stack.push(it) }

        then: 'peek and pop work'
        stack.peek() == 'C'
        stack.pop() == 'C'
        stack.peek() == 'B'
    }

    def "iterator should throw an exception when stack is empty and next is accessed"() {
        given: 'an empty stack'
        def stack = new Stack<Integer>()

        and: 'an iterator exists'
        def iter = stack.iterator()

        when:
        iter.next()

        then:
        thrown(NoSuchElementException)
    }

    def "iterator should throw an exception when modified concurrently"() {
        given: 'a stack exists'
        def stack = new Stack<Integer>()
        stack.push(5)

        and: 'an iterator is created'
        def iter = stack.iterator()

        and: 'the stack is modified'
        stack.pop()

        when: 'the iterator is utilised'
        iter.hasNext()

        then:
        thrown(ConcurrentModificationException)
    }
}
