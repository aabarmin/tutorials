package sort.selection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {
    private SelectionSort sort = new SelectionSort();

    @Test
    void testSortPrimitives() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{5, 3, 2, 1, 4}));
    }

    @Test
    void testSortComparables() {
        assertArrayEquals(
                new Comparable[]{1, 2, 3, 4,5},
                sort.sort(new Comparable[]{5, 2, 3, 1, 4})
        );
    }
}