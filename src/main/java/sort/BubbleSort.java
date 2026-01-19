package sort;

public class BubbleSort {

    public static void sort(int[] input) {
        for (var i = 0; i < input.length; i++) {
            var isUnchanged = true;

            for (var j = 0; j < input.length - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    var temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                    isUnchanged = false;
                }
            }

            if (isUnchanged) break;
        }
    }
}
