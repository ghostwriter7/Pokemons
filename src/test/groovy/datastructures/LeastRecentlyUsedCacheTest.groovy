package datastructures

import spock.lang.Specification

class LeastRecentlyUsedCacheTest extends Specification {
    def "should perform LRU Cache operations correctly"() {
        given:
        def cache = new LeastRecentlyUsedCache(3)

        when:
        cache.put('A', 10)
        cache.put('B', 20)
        cache.put('C', 30)

        then:
        verifyAll(cache) {
            !isEmpty()
            get('A') == 10
            get('B') == 20
            get('C') == 30
            get('D') == null
        }

        when:
        cache.put('D', 40)

        then:
        verifyAll(cache) {
            cache.get('D') == 40
            cache.get('A') == null
        }

        when:
        cache.put('D', 50)
        cache.put('E', 60)

        then:
        verifyAll(cache) {
            get('D') == 50
            get('E') == 60
            get('C') == 30
            get('B') == null
        }
    }
}
