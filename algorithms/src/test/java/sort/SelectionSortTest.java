package sort;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import sort.SelectionSort;
import sort.utils.SortUtils;

class SelectionSortTest {
    private SelectionSort sort = new SelectionSort();

    @Test
    void testSortPrimitives() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort.sort(new int[]{5, 3, 2, 1, 4}));
    }

    @Test
    void testSortComparables() {
        assertArrayEquals(
                new Comparable[]{1, 2, 3, 4,5},
                sort.sort(new Comparable[]{5, 2, 3, 1, 4})
        );
    }

    @Test
    void testSortCollection() {
        final List<Comparable> wrappedCollection = SortUtils.wrapCollection(
                SortUtils.toList(5, 3, 2, 4, 1)
        );
        assertEquals(
                Arrays.asList(1, 2, 3, 4, 5),
                sort.sort(wrappedCollection)
        );
        final InvocationHandler handler = Proxy.getInvocationHandler(wrappedCollection);
        System.out.println(handler);
    }
}