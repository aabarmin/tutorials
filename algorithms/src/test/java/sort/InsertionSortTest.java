package sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import sort.InsertionSort;

class InsertionSortTest {
    final InsertionSort sort = new InsertionSort();

    @Test
    void arrayTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{5, 3, 2, 1, 4}));
    }

    @Test
    void oneMoreTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{5, 2, 1, 3, 4}));
    }
}