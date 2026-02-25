package shuffle;

import java.util.Random;

public class KnuthShuffle {
    private static final Random random = new Random();

    private KnuthShuffle() {}

    static void shuffle(Object[] array) {
        for (var i = 1; i < array.length; i++) {
            var r = random.nextInt(0, 1 + 1);
            var temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
    }
}
