package datastructures

import spock.lang.Specification

class QueueTest extends Specification {

    def "should perform queue operations properly"() {
        given: 'a new Queue is created'
        IQueue<Integer> queue = new Queue<>()

        expect: 'initial checks are valid'
        verifyAll {
            queue.isEmpty()
            queue.size() == 0
        }

        when: 'items are offered'
        queue.offer(5)
        queue.offer(10)
        queue.offer(15)

        then: 'counts and head are intact'
        verifyAll {
            !queue.isEmpty()
            queue.size() == 3
            queue.peek() == 5
        }

        when: 'item is removed'
        Integer value = queue.poll()

        then: 'queue is in a valid state'
        verifyAll {
            value == 5
            queue.size() == 2
            queue.peek() == 10
        }

        when: 'queue is emptied'
        Integer first = queue.poll()
        Integer second = queue.poll()
        Integer third = queue.poll()

        then: 'values are valid'
        verifyAll {
            first == 10
            second == 15
            third == null
            queue.isEmpty()
            queue.size() == 0
        }
    }

    def "should support iteration"() {
        given: 'a queue exists with items'
        IQueue<Integer> queue = new Queue<>()
        (1..3).forEach { queue.offer(it) }

        and: 'an iterator exists'
        Iterator<Integer> iter = queue.iterator()

        expect:
        verifyAll {
            iter.hasNext()
            iter.next() == 1
            iter.hasNext()
            iter.next() == 2
            iter.hasNext()
            iter.next() == 3
            !iter.hasNext()
        }
    }
    
    def "iterator should throw an exception when exhausted"() {
        given: 'a queue exists with items'
        IQueue<Integer> queue = new Queue<>()
        queue.offer(1)

        when:
        Iterator<Integer> iter = queue.iterator()
        iter.next()
        iter.next()

        then:
        thrown(NoSuchElementException)
    }

    def "iterator should throw an exception when noticing a concurrent modification"() {
        given: 'a queue exists with items'
        IQueue<Integer> queue = new Queue<>()
        queue.offer(1)

        and: 'an iterator is created'
        Iterator<Integer> iter = queue.iterator()

        and: 'an element is added to a queue'
        queue.offer(2)

        when: 'an iterator is used'
        iter.next()

        then: 'a concurrent modification is detected'
        thrown(ConcurrentModificationException)
    }
}
