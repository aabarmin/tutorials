package sort;

import static sort.utils.SortUtils.exchange;
import static sort.utils.SortUtils.isLess;

public class QuickSort {
    private void sort(int[] source, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(source, lo, hi);
        sort(source, lo, j - 1);
        sort(source, j + 1, hi);
    }

    private int partition(int[] source, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = source[lo];

        while (true) {
            while (isLess(source[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (isLess(v, source[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exchange(source, i, j);
        }

        exchange(source, lo, j);

        return j;
    }

    public int[] sort(int[] source) {
        sort(source, 0, source.length - 1);
        return source;
    }
}
