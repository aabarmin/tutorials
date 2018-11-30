package sort.selection;

import java.util.List;

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

    public List<Comparable> sort(List<Comparable> source) {
        for (int i = 0; i < source.size() - 1; i++) {
            Comparable min = source.get(i);
            int minIndex = i;
            for (int j = i + 1; j < source.size(); j++) {
                if (min.compareTo(source.get(j)) > 0) {
                    min = source.get(j);
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Comparable buffer = source.get(i);
                source.set(i, source.get(minIndex));
                source.set(minIndex, buffer);
            }
        }
        return source;
    }
}
