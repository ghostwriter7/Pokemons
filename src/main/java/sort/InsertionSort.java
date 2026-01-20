package sort;

/**
 * @see <a href="https://www.geeksforgeeks.org/dsa/insertion-sort-algorithm/">Insertion Sort</a>
 */
public class InsertionSort {

    /**
     * 1. Start at the 1st index. The 0th element is assumed to be sorted.
     * 2. Iterate backwards from the current index and shift every greater element to the right
     * 3. Do not swap -> it is inefficient
     * 4. At the end write the current value
     */
    public static <T extends Comparable<? super T>> void sort(T[] array) {
        for (var i = 1; i < array.length; i++) {
            var value = array[i];
            var prevIndex = i - 1;

            while (prevIndex >= 0 && array[prevIndex].compareTo(value) > 0) {
                array[prevIndex + 1] = array[prevIndex];
                prevIndex--;
            }
            array[prevIndex + 1] = value;
        }
    }
}
