package search.binary;

import java.util.Arrays;

public class BinarySearch {
    public static final int search(int value, int[] collection) {
        Arrays.sort(collection);
        int lo = 0;
        int hi = collection.length - 1;
        while (hi >= lo) {
            int mid = (lo + hi) / 2;
            if (collection[mid] == value) {
                return mid;
            } else if (value > collection[mid]) {
                lo = mid + 1;
            } else if (value < collection[mid]) {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
