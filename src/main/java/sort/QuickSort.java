package sort;

/**
 * O(n logn)
 * O(n * n) - worst case - reverse sorted array
 * Pick the middle element for the best output
 */
public class QuickSort {

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) return;

        var pivotIndex = partition(array, lo, hi);
        quickSort(array, lo, pivotIndex - 1); // sort left side excluding pivot
        quickSort(array, pivotIndex + 1, hi); // sort right side excluding pivot
    }

    private static int partition(int[] array, int lo, int hi) {
        int pivot = array[hi]; // not always optimal to pick the last value

        int idx = lo - 1; // start off the array

        for (var i = lo; i < hi; i++) {
            if (array[i] <= pivot) {
                // value is smaller or equal than pivot, therefore, it needs to be placed on the left side of the pivot
                idx++;
                var temp = array[i];
                array[i] = array[idx];
                array[idx] = temp;
            }
        }

        // swap pivot to put it in the proper place
        idx++;
        array[hi] = array[idx];
        array[idx] = pivot;

        return idx;
    }


    private static int pickPivot(int start, int end) {
        return start + (end - start);
    }
}
