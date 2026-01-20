package sort;

/**
 * @see <a href="https://www.geeksforgeeks.org/dsa/selection-sort-algorithm-2/">Selection Sort</a>
 */
public class SelectionSort {

    /**
     * 1. Iterate over the entire array
     * 2. At each iteration, find the smallest item in the unsorted section of the array
     * 3. Swap the smallest item with the current index
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        for (var i = 0; i < array.length - 1; i++) {
            var minIndex = i;

            for (var j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                var temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
