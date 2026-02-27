package select;

class QuickSelect {

    static <T extends Comparable<? super T>> T kthSmallest(T[] array, int k) {
        if (array.length == 0) return null;
        if (k < 0 || k > array.length) throw new IllegalArgumentException("k must be in range [0, array.length)");

        var lo = 0;
        var hi = array.length - 1;

        while (lo < hi) {
            var j = partition(array, lo, hi);
            if (j == k) return array[j];
            else if (j < k) lo = j + 1;
            else hi = j - 1;
        }

        return array[lo];
    }

    static <T extends Comparable<? super T>> T kthSmallest(T[] array, int k, int lo, int hi) {
        if (k < 0 || k > array.length) throw new IllegalArgumentException("k must be in range [0, array.length)");

        int pivot = partition(array, lo, hi);

        if (pivot == k) return array[pivot];

        if (pivot < k) return kthSmallest(array, k, pivot + 1, hi);

        return kthSmallest(array, k, lo, pivot - 1);
    }

    private static <T extends Comparable<? super T>> int partition(T[] array, int lo, int hi) {
        var mid = lo + (hi - lo) / 2;
        var pivot = medianOfThree(array, lo, mid, hi);
        var pivotValue = array[pivot];
        swap(array, pivot, hi);

        var i = lo - 1;
        for (var j = lo; j < hi; j++) {
            if (array[j].compareTo(pivotValue) < 0) {
                swap(array, ++i, j);
            }
        }

        swap(array, ++i, hi);
        return i;
    }

    private static <T extends Comparable<? super T>> int medianOfThree(T[] array, int lo, int mid, int hi) {
        T a = array[lo], b = array[mid], c = array[hi];

        if (a.compareTo(b) <= 0) {
            if (b.compareTo(c) <= 0) return mid;
            if (a.compareTo(c) <= 0) return hi;
            return lo;
        }

        if (a.compareTo(c) <= 0) return lo;
        if (b.compareTo(c) <= 0) return hi;
        return mid;
    }

    private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
