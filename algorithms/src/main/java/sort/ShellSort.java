package sort;

import static sort.utils.SortUtils.exchange;
import static sort.utils.SortUtils.isLess;

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
}
