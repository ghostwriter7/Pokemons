package sort;

class TopDownMergeSort {

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> void sort(T[] arr) {
        T[] aux = (T[]) new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] array, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        var mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);

        if (lessOrEqual(array[mid], array[mid + 1])) return;

        merge(array, aux, lo, mid, hi);
    }

    private static <T extends Comparable<? super T>> void merge(T[] array, T[] aux, int lo, int mid, int hi) {
        assert isSorted(array, lo, mid); // Precondition, array[lo..mid] must be sorted
        assert isSorted(array, mid + 1, hi); // Precondition, array[mid + 1..hi] must be sorted

        System.arraycopy(array, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid + 1;
        for (var k = lo; k <= hi; k++) {
            if (i > mid)                               array[k] = aux[j++];
            else if (j > hi)                           array[k] = aux[i++];
            else if (lessOrEqual(aux[i], aux[j]))      array[k] = aux[i++];
            else                                       array[k] = aux[j++];
        }

        assert isSorted(array, lo, hi);
    }

    private static <T extends Comparable<? super T>> boolean lessOrEqual(T v, T w) {
        return v.compareTo(w) <= 0;
    }

    private static <T extends Comparable<? super T>> boolean isSorted(T[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (lessOrEqual(array[i], array[i - 1])) return false;
        }
        return true;
    }
}
