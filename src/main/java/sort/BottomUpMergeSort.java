package sort;

class BottomUpMergeSort {

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> void sort(T[] input) {
        var n = input.length;
        var aux = (T[]) new Comparable[n];
        for (var sz = 1; sz < n; sz *= 2) {
            for (var lo = 0; lo < n - sz; lo += sz + sz) {
                merge(input, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] input, T[] aux, int lo, int mid, int hi) {
        if (hi <= lo) return;

        System.arraycopy(input, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;

        for (var k = lo; k <= hi; k++) {
            if (i > mid)                            input[k] = aux[j++];
            else if (j > hi)                        input[k] = aux[i++];
            else if (lessOrEqual(aux[i], aux[j]))   input[k] = aux[i++];
            else                                    input[k] = aux[j++];
        }
    }

    private static <T extends Comparable<? super T>> boolean lessOrEqual(T v, T w) {
        return v.compareTo(w) <= 0;
    }
}
