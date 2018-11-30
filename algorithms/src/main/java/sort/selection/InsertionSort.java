package sort.selection;

public class InsertionSort {
    private static boolean isLess(int a, int b) {
        return a < b;
    }

    private static void exchange(int[] source, int a, int b) {
        int buffer = source[a];
        source[a] = source[b];
        source[b] = buffer;
    }

    public int[] sort(int[] source) {
        for (int i = 1; i < source.length; i++) {
            for (int j = i; j > 0 && isLess(source[j], source[j - 1]); j--) {
                exchange(source, j, j - 1);
            }
        }
        return source;
    }
}
