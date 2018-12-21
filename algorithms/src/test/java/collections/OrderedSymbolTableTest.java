package collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A special kind of test for an ordered symbol tables.
 */
class OrderedSymbolTableTest {
    static Stream<OrderedSymbolTable<Integer, String>> instances() {
        return Stream.of(
                new BinaryTreeSymbolTable<>()
        );
    }

    @ParameterizedTest
    @MethodSource("instances")
    void getMinAndMaxKeys(OrderedSymbolTable<Integer, String> table) {
        table.put(1, "1");
        table.put(2, "2");

        assertEquals(Integer.valueOf(1), table.min(), "Invalid minimal key");
        assertEquals(Integer.valueOf(2), table.max(), "Invalid maximal key");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void sizeTest(OrderedSymbolTable<Integer, String> table) {
        IntStream.range(1, 11)
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(3, table.size(5, 7));
    }

    @ParameterizedTest
    @MethodSource("instances")
    void rankTest(OrderedSymbolTable<Integer, String> table) {
        IntStream.range(1, 11)
                .forEach(i -> table.put(i, String.valueOf(i)));

        assertEquals(7, table.rank(8));
    }

    @ParameterizedTest
    @MethodSource("instances")
    void deleteMinTest(OrderedSymbolTable<Integer, String> table) {
        IntStream.range(1, 11)
                .forEach(i -> table.put(i, String.valueOf(i)));

        table.deleteMin();
        assertFalse(table.isEmpty(), "Table shouldn't be empty");
        assertFalse(table.contains(1), "Minimal element should be removed");
    }

    @ParameterizedTest
    @MethodSource("instances")
    void deleteMaxTest(OrderedSymbolTable<Integer, String> table) {
        IntStream.range(1, 11)
                .forEach(i -> table.put(i, String.valueOf(i)));

        table.deleteMax();
        assertFalse(table.isEmpty(), "Table shouldn't be empty");
        assertFalse(table.contains(10), "Maximal element should be removed");
    }
}