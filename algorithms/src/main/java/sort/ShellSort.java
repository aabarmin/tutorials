package sort;

public class ShellSort {
    public int[] sort(int[] source) {
        int n = source.length;
        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && isLess(source[j], source[j - h]); j = j - h) {
                    exchange(source, j, j - h);
                }
            }
            h = h / 3;
        }

        return source;
    }

    private static boolean isLess(int a, int b) {
        return a < b;
    }

    private static void exchange(int[] source, int from, int to) {
        int buffer = source[from];
        source[from] = source[to];
        source[to] = buffer;
    }
}
