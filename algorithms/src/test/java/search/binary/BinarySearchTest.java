package search.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {
    @Test
    void searchNoFind() {
        int[] data = new int[]{1, 2, 3, 4, 5};
        assertEquals(BinarySearch.search(10, data), -1);
    }

    @Test
    void searchAndFind() {
        int[] data = new int[]{1, 2, 3, 4, 5};
        assertEquals(BinarySearch.search(3, data), 2);
    }
}