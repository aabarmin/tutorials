package sort.selection;

/**
 * Selection Sort.
 * N^2 / 2 complexity
 */
public class SelectionSort {
    public int[] sort(int[] source) {
        for (int i = 0; i < source.length - 1; i++) {
            int min = source[i];
            int minIndex = i;
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < min) {
                    min = source[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int buffer = source[i];
                source[i] = source[minIndex];
                source[minIndex] = buffer;
            }
        }
        return source;
    }

    public Comparable[] sort(Comparable[] source) {
        for (int i = 0; i < source.length - 1; i++) {
            Comparable min = source[i];
            int minIndex = i;
            for (int j = i + 1; j < source.length; j++) {
                final Comparable toCompare = source[j];
                if (min.compareTo(toCompare) > 0) {
                    min = toCompare;
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                final Comparable buffer = source[i];
                source[i] = source[minIndex];
                source[minIndex] = buffer;
            }
        }
        return source;
    }
}
