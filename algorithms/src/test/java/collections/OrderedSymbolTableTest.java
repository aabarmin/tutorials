package collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
}