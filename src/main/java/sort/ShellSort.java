package sort;

public class ShellSort {
    private ShellSort() {}

    public static <T> void sort(Comparable<T>[] input) {
        int n = input.length;

        int h = 1;
        // computes the increment sequence
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (var i = h; i < n; i++) {
                // insertion sort with h-step
                for (var j = i; j >= h && less(input[j], input[j - h]); j -= h) {
                    swap(input, j, j - h);
                }
            }

            h /= 3; // moves to the next increment
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
        return v.compareTo((T) w) < 0;
    }

    private static <T> void swap(Comparable<T>[] input, int i, int j) {
        var temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
