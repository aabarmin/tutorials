package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    private QuickSort sort = new QuickSort();

    @Test
    void testSort() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{3, 4, 5, 1, 2}));
    }
}