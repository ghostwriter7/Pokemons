package search;

public class LinearSearch {

    public static int search(int[] haystack, int needle) {
        for (var i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return i;
            }
        }

        return -1;
    }
}
