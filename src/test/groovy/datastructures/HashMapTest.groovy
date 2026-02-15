package datastructures

import spock.lang.Specification

class HashMapTest extends Specification {

    def "should perform map operations correctly"() {
        given:
         def map = new HashMap(5)

        when:
        map.put('A', 10)

        then:
        verifyAll(map) {
            size() == 1
            !isEmpty()
            map.get('A') == 10
        }

        when:
        map.remove('A')

        then:
        verifyAll(map) {
            size() == 0
            isEmpty()
            map.get('A') == null
        }

        when:
        map.put('B', 20)
        map.put('C', 30)
        map.put('D', 40)

        then:
        verifyAll(map) {
            size() == 3
            !isEmpty()
            map.toString() == '[ B=20 C=30 D=40 ]'
        }

        when:
        var keySet = map.keySet()
        var values = map.values()

        then:
        keySet == ['B', 'C', 'D'] as Set
        values == [20, 30, 40]

        when:
        map.clear()

        then:
        verifyAll(map) {
            isEmpty()
            size() == 0
            map.get('B') == null
            map.get('C') == null
            map.get('D') == null
        }

        when:
        map.put('P', 26)
        map.put('H', 7)

        then:
        verifyAll(map) {
            containsKey('P')
            containsKey('H')
            !containsKey('D')
            containsValue(26)
            containsValue(7)
            !containsValue(100)
        }
    }
}
