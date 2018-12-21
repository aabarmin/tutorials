package collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Common test for many implementation of the Symbol Table.
 */
class SymbolTableTest {
    static Stream<SymbolTable<Integer, String>> instances() {
        return Stream.of(
                new LinkedSymbolTable<>(),
                new BinaryTreeSymbolTable<>()
        );
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tableIsEmptyByDefault(SymbolTable<Integer, String> table) {
        assertTrue(table.isEmpty(), "Table should be empty by default");
        assertEquals(0, table.size(), "Size should be equal to 0");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tryToAddALotOfItems(SymbolTable<Integer, String> table) {
        final int count = 10_000;
        IntStream.range(0, count)
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertFalse(table.isEmpty(), "Table shouldn't be empty");
        assertEquals(count, table.size(), "A number of keys should be equal to 100.000");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tryToAddAndGetLotOfItems(SymbolTable<Integer, String> table) {
        final int count = 10_000;
        final Random random = new Random();
        final List<Integer> generated = new ArrayList<>();

        IntStream.generate(() -> random.nextInt())
                .limit(count)
                .peek(generated::add)
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(count, table.size(), "Invalid size of collection");

        final List<String> values = generated.stream()
                .map(i -> table.get(i))
                .filter(value -> value != null)
                .collect(Collectors.toList());

        assertEquals(count, values.size(), "Invalid size of returned collection");

        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(generated.get(i)), values.get(i), "Invalid value was received");
        }
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tableShouldReturnNullIfKeyIsNotPresent(SymbolTable<Integer, String> table) {
        table.put(8, "8");
        table.put(10, "10");
        assertNull(table.get(9), "A value shouldn't be provided");
        assertNotNull(table.get(8), "A value should be provided");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void testingContainsMethod(SymbolTable<Integer, String> table) {
        table.put(1, "1");
        assertTrue(table.contains(1), "There is a key in the table");
        assertFalse(table.contains(2), "There is no key in the table");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tryToAddAndThenRemoveItems(SymbolTable<Integer, String> table) {
        IntStream.range(1, 11)
                .unordered()
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(10, table.size(), "Invalid number of elements");

        table.delete(2);
        assertEquals(9, table.size(), "Invalid number of elements after removal");
        assertNull(table.get(2), "Value by removed key was received");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void tryToAddAndRemoveALotOfItems(SymbolTable<Integer, String> table) {
        final int count = 1_000;
        final Random random = new Random();
        final List<Integer> added = new ArrayList<>();
        IntStream.generate(() -> random.nextInt())
                .distinct()
                .limit(count)
                .peek(i -> added.add(i))
                .forEach(i -> table.put(i, String.valueOf(i)));
        assertEquals(count, table.size(), "Invalid number of elements was inserted");
        // assert all added elements are in the table
        added.stream()
                .forEach(i -> {
                    final String value = table.get(i);
                    assertNotNull(value, "There is no value for an added key " + i);
                });
        // removing part of elements
        final List<Integer> removed = new ArrayList<>();
        StreamSupport.stream(table.keys().spliterator(), false)
                .limit(count / 2)
                .peek(removed::add)
                .forEach(i -> table.delete(i));
        assertEquals(count / 2, removed.size(), "Invalid number of removed items");
        assertEquals(count / 2, table.size(), "Invalid size after removal");
        removed.stream()
                .forEach(i -> assertFalse(table.contains(i), "Removed key was found"));
    }

    @ParameterizedTest
    @MethodSource("instances")
    void testKeysMethod(SymbolTable<Integer, String> table) {
        table.put(1, "1");
        table.put(2, "2");
        assertEquals(Arrays.asList(1, 2), table.keys(), "Keys weren't provided");
    }
}