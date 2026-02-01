package sort;

import java.util.Random;

/**
 * O(n logn)
 * O(n * n) - worst case - reverse sorted array
 * Pick the middle element for the best output
 */
public class QuickSort {
    private static final Random random = new Random();

    public static void quickSort(int[] array, int lo, int hi, PivotStrategy strategy) {
        if (lo >= hi) return;

        var pivotIndex = partition(array, lo, hi, strategy);
        quickSort(array, lo, pivotIndex - 1, strategy);
        quickSort(array, pivotIndex + 1, hi, strategy);
    }

    private static int partition(int[] array, int lo, int hi, PivotStrategy strategy) {
        int pivot = getPivot(array, lo, hi, strategy);

        int idx = lo - 1;

        for (var i = lo; i < hi; i++) {
            if (array[i] <= pivot) {
                swap(array, i, ++idx);
            }
        }

        swap(array, hi, ++idx);
        return idx;
    }

    private static int getPivot(int[] array, int lo, int hi, PivotStrategy strategy) {
        return switch (strategy) {
            case LAST -> array[hi];
            case RANDOM -> {
                var idx = random.nextInt(lo, hi + 1);
                swap(array, hi, idx);
                yield array[hi];
            }
            case MEDIAN_OF_THREE -> {
                int midIndex = lo + (hi - lo) / 2;
                int low = array[lo];
                int mid = array[midIndex];
                int high = array[hi];

                int medianIndex;

                if ((low <= mid && mid <= high) || (high <= mid && mid <= low)) {
                    medianIndex = midIndex;
                } else if ((mid <= low && low <= high) || (high <= low && low <= mid)) {
                    medianIndex = lo;
                } else {
                    medianIndex = hi;
                }
                swap(array, hi, medianIndex);
                yield array[hi];
            }
        };
    }

    private static void swap(int[] array, int a, int b){
        var temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    enum PivotStrategy {
        LAST,
        RANDOM,
        MEDIAN_OF_THREE
    }
}
