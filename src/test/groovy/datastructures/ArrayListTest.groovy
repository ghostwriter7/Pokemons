package datastructures

import spock.lang.Specification

class ArrayListTest extends Specification {

    def "should perform all operations correctly"() {
        given: 'a new ArrayList'
        IList<Integer> list = new ArrayList<>();

        when: 'items are added'
        (1..10).forEach {list.append(it)}

        then: 'items are correctly stored'
        verifyAll {
            list.size() == 10
            list.get(0) == 1
            list.get(5) == 6
            list.get(9) == 10
        }

        when: 'items are removed'
        list.remove(3)
        def removed = list.removeAt(8)

        then: 'items are removed and remaining indices are shifted correctly'
        verifyAll {
            removed == 10
            list.size() == 8
            list.get(2) == 4
            list.get(7) == 9
        }

        when: 'items are inserted'
        list.insertAt(100, 1)
        list.insertAt(50, 9)

        then: 'items are shifted correctly'
        verifyAll {
            list.get(1) == 100
            list.get(2) == 2
            list.get(8) == 9
            list.get(9) == 50
            list.size() == 10
        }

        when: 'an item is prepended'
        list.prepend(-1)

        then: 'items are shifted'
        verifyAll {
            list.size() == 11
            list.get(0) == -1
            list.get(1) == 1
        }

        and: 'a final list is intact'
        def iter = list.iterator()
        def finalList = new java.util.ArrayList<>();
        iter.forEachRemaining(finalList::add)

        finalList == [-1, 1, 100, 2, 4, 5, 6, 7, 8, 9, 50]
    }
}
