package collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeSymbolTableTest {
    private SymbolTable<Integer, String> table = new BinaryTreeSymbolTable<>();

    @BeforeEach
    void setUp() {
        IntStream.of(5, 3, 7, 2, 4, 6, 8, 1, 9)
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(9, table.size(), "Invalid size of table");
    }

    @Test
    void deleteRightNodeWithoutChildren() {
        table.delete(9);
        assertEquals(8, table.size(), "Invalid size after delete");
    }

    @Test
    void deleteLeftNodeWithoutChildren() {
        table.delete(6);
        assertEquals(8, table.size(), "Invalid size after delete");
    }

    @Test
    void deleteNodeWithASingleChild() {
        table.delete(8);
        assertEquals(8, table.size(), "Invalid size after delete");
    }

    @Test
    void deleteNodeWithTwoChildren() {
        table.delete(7);
        assertEquals(8, table.size(), "Invalid size after delete");
        assertNotNull(table.get(9), "Can't get value by key 9");
    }

    @Test
    void deleteRootNodeWithTwoChildren() {
        table.delete(5);
        assertEquals(8, table.size(), "Invalid size after delete");
    }

    @Test
    void checkKeys() {
        final Iterable<Integer> keys = table.keys();
        final Iterator<Integer> iterator = keys.iterator();
        while (iterator.hasNext()) {
            final Integer key = iterator.next();
            assertTrue(table.contains(key), "The table doesn't contain a key");
        }
    }

    @Test
    void deleteElementsOneByOne() {
        int count = table.size();
        while (!table.isEmpty()) {
            final Iterable<Integer> keys = table.keys();
            final Integer nextKey = keys.iterator().next();

            assertTrue(table.contains(nextKey), "The table should contain the key");
            assertEquals(count, table.size(), "Invalid size before deletion");

            table.delete(nextKey);
            count--;

            assertFalse(table.contains(nextKey), "The table shouldn't contain the key");
            assertEquals(count, table.size(), "Invalid size after deletion");
        }
    }

    @Test
    void tryToAddALotOfItems() {
        final SymbolTable<Integer, String> table = new BinaryTreeSymbolTable<>();

        final int count = 1_000;
        final Random random = new Random();
        IntStream.generate(random::nextInt)
                .limit(count)
                .distinct()
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(count, table.size(), "Invalid number of items added");

        // try to remove a few of them
        StreamSupport.stream(table.keys().spliterator(), false)
                .limit(count / 2)
                .peek(i -> assertTrue(table.contains(i), "Table doesn't contain necessary key"))
                .forEach(i -> table.delete(i));

        assertEquals(count / 2, table.size(), "Invalid number of items after delete");
    }
}