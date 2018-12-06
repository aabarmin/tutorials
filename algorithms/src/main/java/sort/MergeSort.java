package sort;

import static sort.utils.SortUtils.isLess;

public class MergeSort {
    private void merge(int[] source, int[] aux, int lo, int mid, int hi) {
        // copy part of source array
        System.arraycopy(source, lo, aux, lo, hi - lo + 1);
        // merge all back
        // i points to the left bound of both arrays, to the lo position now
        // j points to the position in the middle of both arrays
        int i = lo;
        int j = mid + 1;
        // join two arrays (source and aux, aux is the part of source)
        // from lo to hi positions (inclusive)
        // k iterates over all the space form lo to hi, it's an index in a destination array
        for (int k = lo; k <= hi; k++) {
            // left marker has gone to the second part of array that we are merging now
            // let's take items from the right side
            if (i > mid) {
                source[k] = aux[j];
                j++;

            // right marker has gone out of right part of array, let's take from the left part
            } else if (j > hi) {
                source[k] = aux[i];
                i++;

            // these two branches are used when we need to sort small 2-items collections
            // in most cases these branches aren't used
            } else if (isLess(aux[j], aux[i])) {
                // comparing i-th and j-th elements, taking the least of them
                source[k] = aux[j];
                // moving the j pointer forward
                j++;
            } else {
                // comparing i-th and j-th elements, taking the least of them
                source[k] = aux[i];
                // moving the i pointer forward
                i++;
            }
        }
    }

    private void sort(int[] source, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // find a middle of source data
        int mid = lo + (hi - lo) / 2;
        // sort parts one by one
        sort(source, aux, lo, mid);
        sort(source, aux, mid + 1, hi);
        // merge them back
        merge(source, aux, lo, mid, hi);
    }

    public int[] sort(int[] source) {
        int[] aux = new int[source.length];
        sort(source, aux, 0, aux.length - 1);
        return source;
    }

    public Comparable[] sort(Comparable[] source) {
        return source;
    }
}
