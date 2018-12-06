package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private MergeSort sort = new MergeSort();

    @Test
    void oneMoreTest() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, sort.sort(new int[]{0, 4, 2, 3, 1}));
    }
}