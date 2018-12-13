package collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                new LinkedSymbolTable<>()
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
        IntStream.range(0, count)
                .forEach(i -> table.put(i, String.valueOf(i)));

        final List<String> values = IntStream.range(0, count)
                .mapToObj(key -> table.get(key))
                .filter(value -> value != null)
                .collect(Collectors.toList());
        assertFalse(values.isEmpty(), "Values should be returned back");
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
}