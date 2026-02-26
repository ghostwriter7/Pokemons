package sort;

class BottomUpMergeSort {

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> void sort(T[] input) {
        var n = input.length;
        var aux = (T[]) new Comparable[n];

        // sz = current run length; after each pass, runs of length 2*sz are sorted.
        for (var sz = 1; sz < n; sz *= 2) {
            for (var lo = 0; lo < n - sz; lo += sz + sz) {
                var mid = lo + sz - 1;
                var hi = Math.min(lo + 2 * sz - 1, n - 1);

                // Optional micro-optimisation: if already ordered, skip merge.
                // This is safe because both halves are sorted by the loop invariant.
                if (lessOrEqual(input[mid], input[mid + 1])) continue;

                merge(input, aux, lo, mid, hi);
            }
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] input, T[] aux, int lo, int mid, int hi) {
        System.arraycopy(input, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;

        for (var k = lo; k <= hi; k++) {
            if (i > mid) input[k] = aux[j++];
            else if (j > hi) input[k] = aux[i++];
            else if (lessOrEqual(aux[i], aux[j])) input[k] = aux[i++];
            else input[k] = aux[j++];
        }
    }

    private static <T extends Comparable<? super T>> boolean lessOrEqual(T v, T w) {
        return v.compareTo(w) <= 0;
    }
}
