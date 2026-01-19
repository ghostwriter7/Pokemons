package search;

public class BinarySearch {

    public static int search(int[] haystack, int needle) {
        if (haystack.length == 0) return -1;

        var lo = 0;
        var hi = haystack.length;

        do {
            var m = lo + (hi - lo) / 2;
            var v = haystack[m];

            if (v == needle) {
                return m;
            } else if (v > needle) {
                hi = m;
            } else {
                lo = m + 1;
            }
        } while (lo < hi);

        return -1;
    }
}
