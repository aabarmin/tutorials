package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {
    private ShellSort shellSort = new ShellSort();

    @Test
    void testSimple() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, shellSort.sort(
                new int[]{5, 3, 2, 1, 4}
        ));
    }
}