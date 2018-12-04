package sort;

import static sort.utils.SortUtils.exchange;
import static sort.utils.SortUtils.isLess;

public class InsertionSort {
    public int[] sort(int[] source) {
        for (int i = 1; i < source.length; i++) {
            for (int j = i; j > 0 && isLess(source[j], source[j - 1]); j--) {
                exchange(source, j, j - 1);
            }
        }
        return source;
    }
}
