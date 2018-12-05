package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private MergeSort sort = new MergeSort();

    @Test
    void oneMoreTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{5, 2, 1, 3, 4}));
    }
}